public class Tablero {

    private Casilla[][] tablero;
    private int alt, lon;
    //private InputUsuario inpus = new InputUsuario();

    public Tablero(int alt, int lon){
        this.alt = alt;
        this.lon = lon;
        tablero = new Casilla[this.alt][this.lon];
    }

    public int getAlt(){ return alt; }
    public int getLon(){ return lon; }
    public Casilla getCasilla(int alt, int lon){ return tablero[alt][lon]; }

    public void setCasilla(int num, int alt, int lon){
        this.tablero[alt][lon] = new Casilla(num, alt, lon);
    }

    public void printTablero(){

        for(int i = 0; i < getAlt(); i++){
            for(int j = 0; j < getLon(); j++){
                System.out.print("  |  ");
                System.out.print(getCasilla(i, j));
            }
            System.out.println("  |  ");
        }
    }

    public void initTablero(){

        for(int i = 0; i < getAlt(); i++){
            for(int j = 0; j < getLon(); j++){
                this.tablero[i][j] = new Casilla(0, i, j);
            }
        }
    }
}
