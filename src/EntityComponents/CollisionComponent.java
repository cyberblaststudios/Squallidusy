package EntityComponents;

import Core.GameInstance;
import Entities.Entity;
import Utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by jaden_evanger on 5/22/2017.
 */
public class CollisionComponent extends EntityComponent {

    public Rectangle Box = new Rectangle();

    private int Width;
    private int Height;

    public CollisionComponent(Entity inOwner, Vector2D relativeLocation, int width, int height) {
        super(inOwner, relativeLocation);

        Width = width;
        Height = height;

        Box.setBounds((int)GetWorldLocation().X, (int)GetWorldLocation().Y, width, height);

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

    public ArrayList<Entity> CheckMove(Vector2D movementDirection)
    {
        Vector2D initPosition = GetWorldLocation();

        // test move the component
        Box.x = (int)initPosition.add(movementDirection).X;
        Box.y = (int)initPosition.add(movementDirection).Y;

        // actually check for hits
        ArrayList<Entity> hits = CollisionCheck();

        Box.x = (int)GetWorldLocation().X;
        Box.y = (int)GetWorldLocation().Y;

        return hits;
    }

    public void ComponentTick(float DeltaTime)
    {
        super.ComponentTick(DeltaTime);

        Box.x = (int)GetWorldLocation().X;
        Box.y = (int)GetWorldLocation().Y;
    }
}
