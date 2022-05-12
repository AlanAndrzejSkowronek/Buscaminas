import java.util.Random;

public class GameLogic {
    private final GenericInOutUser inpus;
    public static Board b;
    private final Random rdm = new Random();
    private final int[][] directions = {
            {0, 1},    // RIGHT
            {1, 1},    // RIGHT, DOWN
            {1, 0},    // DOWN
            {1, -1},   // LEFT, DOWN
            {0, -1},   // LEFT
            {-1, -1},  // LEFT, UP
            {-1, 0},   // UP
            {-1, 1}    // RIGHT, UP
    };
    int[] coords, randomPos;
    int flagsAvailable, cellsHiddenWithoutBombs;

    public GameLogic(GenericInOutUser geninoutuser){
        this.inpus = geninoutuser;
    }

    public void execGame(){
        int dif = inpus.chooseDifficulty();

        b = createBoardOutOfDifValue(dif);
        b.initBoard();
        initBombs(dif);
        b.printBoard();

        do {
            inpus.wantToAddFlagMessage();

            if (inpus.chooseToPlaceFlag()){
                coords = inpus.pickCell();

                if (checkIfCanPlaceFlag()){
                    flagsAvailable = b.toggleFlag(coords[0], coords[1], flagsAvailable);
                    inpus.numFlagsAvailableMessage(flagsAvailable);
                }
            } else {
                coords = inpus.pickCell();
                showCell(coords[0], coords[1]);
            }
            b.printBoard();
        } while (!checkBoardWin() && !b.checkLostGame(coords[0], coords[1]));
    }

    private void initBombs(int dif){
        int bombsLimit = dif * b.getRow();
        flagsAvailable = bombsLimit;
        cellsHiddenWithoutBombs = b.returnSize() - flagsAvailable;
        int i = 0;

        while (i <= bombsLimit){
            randomPos = generateRandomNumbers();
            if (!b.getCell(randomPos[0], randomPos[1]).isBomb()){
                assignBomb(randomPos[0], randomPos[1]);
                addNumToNeighbors(randomPos[0], randomPos[1]);
                i++;
            }
        }
    }

    private int[] generateRandomNumbers(){
        return new int[]{rdm.nextInt(b.getRow()), rdm.nextInt(b.getCol())};
    }

    private void addNumToNeighbors(int coord1, int coord2){
        for (int[] direction : directions) {
            if (b.isInRange(coord1 + direction[0], coord2 + direction[1])) {
                b.getCell(coord1 + direction[0], coord2 + direction[1]).addNum(1);
            }
        }
    }

    private void assignBomb(int row, int col){
        b.getCell(row, col).setBomb(true);
        // b.getCell(row, col).setHidden(false); // CHEAT-MODE (Testeos).
    }

    private void showCell(int coord1, int coord2){

        if (!b.getCell(coord1, coord2).isHidden())
            return;

        if (b.getCell(coord1, coord2).hasFlag()) {
            inpus.cantRevealFlagMessage();
            return;
        }

        b.getCell(coord1, coord2).setHidden(false);
        cellsHiddenWithoutBombs--;

        if (b.getCell(coord1, coord2).getNumNeighborBombs() != 0 || b.getCell(coord1, coord2).isBomb())
            return;

        for (int[] direction : directions)
            if (b.isInRange(coord1 + direction[0], coord2 + direction[1]))
                showCell(coord1 + direction[0], coord2 + direction[1]);
    }

    private boolean checkIfCanPlaceFlag() {
        if (b.getCell(coords[0], coords[1]).isHidden()) {
            return true;
        }
        inpus.cantAddFlagMessage();
        return false;
    }

    private Board createBoardOutOfDifValue(int dif){
        int[] size = {5, 5};
        size[0] *= dif;
        size[1] *= dif;
        return new Board(size[0], size[1]);
    }

    private boolean checkBoardWin(){
        if (flagsAvailable >= 0){
            if (cellsHiddenWithoutBombs <= 0){ // ==
                System.out.println("You won the game! Congrats!!!");
                return true;
            }
        }
        return false;
    }
}
