import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by jevanger on 2/6/2017.
 */
public class Display extends Canvas{

    BufferedImage CurrentFrame;

    Graphics graphics;

    public final int HEIGHT = 480;

    public final int WIDTH = 640;

    public Display()
    {
        // create the screen here
        JFrame frame = new JFrame("This amazing game");

        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(this);
    }

    public void DrawToFrameFromBuffer()
    {

    }

    public void DrawToBuffer()
    {
        if (getBufferStrategy() == null)
        {
            createBufferStrategy(2);
            graphics = getBufferStrategy().getDrawGraphics();

        }

        getBufferStrategy().show();
    }

    // purge the buffer AFTER drawing to screen
    public void ClearBuffer()
    {
        // flushes the current buffer
        CurrentFrame.flush();
    }

}
