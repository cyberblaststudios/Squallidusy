package Entities;

import EntityComponents.EntityComponent;
import Levels.Level;
import Utils.Vector2D;
import java.util.ArrayList;

/**
 * Created by jevanger on 5/12/2017.
 */
public abstract class Entity {

    // stores all of the components
    public ArrayList<EntityComponent> Components = new ArrayList<EntityComponent>();

    // the current level that this Entity lives in
    private Level CurrentLevel;

    public Level GetLevel()
    {
        return CurrentLevel;
    }

    public Entity(Vector2D Position, Level level)
    {
        // set the location
        SetCurrentLocation(Position);

        // set the current level
        CurrentLevel = level;
    }

    public void Tick(float DeltaTime)
    {
        // called every frame
        for (EntityComponent comp : Components)
        {
            // call the tick on each one
            comp.ComponentTick(DeltaTime);
        }
    }

    public void Destroy()
    {
        // destroy all the components
        for (int i = Components.size() - 1; i >= 0; i--)
        {
            // destroy each component
            Components.get(i).Destroy();
        }

        // remove self from list in level
        GetLevel().WorldEntities.remove(this);
    }

    // todo get a good Vector class read
    private Vector2D CurrentLocation;

    public Vector2D GetCurrentLocation()
    {
        return CurrentLocation;
    }

    public void SetCurrentLocation(Vector2D newLoc)
    {
        CurrentLocation = newLoc;
    }
}
