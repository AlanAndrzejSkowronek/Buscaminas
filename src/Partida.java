import java.sql.SQLOutput;
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
    int banderinesDisponibles, bombasTablero, casillasSinBombaDesveladas;
    public void execPartida(){
        int dif = inpus.elegirDificultad();

        difTablero(dif);
        t.initTablero();
        initBombas(dif);
        t.printTablero();

        do {
            eleccionBanderin();
            coords = inpus.elegirCasilla();

            // sacar a metodo comprobacion partida perdida
            if (comprobarBomba(coords[0], coords[1]) && !t.getCasilla(coords[0], coords[1]).tieneBanderin()){
                System.out.println("Has perdido la partida!");
                t.getCasilla(coords[0], coords[1]).setTapada(false);
                t.printTablero();
                break;
            }
            desvelarCasilla(coords[0], coords[1]);
            t.printTablero();
        } while (!comprobarTableroVictoria());
    }

    private void initBombas(int dif){
        int altRandom, lonRandom;
        int limitBombas = dif * t.getAlt();
        banderinesDisponibles = limitBombas;
        casillasSinBombaDesveladas = t.returnTamany() - banderinesDisponibles;
        int i = 0;

        while (i <= limitBombas){
            altRandom = rdm.nextInt(t.getAlt());
            lonRandom = rdm.nextInt(t.getLon());

            if (!t.getCasilla(altRandom, lonRandom).esBomba()){

                t.getCasilla(altRandom, lonRandom).setNum(9);
                t.getCasilla(altRandom, lonRandom).setBomba(true);
                t.getCasilla(altRandom, lonRandom).setTapada(false);

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

        if (!t.getCasilla(coord1, coord2).estaTapada())
            return;

        if (t.getCasilla(coord1, coord2).tieneBanderin()) {
            System.out.println("No se puede desvelar un banderín!");
            return;
        }

        t.getCasilla(coord1, coord2).setTapada(false);
        casillasSinBombaDesveladas--;

        if (t.getCasilla(coord1, coord2).getNum() != 0)
            return;

        for (int i = 0; i < direcciones.length; i++)
            if (estaEnRango(coord1 + direcciones[i][0], coord2 + direcciones[i][1]))
                desvelarCasilla(coord1 + direcciones[i][0], coord2 + direcciones[i][1]);
    }

    private void eleccionBanderin() {
        System.out.print("Quieres poner/quitar un banderín? SI [s] o NO [n]: ");

        coords = inpus.ponerBanderin();

        if (coords == null)
            return;

        if (!t.getCasilla(coords[0], coords[1]).estaTapada()) {
            System.out.println("No se puede poner ahí un banderín!");
        } else if (t.getCasilla(coords[0], coords[1]).tieneBanderin()) {
            t.getCasilla(coords[0], coords[1]).setBanderin(false);
            banderinesDisponibles++;
        } else {
            t.getCasilla(coords[0], coords[1]).setBanderin(true);
            banderinesDisponibles--;
        }
        t.printTablero();
        System.out.println("Te quedan " + banderinesDisponibles + " para poner (recuerda tener 0 o más banderines disponibles para ganar.)");
        eleccionBanderin();
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

    private boolean comprobarTableroVictoria(){
        int tamany = t.returnTamany();
        int casillasSinBomba = tamany - bombasTablero;

        if (banderinesDisponibles >= 0){
            if (casillasSinBombaDesveladas <= 0){
                System.out.println("Has ganado la partida!");
                return true;
            }
        }
        return false;
    }

}
