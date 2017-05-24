package Entities.Projectiles;

import Entities.Entity;
import EntityComponents.CollisionComponent;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;

/**
 * Created by jevanger on 5/18/2017.
 */
public class Blast extends Entity{

    private float timeout = 5.0f;
    private float Speed = 0;

    private boolean isActive = false;

    private Vector2D Direction;

    public CollisionComponent collision;

    public Blast(Vector2D Position, Level level) {
        super(Position, level);

        SpriteComponent sprite = new SpriteComponent(this, new Vector2D(0,0), "BLAST_01", RenderBuckets.FOREGROUND_BUCKET, 10000);



        collision = new CollisionComponent(this, new Vector2D(0,0), 16, 13);
    }

    public void Initialize(Vector2D direction, float speed)
    {
        Speed = speed;

        isActive = true;

        Direction = direction;
    }

    public void Tick(float DeltaTime)
    {
        super.Tick(DeltaTime);

        SetCurrentLocation(GetCurrentLocation().add(Direction.scale(Speed)));
        timeout -= DeltaTime;
        if(timeout <= 0) {
            Destroy();
        }
    }
}
