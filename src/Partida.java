import java.util.Random;

public class Partida {
    private InputUsuario inpus = new InputUsuario();
    public static Tablero t;
    private Random rdm = new Random();
    private int[] direcciones = {0, 1, 1, 1, 1, 0, 1, -1, 0, -1, -1, -1, -1, 0, -1, 1};
    public void execPartida(){
        int dif = inpus.elegirDificultad();

        difTablero(dif);
        t.initTablero();
        System.out.println("Generando minas...");
        initBombas(dif);
        t.printTablero();
    }
    private void initBombas(int dif){
        int altRandom, lonRandom;

        for (int i = 0; i < dif * t.getAlt(); i++){
            altRandom = rdm.nextInt(t.getAlt());
            lonRandom = rdm.nextInt(t.getLon());

            t.setCasilla(altRandom, lonRandom, 9, false, true, false);

            for (int j = 0; j < (direcciones.length - 2); j += 2){
                if (estaEnRango(altRandom, lonRandom)){
                    t.getCasilla(altRandom + direcciones[j], lonRandom + direcciones[j + 1]).setNum(1);
                }
            }
        }
    }

    private Tablero difTablero(int dif){
        int tamany = 5;
        tamany *= dif;
        return t = new Tablero(tamany, tamany);
    }

    private boolean estaEnRango(int pos1, int pos2){
        return (pos1 < t.getAlt() && pos1 >= 0 &&
                pos2 < t.getLon() && pos2 >= 0);
    }
}
