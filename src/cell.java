public class cell {

    private boolean hasFlag = false;
    private boolean hasBomb = false;
    private boolean isHidden = true;
    private int num, row, col;

    public cell (int num, int posRow, int posCol){
        this.num = num;
        this.row = posRow;
        this.col = posCol;
    }

    public int getNum() { return num; }
    public boolean isBomb() { return hasBomb; }
    public boolean hasFlag() { return hasFlag; }
    public boolean isHidden() { return isHidden; }

    public void setNum(int num) { this.num = num; }
    public void addNum(int num) { this.num += num; }
    public void setBomb(boolean bomb) { this.hasBomb = bomb; }
    public void setFlag() { this.hasFlag = true; }
    public void unsetFlag() { this.hasFlag = false; }
    public void setHidden(boolean hidden) { this.isHidden = hidden; }

    @Override
    public String toString(){

        if(hasFlag()){ return "\uD83D\uDEA9"; }
        if (isHidden() && !hasFlag()){ return "?"; }
        if (getNum() == 0 && !isHidden()){ return " "; }
        if (getNum() != 0 && !hasFlag() && !isHidden() && !isBomb()){ return "" + getNum(); }
        if (isBomb() && !isHidden()){ return "¤"; }

        return " ";
    }
}
