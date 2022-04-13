import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUsuario {

    private final Scanner leer = new Scanner(System.in);

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

    public void elegirFichaRevelar(){

        leer.nextInt();

    }
}
