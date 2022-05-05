public class board {

    private cell[][] board;
    private int row, col;

    public board(int rows, int cols){
        this.row = rows;
        this.col = cols;
        board = new cell[this.row][this.col];
    }

    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public int returnSize(){ return (getCol() * getRow()) - 1; }
    public cell getCell(int row, int col){ return board[row][col]; }

    public void setCell(int num, int row, int col){
        this.board[row][col] = new cell(num, row, col);
    }

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
                this.board[i][j] = new cell(0, i, j);
            }
        }
    }

    public boolean checkLostGame(int row, int col){
        if (checkBomb(row, col) && !getCell(row, col).hasFlag()){
            System.out.print("You lost the game!");
            return true;
        }
        return false;
    }

    private boolean checkBomb(int row, int col){
        return getCell(row, col).isBomb();
    }
}