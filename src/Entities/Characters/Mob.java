package Entities.Characters;

import Core.GameInstance;
import EntityComponents.SpriteComponent;
import EntityComponents.CollisionComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;



/**
 * Created by Jonah on 5/16/2017.
 */
public class Mob extends Character{

    float MovementSpeed = 10.0f;

    public CollisionComponent collision;

    public Mob(Vector2D Position, Level level) {
        super(Position, level);

        CharacterSprite = new SpriteComponent(this, new Vector2D(0,0), "MOB_01", RenderBuckets.FOREGROUND_BUCKET, 100);

        collision = new CollisionComponent(this, new Vector2D(0,0), 100, 100);
    }

    public void Tick(float DeltaTime)
    {
        super.Tick(DeltaTime);

        /*
        int dir = (int)(Math.random() * 3);

        if (dir == 0)
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, -MovementSpeed * DeltaTime)));
        }
        else if(dir == 1)
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, MovementSpeed * DeltaTime)));
        }
        if (dir == 2)
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
        }
        else
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
        }

        */
    }
}