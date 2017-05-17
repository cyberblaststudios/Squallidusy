package Entities.World;

import Entities.Entity;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;

/**
 * Created by Jaden on 5/16/2017.
 */
public class FloorTile extends Entity{

    public FloorTile(Vector2D Position, Level level) {
        super(Position, level);

        SpriteComponent sprite = new SpriteComponent(this, new Vector2D(0,0), "FT_GRASS", RenderBuckets.SCENERY_BUCKET, 0);

        System.out.println(Position.X + " " + Position.Y);
    }

}
