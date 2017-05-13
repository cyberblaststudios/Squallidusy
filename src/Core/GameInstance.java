package Core;

import Levels.Level;
import Levels.MainLevel;

/**
 * Created by jevanger on 2/6/2017.
 */

public class GameInstance implements Runnable
{
    // this statically links the game instance singleton
    private static GameInstance CurrentGameInst;

    // reference to the current running level
    private Level CurrentLevel;

    // denotes whether the game is updating
    public boolean isRunning = true;

    // determines the settings for the game loop
    private final int TARGET_FPS = 60;
    private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    private int fps = 0;
    private int lastFpsTime = 0;

    // display for drawing things to the screen
    private Display display;

    public GameInstance()
    {
        // set the singleton to this instance
        CurrentGameInst = this;

        // load all initial assets
        AssetManager.GetAssetManager();

        // generates a new display
        display = new Display();

        // run the current level >>>>TODO<<<<< make this a function to switch levels
        CurrentLevel = new MainLevel();
        CurrentLevel.StartLevel();
    }

    // returns the current game instance
    public static GameInstance GetGameInstance()
    {
        return CurrentGameInst;
    }

    // gets the current running level
    public Level GetCurrentLevel()
    {
        return CurrentLevel;
    }

    // gets the display
    public Display GetDisplay()
    {
        return display;
    }

    // this is the beating heart of the game
    public void GameLoop()
    {
        long lastLoopTime = System.nanoTime();

        // keep looping round til the game ends
        while (isRunning)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            float delta = updateLength / ((float)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                // debug the actual drawing FPS
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            Tick(delta);

            // draw to screen
            Render();

            try{
                Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            }
            catch(Exception e)
            {
                // some thread thing failed!
            }
        }
    }

    public void Tick(float deltaTime)
    {
        // runs every frame

        // tick the level
        CurrentLevel.LevelTick(deltaTime);
    }

    public void Render()
    {
        // Runs after the tick
        display.DrawToScreen();
    }

    public void run() {
        // start the game loop
        GameLoop();

        // when the game loop stops, then the game thread returns and the game closes
    }
}
