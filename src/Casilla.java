public class Casilla {

    private boolean esBanderin = false;
    private boolean esBomba = false;
    private boolean tapada = true;
    private int num, alt, lon;

    public Casilla (int num, boolean esBanderin, boolean esBomba, boolean tapada, int posRow, int posCol){
        this.num = num;
        this.esBanderin = esBanderin;
        this.esBomba = esBomba;
        this.tapada = tapada;
        this.alt = posRow;
        this.lon = posCol;
    }

    public int getNum() { return num; }
    public boolean EsBomba() { return esBomba; }
    public boolean tieneBanderin() { return esBanderin; }
    public boolean estaTapada() { return tapada; }

    @Override
    public String toString(){

        if(tieneBanderin()){ return "\uD83D\uDEA9"; }
        if (estaTapada() && !tieneBanderin()){ return "?"; }
        if (getNum() == 0 && !estaTapada()){ return " "; }
        if (getNum() != 0 && !tieneBanderin()){ return "" + getNum(); }

        return " ";
    }
}
