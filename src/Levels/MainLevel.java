package Levels;

import Core.Display;
import Core.GameInstance;
import Core.InputManager;
import Entities.Characters.Mob;
import Entities.Characters.Player;
import Utils.Vector2D;
import Entities.MapEntity;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by Jaden on 5/12/2017.
 */
public class MainLevel extends Level {

    float panspeed = 100.0f;

    public void StartLevel()
    {
        // spawn the entities here
        try {

            Display dis = GameInstance.GetGameInstance().GetDisplay();
            SpawnEntity(MapEntity.class, new Vector2D(0, 0));
            SpawnEntity(Player.class, new Vector2D(0,0));
            SpawnEntity(Mob.class, new Vector2D(200,200));
        } catch (Exception e) {
            e.printStackTrace();
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
