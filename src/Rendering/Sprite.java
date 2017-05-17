package Rendering;

import Core.AssetManager;
import Core.Display;
import Core.GameInstance;
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

    private boolean XFlipped = false;
    private boolean YFlipped = false;

    // loads immage from asset ID
    public Sprite(String assetID, Vector2D spritePosition)
    {
        // set the image to apply to sprite
        SetImageAssetID(assetID);

        SetPosition(spritePosition);
    }

    public boolean isXFlipped() {
        return XFlipped;
    }

    public void setXFlipped(boolean XFlipped) {
        this.XFlipped = XFlipped;
    }

    public boolean isYFlipped() {
        return YFlipped;
    }

    public void setYFlipped(boolean YFlipped) {
        this.YFlipped = YFlipped;
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

    public void Render(Graphics2D g) {

        //Rectangle spriteBounds = new Rectangle((int) SpritePosition.X, (int) SpritePosition.Y, SpriteImage.getWidth(), SpriteImage.getHeight());

        //Display vp = GameInstance.GetGameInstance().GetDisplay();

        //Rectangle viewportBounds = new Rectangle((int) vp.GetViewportOffset().X, (int) vp.GetViewportOffset().Y, (int) vp.ScaledViewportSize.X, (int) vp.ScaledViewportSize.Y);

        // cull out any render targets that are not in view
        //if (viewportBounds.intersects(spriteBounds))
        //{
            int sx1 = XFlipped ? SpriteImage.getWidth() : 0;

            int sy1 = YFlipped ? SpriteImage.getHeight() : 0;

            int sx2 = XFlipped ? 0 : SpriteImage.getWidth();

            int sy2 = YFlipped ? 0 : SpriteImage.getHeight();

            // draw the image with flipping handled
            g.drawImage(SpriteImage, (int) SpritePosition.X, (int) SpritePosition.Y, (int) SpritePosition.X + SpriteImage.getWidth(), (int) SpritePosition.Y + SpriteImage.getHeight(), sx1, sy1, sx2, sy2, null);
       // }
    }
}
