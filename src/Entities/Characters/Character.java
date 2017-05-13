package Entities.Characters;

import Entities.Entity;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Character extends Entity {

    private SpriteComponent CharacterSprite = null;

    public Character(Vector2D Position, Level level)
    {
        super(Position, level);

        // create the characterSprite
        CharacterSprite = new SpriteComponent(this, new Vector2D(0.0f, 0.0f), "image1", RenderBuckets.FOREGROUND_BUCKET, 100);
    }

    public SpriteComponent GetCharacterSpriteComponent()
    {
        return CharacterSprite;
    }
}
