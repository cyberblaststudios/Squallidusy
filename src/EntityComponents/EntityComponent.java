package EntityComponents;

import Entities.Entity;
import Utils.Vector2D;

/**
 * Created by jevanger on 5/12/2017.
 */
public abstract class EntityComponent {

    public Entity Owner;

    private Vector2D RelativeLocation = new Vector2D(0.0f, 0.0f);

    public EntityComponent(Entity inOwner, Vector2D relativeLocation)
    {
        Owner = inOwner;

        SetRelativeLocation(relativeLocation);
    }

    public void Destroy()
    {
        // called when this component is destroyed
    }

    public Vector2D GetWorldLocation()
    {
        // add the entity location to the relative to get the world position of this component
        return Owner.GetCurrentLocation().add(RelativeLocation);
    }

    public Vector2D GetRelativeLocation()
    {
        return RelativeLocation;
    }

    public void SetWorldLocation(Vector2D worldLocation)
    {
        SetRelativeLocation(GetWorldLocation().sub(worldLocation));
    }

    public void SetRelativeLocation(Vector2D relLocation)
    {
        RelativeLocation = relLocation;
    }
}
