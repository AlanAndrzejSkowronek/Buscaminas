public class Tablero {

    private Casilla[][] tablero;
    private int lon, alt;

    public Tablero(int lon, int alt){
        this.lon = lon;
        this.alt = alt;
        tablero = new Casilla[this.lon][this.alt];
    }

    public int getLon(){ return lon; }
    public int getAlt(){ return alt; }
    public Casilla getCasilla(int alt, int lon){ return tablero[alt][lon]; }

    public void setCasilla(int alt, int lon, int num, boolean esBanderin, boolean esBomba, boolean estaTapada){
        this.tablero[alt][lon] = new Casilla(num, esBanderin, esBomba, estaTapada);
    }

    public void printTablero(){

        setCasilla(3, 2, 4, true, false, true);

        for(int i = 0; i < (getLon() - 1); i++){
            for(int j = 0; j < (getAlt() - 1); j++){

                System.out.print("  |  ");
                if(getCasilla(i, j).tieneBanderin()){
                    System.out.print("<|");

                } else if (getCasilla(i, j).estaTapada()){
                    System.out.print("?");
                }

            }
            System.out.print("  |  ");
            System.out.println();
        }
    }

    public void initTablero(){

        for(int i = 0; i < (getLon() - 1); i++){
            for(int j = 0; j < (getAlt() - 1); j++){
                this.tablero[i][j] = new Casilla(0, false, false, true);
            }
        }
    }

    public void genBombas(){

    }
}
