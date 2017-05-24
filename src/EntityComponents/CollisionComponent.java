package EntityComponents;

import Core.GameInstance;
import Entities.Entity;
import Utils.CollisionCheckResult;
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
    public CollisionCheckResult CollisionCheck(boolean adjustParentLocation)
    {
        ArrayList<Entity> hits = new ArrayList<Entity>();

        Vector2D correctionVector = new Vector2D(0,0);

        for (CollisionComponent comp : Owner.GetLevel().CollisionComps)
        {
            if (comp != null && comp != this)
            {
                if (comp.Box.intersects(this.Box))
                {
                    // add the entity if it was hit
                    hits.add(comp.Owner);

                    // adjust the parent entity if nessesary
                    if (adjustParentLocation) {

                        Rectangle intersect = comp.Box.intersection(this.Box);

                        // the amount
                        Vector2D correctionMove = new Vector2D(0, 0);

                        if (intersect.width != 0 && intersect.height != 0)
                        {
                            correctionMove.X = intersect.width;
                            correctionMove.Y = intersect.height;
                        }

                        // add the correction to the master correction vector
                        correctionVector = correctionVector.add(correctionMove);
                    }
                }
            }
        }

        return new CollisionCheckResult(correctionVector, hits);
    }

    public CollisionCheckResult CheckMove(Vector2D movementDirection, boolean adjustParentLocation)
    {
        Vector2D initPosition = GetWorldLocation();

        // test move the component
        Box.x = (int)initPosition.add(movementDirection).X;
        Box.y = (int)initPosition.add(movementDirection).Y;

        // actually check for hits
        CollisionCheckResult result = CollisionCheck(adjustParentLocation);

        Box.x = (int) GetWorldLocation().X;
        Box.y = (int) GetWorldLocation().Y;

        return result;
    }

    public void ComponentTick(float DeltaTime)
    {
        super.ComponentTick(DeltaTime);

        Box.x = (int)GetWorldLocation().X;
        Box.y = (int)GetWorldLocation().Y;
    }

    public void Destroy()
    {
        super.Destroy();

        GameInstance.GetGameInstance().CurrentLevel.CollisionComps.remove(this);
    }
}
