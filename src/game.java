import java.util.Random;

public class game {
    private inputUser inpus = new inputUser();
    public static board b;
    private Random rdm = new Random();
    private int[][] directions = {
            {0, 1},    // RIGHT
            {1, 1},    // RIGHT, DOWN
            {1, 0},    // DOWN
            {1, -1},   // LEFT, DOWN
            {0, -1},   // LEFT
            {-1, -1},  // LEFT, UP
            {-1, 0},   // UP
            {-1, 1}    // RIGHT, UP
    };
    int[] coords;
    int flagsAvailable, bombsInBoard, cellsShownWithoutBombs;
    public void execPartida(){
        int dif = inpus.chooseDifficulty();

        createBoardOutOfDifValue(dif);
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
        int rowRandom, colRandom;
        int bombsLimit = dif * b.getRow();
        flagsAvailable = bombsLimit;
        cellsShownWithoutBombs = b.returnSize() - flagsAvailable;
        int i = 0;

        while (i <= bombsLimit){
            rowRandom = rdm.nextInt(b.getRow());
            colRandom = rdm.nextInt(b.getCol());

            if (!b.getCell(rowRandom, colRandom).isBomb()){

                b.getCell(rowRandom, colRandom).setNum(9);
                b.getCell(rowRandom, colRandom).setBomb(true);

                for (int j = 0; j < directions.length; j++){
                    if (isInRange(rowRandom + directions[j][0], colRandom + directions[j][1])){
                        b.getCell(rowRandom + directions[j][0], colRandom + directions[j][1]).addNum(1);
                    }
                }
                i++;
            }
        }
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

        for (int i = 0; i < directions.length; i++)
            if (isInRange(coord1 + directions[i][0], coord2 + directions[i][1]))
                showCell(coord1 + directions[i][0], coord2 + directions[i][1]);
    }

    private void chooseFlag() {
        System.out.print("You want to add/remove a flag? YES [y] or NO [n]: ");

        coords = inpus.placeFlag();

        if (coords == null)
            return;

        if (!b.getCell(coords[0], coords[1]).isHidden()) {
            System.out.println("You can't add a flag there!");
        } else if (b.getCell(coords[0], coords[1]).hasFlag()) {
            b.getCell(coords[0], coords[1]).setFlag(false);
            flagsAvailable++;
        } else {
            b.getCell(coords[0], coords[1]).setFlag(true);
            flagsAvailable--;
        }
        b.printBoard();
        System.out.println("You have " + flagsAvailable + " flag/s left (Remember to have 0 or more flags left to win).");
        chooseFlag();
    }

    private board createBoardOutOfDifValue(int dif){
        int size = 5;
        size *= dif;
        return b = new board(size, size);
    }

    private boolean isInRange(int pos1, int pos2){
        return  pos1 < b.getRow() &&
                pos1 >= 0         &&
                pos2 < b.getCol() &&
                pos2 >= 0;
    }

    private boolean checkBoardWin(){
        int size = b.returnSize();
        int casillasSinBomba = size - bombsInBoard;

        if (flagsAvailable >= 0){
            if (cellsShownWithoutBombs <= 0){
                System.out.println("You won the game! Congrats!!!");
                return true;
            }
        }
        return false;
    }
}
