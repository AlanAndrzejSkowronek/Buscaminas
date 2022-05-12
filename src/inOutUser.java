import java.util.Scanner;

public class InOutUser implements GenericInOutUser {
    private final Scanner read = new Scanner(System.in);

    public int chooseDifficulty(){
        int dif;
        System.out.println("Please, pick a difficulty:");
        System.out.println("    1. Easy \n" +
                "    2. Normal \n" +
                "    3. Hard");
        System.out.print("Write a number: ");
        dif = read.nextInt();

        while (dif > 3 || dif < 1){
            System.out.print("That difficulty doesn't exists! Pick another, please... ");
            dif = read.nextInt();
        }

        return dif;
    }

    public int[] pickCell() {
        String elec;
        int x, y;

        do {
            System.out.print("Choose row: ");
            x = read.nextInt() - 1;
            System.out.print("Choose column: ");
            y = read.nextInt() - 1;

            System.out.println("Are you sure you want this position?: ");
            System.out.println("           Row: " + (x + 1));
            System.out.println("        Column: " + (y + 1));
            System.out.println("YES [y] or NO [n]: ");
            elec = read.next();

        } while (elec.equalsIgnoreCase("n"));

        return new int[]{x, y};
    }

    public boolean chooseToPlaceFlag(){
        if (read.next().equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }

    public void wantToAddFlagMessage() {
        System.out.print("You want to add/remove a flag? YES [y] or NO [n]: ");
    }

    public void numFlagsAvailableMessage(int flagsAvailable) {
        System.out.println("You have " + flagsAvailable + " flag/s left (Remember to have 0 or more flags left to win).");
    }

    public void cantAddFlagMessage() {
        System.out.println("You can't add a flag there!");
    }

    public void cantRevealFlagMessage() {
        System.out.println("You can't reveal a cell with a flag!");
    }
}

