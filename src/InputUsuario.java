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

    public int[] elegirCasilla() {
        String elec;
        int x = 1, y = 1;
        int[] pos;

        do {

            System.out.print("Elige fila: ");
            x = leer.nextInt();
            System.out.print("Elige columna: ");
            y = leer.nextInt();

            System.out.println("Estas seguro de que quieres esta posición?");
            System.out.println("           Fila: " + x);
            System.out.println("        Columna: " + y);
            System.out.println("SI [s] o NO [n]: ");
            elec = leer.next();

        } while (elec.equalsIgnoreCase("n"));

        return pos = new int[]{x, y};
    }
}
