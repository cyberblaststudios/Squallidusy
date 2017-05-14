package Levels;

import Core.GameInstance;
import Core.InputManager;
import Entities.Characters.Player;
import Utils.Vector2D;

import java.awt.event.KeyEvent;

/**
 * Created by Jaden on 5/12/2017.
 */
public class MainLevel extends Level {

    float panspeed = 20;

    public void StartLevel()
    {
        // spawn the entities here
        try {
            for (int i = 0; i < 2000; i++) {
                SpawnEntity(Player.class, new Vector2D(0.0f, 0.0f));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LevelTick(float DeltaTime)
    {
        super.LevelTick(DeltaTime);

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
