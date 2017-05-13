package Levels;

import Entities.Entity;
import Utils.Vector2D;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by jevanger on 5/12/2017.
 */
public abstract class Level {

    // keeps track of all the game entites in the world
    private ArrayList<Entity> WorldEntities = new ArrayList<Entity>();

    public void LevelTick(float DeltaTime)
    {
        for(Entity entity : WorldEntities)
        {

        }
    }

    // called when the level starts
    public void StartLevel()
    {
        // Called when level is created
    }

    // this will spawn a entity in the world at the world position indicated by SpawnLocation
    public Entity SpawnEntity(Class<Entity> SpawnClass, Vector2D SpawnLocation) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // we are now calling a reflected constructor
        Constructor<Entity> constructor = SpawnClass.getConstructor(Vector2D.class, Level.class);

        Entity newEntity = constructor.newInstance(SpawnLocation, this);

        // add it to the entity registry
        WorldEntities.add(newEntity);

        return newEntity;
    }
}
