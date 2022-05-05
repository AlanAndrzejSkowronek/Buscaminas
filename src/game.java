import java.util.Random;

public class game {
    private final inputUser inpus = new inputUser();
    public static board b;
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
    int flagsAvailable, cellsShownWithoutBombs;
    public void execGame(){
        int dif = inpus.chooseDifficulty();

        b = createBoardOutOfDifValue(dif);
        b.initBoard();
        initBombs(dif);
        b.printBoard();

        do {
            chooseFlag();
            coords = inpus.pickCell();

            showCell(coords[0], coords[1]);
            b.printBoard();
        } while (!checkBoardWin() && !b.checkLostGame(coords[0], coords[1]));
    }

    private void initBombs(int dif){
        int bombsLimit = dif * b.getRow();
        flagsAvailable = bombsLimit;
        cellsShownWithoutBombs = b.returnSize() - flagsAvailable;
        int i = 0;

        while (i <= bombsLimit){
            randomPos = generateRandomNumbers();
            if (!b.getCell(randomPos[0], randomPos[1]).isBomb()){
                assignBomb(randomPos[0], randomPos[1]);
                addNumToNeighbors();
                i++;
            }
        }
    }

    private int[] generateRandomNumbers(){
        return new int[]{rdm.nextInt(b.getRow()), rdm.nextInt(b.getCol())};
    }

    private void addNumToNeighbors(){
        for (int[] direction : directions) {
            if (b.isInRange(randomPos[0] + direction[0], randomPos[1] + direction[1])) {
                b.getCell(randomPos[0] + direction[0], randomPos[1] + direction[1]).addNum(1);
            }
        }
    }

    private void assignBomb(int row, int col){
        b.getCell(row, col).setNum(9);
        b.getCell(row, col).setBomb(true);
    }

    private void showCell(int coord1, int coord2){

        if (!b.getCell(coord1, coord2).isHidden())
            return;

        if (b.getCell(coord1, coord2).hasFlag()) {
            System.out.println("You can't reveal a cell with a flag!");
            return;
        }

        b.getCell(coord1, coord2).setHidden(false);
        cellsShownWithoutBombs--;

        if (b.getCell(coord1, coord2).getNum() != 0)
            return;

        for (int[] direction : directions)
            if (b.isInRange(coord1 + direction[0], coord2 + direction[1]))
                showCell(coord1 + direction[0], coord2 + direction[1]);
    }

    private void chooseFlag() {
        System.out.print("You want to add/remove a flag? YES [y] or NO [n]: ");
        coords = inpus.placeFlag();

        if (coords == null)
            return;

        if (!b.getCell(coords[0], coords[1]).isHidden()) {
            System.out.println("You can't add a flag there!");
        } else {
            flagsAvailable = b.toggleFlag(coords[0], coords[1], flagsAvailable);
        }
        b.printBoard();
        System.out.println("You have " + flagsAvailable + " flag/s left (Remember to have 0 or more flags left to win).");
        chooseFlag();
    }

    private board createBoardOutOfDifValue(int dif){
        int[] size = {5, 5};
        size[0] *= dif;
        size[1] *= dif;
        return new board(size[0], size[1]);
    }

    private boolean checkBoardWin(){
        if (flagsAvailable >= 0){
            if (cellsShownWithoutBombs <= 0){
                System.out.println("You won the game! Congrats!!!");
                return true;
            }
        }
        return false;
    }
}
