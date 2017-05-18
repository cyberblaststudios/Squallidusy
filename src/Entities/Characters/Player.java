package Entities.Characters;

import Core.GameInstance;
import Core.InputEvent;
import Core.InputManager;
import Entities.Projectiles.Blast;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.Vector2D;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

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

                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    CharacterSprite.SetImageAssetID("warrior_running");
                }
                else if (e.getKeyCode() == KeyEvent.VK_R) {

                    Blast blast = null;

                    try {
                        blast = (Blast)GetLevel().SpawnEntity(Blast.class, GetCurrentLocation());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    if(blast != null)
                    {
                        blast.Initialize(new Vector2D(1,0), 10);
                    }
                }
            }

            public void KeyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    CharacterSprite.SetImageAssetID("warrior_still");
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
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

        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_UP))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, -MovementSpeed * DeltaTime)));
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_DOWN))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, MovementSpeed * DeltaTime)));
        }
        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_LEFT))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
            CharacterSprite.SetXFlipped(true);
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_RIGHT))
        {
            SetCurrentLocation(GetCurrentLocation().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
            CharacterSprite.SetXFlipped(false);
        }
    }
}
