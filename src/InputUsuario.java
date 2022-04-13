import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUsuario {

    private final Scanner leer = new Scanner(System.in);
    private Tablero t = Partida.t;

    public int elegirDificultad(){
        int dif = 2;
        System.out.println("Porfavor, elija una dificultad:");
        System.out.println("    1. Facil \n" +
                           "    2. Mediana \n" +
                           "    3. Difícil");
            System.out.println("Escriba un número: ");
            dif = leer.nextInt();

            while (dif > 3 || dif < 1){
                System.out.println("No existe esa dificultad! Escoje otra.");
                dif = leer.nextInt();
            }

        return dif;
    }

    private void elegirCasilla() {
        String elec;
        int x, y;
        do {
            x = elegirFichaRevelarX();
            y = elegirFichaRevelarY();

            System.out.println("Estas seguro de que quieres esta posición?");
            System.out.println("           Fila: " + x);
            System.out.println("        Columna: " + y);
            System.out.println("SI [s] o NO [n]: ");
            elec = leer.next();

        } while (elec == "n");
    }

    public int elegirFichaRevelarX(){
        System.out.println("Elige una fila: ");
        int x = leer.nextInt();

        while (x > t.getAlt() || x < 0) {
            System.out.println("Elige una fila en el rango!: 0 - " + t.getAlt());
            x = leer.nextInt();
        }
        //return new int {x,y}
        return x;
    }

    public int elegirFichaRevelarY(){
        System.out.println("Elige una columna: ");
        int y = leer.nextInt();

        while (y > t.getLon() || y < 0) {
            System.out.println("Elige una fila en el rango!: 0 - " + t.getLon());
            y = leer.nextInt();
        }

        return y;
    }
}
