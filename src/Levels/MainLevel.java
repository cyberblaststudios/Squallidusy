package Levels;

import Core.Display;
import Core.GameInstance;
import Core.InputManager;
import Entities.Characters.Player;
import Entities.World.FloorTile;
import Utils.Vector2D;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jaden on 5/12/2017.
 */
public class MainLevel extends Level {

    float panspeed = 10;

    public void StartLevel()
    {
        // spawn the entities here
        try {

            Display dis = GameInstance.GetGameInstance().GetDisplay();

            SpawnEntity(Player.class, new Vector2D(0,0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // spawn a bunch of tiles
        for(int x = 0; x < 100; x++)
        {
            for(int y = 0; y < 100; y++)
            {
                try {
                    SpawnEntity(FloorTile.class, new Vector2D(x * 8, y * 8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //GameInstance.GetGameInstance().GetDisplay().SetViewportScalePercentage(40);
    }

    public void LevelTick(float DeltaTime)
    {
        super.LevelTick(DeltaTime);

        // viewport controls
        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_UP))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(0, panspeed * DeltaTime)));
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_DOWN))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(0, -panspeed * DeltaTime)));
        }
        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_LEFT))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(panspeed * DeltaTime, 0)));
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_RIGHT))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(-panspeed * DeltaTime, 0)));
        }

        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_PAGE_UP))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportScalePercentage(GameInstance.GetGameInstance().GetDisplay().GetViewportSizePercentage() + .004f * DeltaTime);
        }else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_PAGE_DOWN))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportScalePercentage(GameInstance.GetGameInstance().GetDisplay().GetViewportSizePercentage() - .004f * DeltaTime);
        }
    }
}
