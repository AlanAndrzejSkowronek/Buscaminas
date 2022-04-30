public class Casilla {

    private boolean esBanderin = false;
    private boolean bomba = false;
    private boolean tapada = true;
    private int num, alt, lon;

    public Casilla (int num, int posRow, int posCol){
        this.num = num;
        this.alt = posRow;
        this.lon = posCol;
    }

    public int getNum() { return num; }
    public boolean esBomba() { return bomba; }
    public boolean tieneBanderin() { return esBanderin; }
    public boolean estaTapada() { return tapada; }

    public void setNum(int num) { this.num = num; }
    public void addNum(int num) { this.num += num; }
    public void setBomba(boolean bomba) { this.bomba = bomba; }
    public void setBanderin(boolean banderin) { this.esBanderin = banderin; }
    public void setTapada(boolean tapada) { this.tapada = tapada; }

    @Override
    public String toString(){

        if(tieneBanderin()){ return "\uD83D\uDEA9"; }
        if (estaTapada() && !tieneBanderin()){ return "?"; }
        if (getNum() == 0 && !estaTapada()){ return " "; }
        if (getNum() != 0 && !tieneBanderin() && !estaTapada() && !esBomba()){ return "" + getNum(); }
        if (esBomba() && !estaTapada()){ return "¤"; }

        return " ";
    }
}
