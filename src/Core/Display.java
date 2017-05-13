package Core;

import Utils.Vector2D;
import javax.swing.*;
import java.awt.*;

/**
 * Created by jevanger on 2/6/2017.
 */
public class Display extends Canvas{

    // graphics object
    Graphics2D graphics;

    JFrame frame;

    public Vector2D ViewportSize = new Vector2D(1920, 1080);
    public Vector2D ScaledViewportSize = new Vector2D(640, 360);

    public Display()
    {
        // create the screen here
        frame = new JFrame("This amazing game");
        JPanel panel = (JPanel)frame.getContentPane();
        panel.setPreferredSize(new Dimension(ViewportSize.X, ViewportSize.Y));
        panel.setLayout(null);
        frame.setSize(new Dimension(ViewportSize.X, ViewportSize.Y));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        this.setIgnoreRepaint(true);
        this.setSize(new Dimension(ViewportSize.X, ViewportSize.Y));
        panel.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void DrawToScreen()
    {
        if (getBufferStrategy() == null)
        {
            // double buffer setup
            createBufferStrategy(2);

            // gets the graphics object
            graphics = (Graphics2D)getBufferStrategy().getDrawGraphics();
        }

        // update viewport size
        ViewportSize.X = frame.getWidth();
        ViewportSize.Y = frame.getHeight();

        // clear the image for the next frame
        graphics.clearRect(0, 0, ViewportSize.X, ViewportSize.Y);
        graphics.scale(ViewportSize.X / ScaledViewportSize.X, ViewportSize.Y / ScaledViewportSize.Y);

        AssetManager manager = AssetManager.GetAssetManager();

        getBufferStrategy().show();
        graphics.dispose();
    }
}
