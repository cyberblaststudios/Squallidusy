package Rendering;

import Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jaden on 5/12/2017.
 */
public interface Renderable {

    // passes the graphics object to the render target
    void Render(Graphics2D g);

    // gets the index of the render bucket that this will be rendered in
    int GetRenderBucket();

    // gets the render priority of the bucket that this will be rendered in
    int GetRenderPriority();
}
