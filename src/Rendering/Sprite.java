package Rendering;

import Core.AssetManager;
import Utils.Vector2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Sprite {

    private BufferedImage SpriteImage;
    private Vector2D SpritePosition;

    // loads immage from asset ID
    public Sprite(String assetID, Vector2D spritePosition)
    {
        // set the image to apply to sprite
        SetImageAssetID(assetID);

        SetPosition(spritePosition);
    }

    // Position is in world units
    public void SetPosition(Vector2D Position)
    {
        SpritePosition = Position;
    }

    public Vector2D SetPosition()
    {
        return SpritePosition;
    }

    public void SetImageAssetID(String assetID)
    {
        AssetManager assetManager = AssetManager.GetAssetManager();

        // get the image from the
        SpriteImage = assetManager.GetImageAsset(assetID);
    }

    public void Render(Graphics2D g)
    {
        g.drawImage(SpriteImage, (int)SpritePosition.X, (int)SpritePosition.Y, new ImageObserver() {
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return true;
            }
        });
    }
}
