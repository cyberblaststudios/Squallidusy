package Entities.Characters;

import Core.GameInstance;
import Core.InputEvent;
import Core.InputManager;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;

import java.awt.event.KeyEvent;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Player extends Character{

    float MovementSpeed = 10.0f;

    public Player(Vector2D Position, Level level)
    {
        super(Position, level);

        // create the characterSprite
        CharacterSprite = new SpriteComponent(this, new Vector2D(0.0f, 0.0f), "warrior_still", RenderBuckets.FOREGROUND_BUCKET, 100);

        InputManager.GetInputManager().OnInputEvent(new InputEvent() {
            public void KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
            }

            public void KeyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_W)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
                else if (e.getKeyCode() == KeyEvent.VK_A)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
                else if (e.getKeyCode() == KeyEvent.VK_D)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
            }

            public void KeyTyped(KeyEvent e) {}
        });
    }

    public void Tick(float DeltaTime)
    {
        super.Tick(DeltaTime);

        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_W))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, -MovementSpeed * DeltaTime)));
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_S))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, MovementSpeed * DeltaTime)));
        }
        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_A))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
            CharacterSprite.SetXFlipped(true);
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_D))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
            CharacterSprite.SetXFlipped(false);
        }
    }
}
