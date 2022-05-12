public class Cell {

    private boolean hasFlag = false;
    private boolean hasBomb = false;
    private boolean isHidden = true;
    private int numNeighborBombs;

    public Cell (int numNeighborBombs){
        this.numNeighborBombs = numNeighborBombs;
    }

    public int getNumNeighborBombs() { return numNeighborBombs; }
    public boolean isBomb() { return hasBomb; }
    public boolean hasFlag() { return hasFlag; }
    public boolean isHidden() { return isHidden; }
    public void addNum(int num) { this.numNeighborBombs += num; }
    public void setBomb(boolean bomb) { this.hasBomb = bomb; }
    public void changeFlag() { this.hasFlag = !hasFlag; }
    public void setHidden(boolean hidden) { this.isHidden = hidden; }
    @Override
    public String toString(){

        if(hasFlag()){ return "\uD83D\uDEA9"; }
        if (isHidden() && !hasFlag()){ return "?"; }
        if (getNumNeighborBombs() == 0 && !isHidden() && !isBomb()){ return " "; }
        if (getNumNeighborBombs() != 0 && !hasFlag() && !isHidden() && !isBomb()){ return "" + getNumNeighborBombs(); }
        if (isBomb() && !isHidden()){ return "¤"; }

        return " ";
    }
}
