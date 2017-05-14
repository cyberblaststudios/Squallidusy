package Entities.Characters;

import Core.GameInstance;
import Core.InputEvent;
import Core.InputManager;
import Levels.Level;
import Utils.Vector2D;

import java.awt.event.KeyEvent;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Player extends Character{

    float MovementSpeed = 10.0f;

    public Player(Vector2D Position, Level level) {
        super(Position, level);

    }

    public void Tick(float DeltaTime)
    {
        super.Tick(DeltaTime);

        /*
        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_UP))
        {
            System.out.println("youtuvbe");
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, -MovementSpeed * DeltaTime)));
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_DOWN))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, MovementSpeed * DeltaTime)));
        }
        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_LEFT))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_RIGHT))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
        }*/

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
