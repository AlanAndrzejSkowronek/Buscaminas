public class Casilla {

    private boolean esBanderin = false;
    private boolean esBomba = false;
    private boolean tapada = true;
    private int num;

    public Casilla (int num, boolean esBanderin, boolean esBomba, boolean tapada){
        this.num = num;
        this.esBanderin = esBanderin;
        this.esBomba = esBomba;
        this.tapada = tapada;
    }

    public int getNum() { return num; }
    public boolean EsBomba() { return esBomba; }
    public boolean tieneBanderin() { return esBanderin; }
    public boolean estaTapada() { return tapada; }
}
