package Entities.Characters;

import Core.GameInstance;
import Levels.Level;
import Utils.Vector2D;



/**
 * Created by Jonah on 5/16/2017.
 */
public class Mob extends Character{

    float MovementSpeed = 10.0f;

    public Mob(Vector2D Position, Level level) {
        super(Position, level);

    }

    public void Tick(float DeltaTime)
    {
        super.Tick(DeltaTime);

        int dir = (int)(Math.random() * 3);

        if (dir == 0)
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, -MovementSpeed * DeltaTime)));
        }
        else if(dir == 1)
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, MovementSpeed * DeltaTime)));
        }
        if (dir == 2)
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
        }
        else
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
        }
    }
}
