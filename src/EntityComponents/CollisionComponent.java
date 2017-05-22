package EntityComponents;

import Entities.Entity;
import Utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by jaden_evanger on 5/22/2017.
 */
public class CollisionComponent extends EntityComponent {

    public Rectangle Box;

    private int Width;
    private int Height;

    public CollisionComponent(Entity inOwner, Vector2D relativeLocation, int width, int height) {
        super(inOwner, relativeLocation);

        Width = width;
        Height = height;

        // register this to the global pool of collision components
        Owner.GetLevel().CollisionComps.add(this);
    }

    // returns entities hit
    public ArrayList<Entity> CollisionCheck()
    {
        ArrayList<Entity> hits = new ArrayList<Entity>();

        for (CollisionComponent comp : Owner.GetLevel().CollisionComps)
        {
            if (comp != null && comp != this)
            {
                if (comp.Box.intersects(this.Box))
                {
                    // add the entity if it was hit
                    hits.add(comp.Owner);
                }
            }
        }

        return hits;
    }

    public ArrayList<Entity> CheckMove(Vector2D movementDirection, float moveDist)
    {
        Vector2D initPosition = GetWorldLocation();

        // test move the component
        SetWorldLocation(movementDirection.scale(moveDist));

        ArrayList<Entity> hits = CollisionCheck();

        return hits;
    }

    public void ComponentTick(float DeltaTime)
    {
        super.ComponentTick(DeltaTime);

        // set the box position
        Box.setSize(Width, Height);
        Box.setLocation((int)GetWorldLocation().X, (int)GetWorldLocation().Y);
    }
}
