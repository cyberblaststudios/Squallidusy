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
    public ArrayList<Entity> WorldEntities = new ArrayList<Entity>();

    public ArrayList<Entity> DestroyList = new ArrayList<Entity>();

    public void LevelTick(float DeltaTime)
    {
        for(Entity entity : WorldEntities)
        {
            // tick each of the Entities
            entity.Tick(DeltaTime);
        }

        for (int i = DestroyList.size() - 1; i >=0; i--)
        {
            DestroyList.get(i).Destroy();
        }
    }

    // called when the level starts
    public void StartLevel()
    {
        // Called when level is created
    }

    // this will spawn a entity in the world at the world position indicated by SpawnLocation
    public Entity SpawnEntity(Class<?> SpawnClass, Vector2D SpawnLocation) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        if (SpawnClass == null) return null;

        // we are now calling a reflected constructor
        Constructor<?> constructor = SpawnClass.getConstructor(Vector2D.class, Level.class);

        if (constructor == null) return null;

        Entity newEntity = (Entity)constructor.newInstance(SpawnLocation, this);

        if (newEntity != null)
        {
            // add it to the entity registry
            WorldEntities.add(newEntity);
        }

        return newEntity;
    }

    public void Destroy()
    {
        for (Entity entity : WorldEntities)
        {
            // destroy each entity
            entity.Destroy();
        }
    }
}
