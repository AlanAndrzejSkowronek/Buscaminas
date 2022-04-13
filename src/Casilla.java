public class Casilla {

    private boolean esBanderin = false;
    private boolean esBomba = false;
    private int num;

    public Casilla (int num, boolean esBanderin, boolean esBomba){
        this.num = num;
        this.esBanderin = esBanderin;
        this.esBomba = esBomba;
    }

    public int getNum() { return num; }
    public boolean EsBomba() { return esBomba; }
    public boolean tieneBanderin() { return esBanderin; }
}
