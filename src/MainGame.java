
public class MainGame{

    public static void main(String[] args)
    {
        // create the game instance
        GameInstance gameInst = new GameInstance();

        // create game Thread
        Thread gameThread = new Thread(gameInst);
        gameThread.start();
    }
}
