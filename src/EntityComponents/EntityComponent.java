package EntityComponents;

import Entities.Entity;

/**
 * Created by jevanger on 5/12/2017.
 */
public abstract class EntityComponent {

    public Entity Owner;

    public EntityComponent(Entity inOwner)
    {
        Owner = inOwner;
    }
}
