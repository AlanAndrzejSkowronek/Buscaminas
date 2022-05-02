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
    public void execPartida(){
        int dif = inpus.elegirDificultad();

        difTablero(dif);
        t.initTablero();
        System.out.println("Generando minas...");
        initBombas(dif);
        t.printTablero();

        while (true) {
            int[] coords = inpus.elegirCasilla();
            t.getCasilla(coords[0], coords[1]).setTapada(false);
            t.printTablero();
        }
    }

    private void initBombas(int dif){
        int altRandom, lonRandom;
        int limitBombas = dif * t.getAlt();
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
}
