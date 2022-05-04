import java.util.Random;

public class Partida {
    private InputUsuario inpus = new InputUsuario();
    public static Tablero t;
    private Random rdm = new Random();
    private int[][] direcciones = {
            {0, 1},    // RIGHT
            {1, 1},    // RIGHT, DOWN
            {1, 0},    // DOWN
            {1, -1},   // LEFT, DOWN
            {0, -1},   // LEFT
            {-1, -1},  // LEFT, UP
            {-1, 0},   // UP
            {-1, 1}    // RIGHT, UP
    };
    int[] coords;
    int banderinesDisponibles;
    public void execPartida(){
        int dif = inpus.elegirDificultad();

        difTablero(dif);
        t.initTablero();
        initBombas(dif);
        t.printTablero();

        while (true) {
            coords = inpus.elegirCasilla();

            // sacar a metodo comprobacion partida perdida
            if (comprobarBomba(coords[0], coords[1])){
                System.out.println("Has perdido la partida!");
                t.getCasilla(coords[0], coords[1]).setTapada(false);
                t.printTablero();
                break;
            }
            desvelarCasilla(coords[0], coords[1]);
            t.printTablero();

            // sacar a metodo eleccionBanderin
            coords = null;
            System.out.println("Quieres poner banderín (SI [s] o NO [n])?: ");
            coords = inpus.ponerBanderin();
            if (coords != null && !t.getCasilla(coords[0], coords[1]).tieneBanderin() && t.getCasilla(coords[0], coords[1]).estaTapada()){
                t.getCasilla(coords[0], coords[1]).setBanderin(true);
                t.printTablero();
                System.out.println("Quieres poner otro?: ");
                coords = inpus.ponerBanderin();
            }
        }
    }

    private void initBombas(int dif){
        int altRandom, lonRandom;
        int limitBombas = dif * t.getAlt();
        banderinesDisponibles = limitBombas;
        int i = 0;

        while (i <= limitBombas){
            altRandom = rdm.nextInt(t.getAlt());
            lonRandom = rdm.nextInt(t.getLon());

            if (!t.getCasilla(altRandom, lonRandom).esBomba()){

                t.getCasilla(altRandom, lonRandom).setNum(9);
                t.getCasilla(altRandom, lonRandom).setBomba(true);

                for (int j = 0; j < direcciones.length; j++){
                    if (estaEnRango(altRandom + direcciones[j][0], lonRandom + direcciones[j][1])){
                        t.getCasilla(altRandom + direcciones[j][0], lonRandom + direcciones[j][1]).addNum(1);
                    }
                }
                i++;
            }
        }
    }

    private void desvelarCasilla(int coord1, int coord2){

        if (!t.getCasilla(coord1, coord2).estaTapada() || t.getCasilla(coord1, coord2).tieneBanderin())
            return;

        t.getCasilla(coord1, coord2).setTapada(false);

        if (t.getCasilla(coord1, coord2).getNum() != 0)
            return;

        for (int i = 0; i < direcciones.length; i++)
            if (estaEnRango(coord1 + direcciones[i][0], coord2 + direcciones[i][1]))
                desvelarCasilla(coord1 + direcciones[i][0], coord2 + direcciones[i][1]);
    }

    private Tablero difTablero(int dif){
        int tamany = 5;
        tamany *= dif;
        return t = new Tablero(tamany, tamany);
    }

    private boolean estaEnRango(int pos1, int pos2){
        return  pos1 < t.getAlt() &&
                pos1 >= 0         &&
                pos2 < t.getLon() &&
                pos2 >= 0;
    }
    private boolean comprobarBomba(int coord1, int coord2){
        return t.getCasilla(coord1, coord2).esBomba();
    }

    /*
    private boolean comprobarTableroVictoria(){
        for (int i = 0; i < t.getAlt(); i++){
            for (int j = 0; j < t.getLon(); j++){
                if (!t.getCasilla(i, j).esBomba() && !t.getCasilla(i, j).estaTapada()){
                    return true;
                }
            }
        }
        return false;
    }
    STAND BY
     */
}
