package Entities;

import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;

/**
 * Created by cman1 on 5/23/2017.
 */
public class MapEntity extends Entity {

    public MapEntity(Vector2D Position, Level level) {
        super(Position, level);
        SpriteComponent map = new SpriteComponent(this, new Vector2D(0,0), "map", RenderBuckets.BACKGROUND_BUCKET, 10000);
    }
}
