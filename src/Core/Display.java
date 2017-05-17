package Core;

import Rendering.RenderItem;
import Rendering.Renderable;
import Utils.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jevanger on 2/6/2017.
 */
public class Display extends Canvas{

    // graphics object
    private Graphics2D graphics;

    // our game window
    private JFrame frame;

    // this is the rendered resolution on the display, no matter the JFrame size
    public Vector2D ViewportSize = new Vector2D(1920, 1080);
    public Vector2D ScaledViewportSize = new Vector2D(1920, 1080);

    // this is the amount away from (0,0) that we render
    private Vector2D ViewportOffset = new Vector2D(0.0f, 0.0f);

    // render buckets
    private ArrayList<ArrayList<RenderItem>> RenderBucketList = new ArrayList<ArrayList<RenderItem>>();

    public Display()
    {
        // create the screen here
        frame = new JFrame("This amazing game");
        this.setMinimumSize(new Dimension((int)ViewportSize.X, (int)ViewportSize.Y));
        this.setPreferredSize(new Dimension((int)ViewportSize.X, (int)ViewportSize.Y));
        this.setMaximumSize(new Dimension((int)ViewportSize.X, (int)ViewportSize.Y));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.addKeyListener(InputManager.GetInputManager());
        this.requestFocus();
        // add the render buckets

        //UI_BUCKET
        RenderBucketList.add(new ArrayList<RenderItem>());
        //FOREGROUND_BUCKET
        RenderBucketList.add(new ArrayList<RenderItem>());
        //SCENERY_BUCKET
        RenderBucketList.add(new ArrayList<RenderItem>());
        //BACKGROUND_BUCKET
        RenderBucketList.add(new ArrayList<RenderItem>());
    }

    public void SetViewportScalePercentage(float percentage)
    {
        // finally set the scaling
        ScaledViewportSize = new Vector2D(ViewportSize.X * percentage, ViewportSize.Y * percentage);
    }

    public float GetViewportSizePercentage()
    {
        return ScaledViewportSize.X / ViewportSize.X;
    }

    public void SortRenderBucket(int renderBucket)
    {
        if (renderBucket >= 0 && renderBucket < RenderBucketList.size())
        {
            Collections.sort(RenderBucketList.get(renderBucket));
        }
    }

    public void AddItemToRenderQueue(Renderable renderItem)
    {
        int renderBucket = renderItem.GetRenderBucket();

        int priority = renderItem.GetRenderPriority();

        if (renderBucket >= 0 && renderBucket < RenderBucketList.size())
        {
            RenderBucketList.get(renderBucket).add(new RenderItem(renderItem, priority));

            // now we should sort the bucket
            SortRenderBucket(renderBucket);
        }
    }

    public void RemoveItemFromRenderQueue(Renderable renderItem)
    {
        int renderBucket = renderItem.GetRenderBucket();

        if (renderBucket >= 0 && renderBucket < RenderBucketList.size())
        {
            RenderBucketList.get(renderBucket).remove(renderItem);

            // now we should sort the bucket
            SortRenderBucket(renderBucket);
        }
    }

    public void SetViewportOffset(Vector2D newOffset)
    {
        ViewportOffset = newOffset;
    }

    public Vector2D GetViewportOffset()
    {
        return ViewportOffset;
    }

    public void DrawToScreen()
    {
        if (getBufferStrategy() == null)
        {
            // double buffer setup
            createBufferStrategy(2);
        }

        // gets the graphics object
        graphics = (Graphics2D)getBufferStrategy().getDrawGraphics();

        // update viewport size
        ViewportSize.X = frame.getWidth();
        ViewportSize.Y = frame.getHeight();

        // clear the image for the next frame
        graphics.clearRect(0, 0, (int)ViewportSize.X, (int)ViewportSize.Y);

        // make sure the scaling does not go off center
        graphics.translate(ViewportSize.X / 2, ViewportSize.Y / 2);
        graphics.scale(ViewportSize.X / ScaledViewportSize.X, ViewportSize.Y / ScaledViewportSize.Y);

        // allow the viewport to shift
        graphics.translate(ViewportOffset.X, ViewportOffset.Y);

        // render the buckets here
        for (ArrayList<RenderItem> list : RenderBucketList)
        {
            for (RenderItem item : list)
            {
                // render each item
                if (item.renderObject != null)
                {
                    item.renderObject.Render(graphics);
                }
            }
        }

        graphics.dispose();
        getBufferStrategy().show();
    }
}
