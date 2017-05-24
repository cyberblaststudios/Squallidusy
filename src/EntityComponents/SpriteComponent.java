package EntityComponents;

import Core.GameInstance;
import Entities.Entity;
import Rendering.Renderable;
import Rendering.Sprite;
import Utils.Vector2D;

import java.awt.*;

/**
 * Created by Jaden on 5/12/2017.
 */
public class SpriteComponent extends EntityComponent implements Renderable{

    private Sprite CurrentSprite;

    private int RenderBucket;
    private int RenderPriority;

    public SpriteComponent(Entity inOwner, Vector2D relativeLocation, String imgAssetID, int renderBucket, int renderPriority)
    {
        super(inOwner, relativeLocation);

        RenderBucket = renderBucket;

        RenderPriority = renderPriority;

        // add this component to the render queue
        GameInstance.GetGameInstance().GetDisplay().AddItemToRenderQueue(this);

        // create the sprite
        CurrentSprite = new Sprite(imgAssetID, GetWorldLocation());
    }

    public void SetImageAssetID(String AssetID)
    {
        CurrentSprite.SetImageAssetID(AssetID);
    }

    public void SetXFlipped(boolean XFlipped)
    {
        CurrentSprite.setXFlipped(XFlipped);
    }

    public void SetYFlipped(boolean YFlipped)
    {
        CurrentSprite.setYFlipped(YFlipped);
    }

    @Override
    public void Destroy()
    {
        super.Destroy();

        // remove ourselves form the rendering queue when we are destroyed
        GameInstance.GetGameInstance().GetDisplay().RemoveItemFromRenderQueue(this);
    }

    public void Render(Graphics2D g)
    {
        // set the proper location before it gets drawn to the screen
        CurrentSprite.SetPosition(GetWorldLocation());

        // allow our sprite object to render
        CurrentSprite.Render(g);
    }

    public int GetRenderBucket()
    {
        return RenderBucket;
    }

    public int GetRenderPriority()
    {
        return RenderPriority;
    }

    public void SetRenderPriority(int renderPriority)
    {
        RenderPriority = renderPriority;

        // tells the renderer to re-sort the render bucket that this sprite is in
        GameInstance.GetGameInstance().GetDisplay().SortRenderBucket(RenderBucket);
    }

    public void SetRenderBucket(int renderBucket)
    {
        RenderBucket = renderBucket;

        // tells the renderer to re-sort the render bucket that this sprite is in
        GameInstance.GetGameInstance().GetDisplay().SortRenderBucket(RenderBucket);
    }
}
