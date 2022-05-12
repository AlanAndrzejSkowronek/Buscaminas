public class Board {

    private Cell[][] board;
    private int row, col;

    public Board(int rows, int cols){
        this.row = rows;
        this.col = cols;
        board = new Cell[this.row][this.col];
    }

    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public int returnSize(){ return (getCol() * getRow()) - 1; }
    public Cell getCell(int row, int col){ return board[row][col]; }

    public void printBoard(){

        for(int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                System.out.print("  |  ");
                System.out.print(getCell(i, j));
            }
            System.out.println("  |  ");
        }
    }

    public void initBoard(){

        for(int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                this.board[i][j] = new Cell(0);
            }
        }
    }

    public boolean checkLostGame(int row, int col){
        if (checkBomb(row, col) && !hasFlag(row, col)){
            System.out.print("You lost the game!");
            return true;
        }
        return false;
    }

    public int toggleFlag(int row, int col, int flagsAvailable){

        if (!hasFlag(row, col))
            flagsAvailable--;
        else
            flagsAvailable++;

        getCell(row, col).changeFlag();

        return flagsAvailable;
    }

    public boolean isInRange(int row, int col){
        return  row < getRow() &&
                row >= 0       &&
                col < getCol() &&
                col >= 0;
    }

    private boolean checkBomb(int row, int col){
        return getCell(row, col).isBomb();
    }
    private boolean hasFlag(int row, int col) { return getCell(row, col).hasFlag(); }
}
