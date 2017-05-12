import java.awt.*;

/**
 * Created by jevanger on 2/6/2017.
 */
import java.awt.*;

public class GameInstance implements Runnable
{
    public boolean isRunning = true;

    final int TARGET_FPS = 60;
    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    public int fps = 0;

    public int lastFpsTime = 0;

    Display display;

    public GameInstance()
    {
        display = new Display();
    }

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
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            Tick(delta);

            // draw to screen
            Render();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try{
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
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

    }

    public void Render()
    {
        // Runs after the tick
        display.DrawToFrameFromBuffer();
    }

    @Override
    public void run() {
        // start the game loop
        GameLoop();

        // when the game loop stops, then the game thread returns and the game closes
    }
}
