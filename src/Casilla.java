public class Casilla {

    private boolean esBanderin = false;
    private boolean bomba = false;
    private boolean tapada = true;
    private int num, alt, lon;

    public Casilla (int num, boolean esBanderin, boolean esBomba, boolean tapada, int posRow, int posCol){
        this.num = num;
        this.esBanderin = esBanderin; // no hace falta
        this.bomba = esBomba; // no hace falta
        this.tapada = tapada; // no hace falta
        this.alt = posRow;
        this.lon = posCol;
    }

    public int getNum() { return num; }
    public boolean esBomba() { return bomba; }
    public boolean tieneBanderin() { return esBanderin; }
    public boolean estaTapada() { return tapada; }

    public void setNum(int num) { this.num += num; }

    @Override
    public String toString(){

        if(tieneBanderin()){ return "\uD83D\uDEA9"; }
        if (estaTapada() && !tieneBanderin()){ return "?"; }
        if (getNum() == 0 && !estaTapada()){ return " "; }
        if (getNum() != 0 && !tieneBanderin() && !estaTapada()){ return "" + getNum(); }
        if (esBomba() && !estaTapada()){ return "\uD83D\uDCA3"; }

        return " ";
    }
}
