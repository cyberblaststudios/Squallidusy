package Entities;

import EntityComponents.EntityComponent;
import Utils.Vector2D;
import com.sun.javafx.animation.transition.Position2D;

import java.util.ArrayList;

/**
 * Created by jevanger on 5/12/2017.
 */
public abstract class Entity {

    // stores all of the components
    public ArrayList<EntityComponent> Components = new ArrayList<EntityComponent>();

    public Entity Owner;

    // todo get a good Vector class ready

    protected Vector2D CurrentPosition;

    // tells when the Entity has intialized the component
    public void OnComponentInitialized(Entity owner)
    {
        Owner = owner;
    }
}
