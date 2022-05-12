public class LaunchGame {

    public static void main(String[] args){
        GameLogic g = new GameLogic(new InOutUser());
        g.execGame();
    }
}
