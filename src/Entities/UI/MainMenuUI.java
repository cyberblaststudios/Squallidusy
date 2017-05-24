package Entities.UI;

import Core.GameInstance;
import Entities.Entity;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Rendering.Sprite;
import Utils.Vector2D;

/**
 * Created by Jaden on 5/23/2017.
 */
public class MainMenuUI extends Entity{

    public MainMenuUI(Vector2D Position, Level level) {
        super(Position, level);

        SpriteComponent spr = new SpriteComponent(this, new Vector2D(0,0), "MAINMENU_UI", RenderBuckets.UI_BUCKET, 1000);

        GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(new Vector2D(-(1920 / 2), -(1080 / 2)));
    }
}
