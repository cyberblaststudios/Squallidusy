package Levels;

import Core.Display;
import Core.GameInstance;
import Core.InputManager;
import Entities.Characters.Mob;
import Entities.Characters.Player;
import Entities.World.FloorTile;
import Utils.Vector2D;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jaden on 5/12/2017.
 */
public class MainLevel extends Level {

    float panspeed = 100.0f;

    public void StartLevel() {
        // spawn the entities here
        try {

            Display dis = GameInstance.GetGameInstance().GetDisplay();

            SpawnEntity(Player.class, new Vector2D(0, 0));
            SpawnEntity(Mob.class, new Vector2D(200, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // recenter the viewport i guess
        GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(new Vector2D(0, 0));
        GameInstance.GetGameInstance().GetDisplay().SetViewportScalePercentage(.4f);

        // spawn a bunch of tiles
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                try {
                    SpawnEntity(FloorTile.class, new Vector2D(x * 8, y * 8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void LevelTick(float DeltaTime)
    {
        super.LevelTick(DeltaTime);

        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_PAGE_UP))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportScalePercentage(GameInstance.GetGameInstance().GetDisplay().GetViewportSizePercentage() + 5f * DeltaTime);
        }else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_PAGE_DOWN))
        {
            GameInstance.GetGameInstance().GetDisplay().SetViewportScalePercentage(GameInstance.GetGameInstance().GetDisplay().GetViewportSizePercentage() - 5f * DeltaTime);
        }
    }
}
