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
    public Level CurrentLevel;

    // denotes whether the game is updating
    public boolean isRunning = true;

    // display for drawing things to the screen
    private Display display;

    private double fps;

    public GameInstance()
    {
        // set the singleton to this instance
        CurrentGameInst = this;

        // load all initial assets
        AssetManager.GetAssetManager();

        // generates a new display
        display = new Display();

        // run the current level
        OpenLevel(MainLevel.class);
    }

    // opens a level
    public void OpenLevel(Class<?> LevelClass)
    {
        // destroy the current level
        if (CurrentLevel != null)
        {
            CurrentLevel.Destroy();
        }

        // spawn the level
        Level newLevel = null;
        try
        {
            newLevel = (Level)LevelClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (newLevel != null)
        {
            // assign the current level
            CurrentLevel = newLevel;

            // start the level
            newLevel.StartLevel();
        }
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

    public double GetFps()
    {
        return fps;
    }

    // this is the beating heart of the game
    public void GameLoop()
    {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        double lastFpsTime = 0;

        // keep looping round til the game ends
        while (isRunning)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            System.out.println("delta: " + delta);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            Tick((float)delta);

            Render();

            try{
                Thread.sleep( (System.nanoTime() - lastLoopTime + OPTIMAL_TIME) / 1000000000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
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
