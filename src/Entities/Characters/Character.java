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

    public SpriteComponent CharacterSprite = null;

    public Character(Vector2D Position, Level level) {
        super(Position, level);

    }

    public SpriteComponent GetCharacterSpriteComponent()
    {
        return CharacterSprite;
    }
}
