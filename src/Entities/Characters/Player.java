package Entities.Characters;

import Core.GameInstance;
import Core.InputEvent;
import Core.InputManager;
import Entities.Projectiles.Blast;
import EntityComponents.CollisionComponent;
import EntityComponents.SpriteComponent;
import Levels.Level;
import Rendering.RenderBuckets;
import Utils.CollisionCheckResult;
import Utils.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Player extends Character{

    float MovementSpeed = 600.0f;

    public CollisionComponent collision;

    public Player(Vector2D Position, Level level)
    {
        super(Position, level);

        // create the characterSprite
        CharacterSprite = new SpriteComponent(this, new Vector2D(0.0f, 0.0f), "warrior_still", RenderBuckets.FOREGROUND_BUCKET, 100);

        collision = new CollisionComponent(this, new Vector2D(0,0), 21, 24);

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

            public void mouseClicked(MouseEvent e) {

                Vector2D mousePos = new Vector2D(e.getX(), e.getY());

                Vector2D screenCenter = new Vector2D(GameInstance.GetGameInstance().GetDisplay().frame.getWidth() / 2, GameInstance.GetGameInstance().GetDisplay().getHeight() / 2);

                Vector2D shootDir = mousePos.sub(screenCenter).normalize();

                Blast bl = null;

                try {
                    bl = (Blast) GetLevel().SpawnEntity(Blast.class, GetCurrentLocation());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                if (bl != null)
                {
                    bl.Initialize(shootDir, 2.0f);
                }
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }
        });
    }

    public void Tick(float DeltaTime)
    {
        super.Tick(DeltaTime);

        if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_UP))
        {
            CollisionCheckResult result = collision.CheckMove(new Vector2D(0, -MovementSpeed * DeltaTime), true);

            // compensate for the collision
            SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));

            if (result.HitEntities.size() == 0) {

                SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, -MovementSpeed * DeltaTime)));
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(0, MovementSpeed * DeltaTime)));

            }
            else
            {
                // compensate for the collision
                SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(result.CorrectionMove));
            }
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_DOWN))
        {
            CollisionCheckResult result = collision.CheckMove(new Vector2D(0, MovementSpeed * DeltaTime), true);

            // compensate for the collision
            SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));

            if (result.HitEntities.size() == 0) {

                SetCurrentLocation(GetCurrentLocation().add(new Vector2D(0, MovementSpeed * DeltaTime)));
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(0, -MovementSpeed * DeltaTime)));

            }
            else
            {
                // compensate for the collision
                SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(result.CorrectionMove));
            }
        }
        else if (InputManager.GetInputManager().isKeyDown(KeyEvent.VK_LEFT))
        {
            CollisionCheckResult result = collision.CheckMove(new Vector2D(-MovementSpeed * DeltaTime, 0), true);

            // compensate for the collision
            SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));

            if (result.HitEntities.size() == 0) {

                SetCurrentLocation(GetCurrentLocation().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
                CharacterSprite.SetXFlipped(true);
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
            }
            else
            {
                // compensate for the collision
                SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(result.CorrectionMove));
            }
        }
        else if(InputManager.GetInputManager().isKeyDown(KeyEvent.VK_RIGHT))
        {
            CollisionCheckResult result = collision.CheckMove(new Vector2D(MovementSpeed * DeltaTime, 0), true);

            if (result.HitEntities.size() == 0) {

                SetCurrentLocation(GetCurrentLocation().add(new Vector2D(MovementSpeed * DeltaTime, 0)));
                CharacterSprite.SetXFlipped(false);
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(new Vector2D(-MovementSpeed * DeltaTime, 0)));
            }
            else
            {
                // compensate for the collision
                SetCurrentLocation(GetCurrentLocation().add(result.CorrectionMove));
                GameInstance.GetGameInstance().GetDisplay().SetViewportOffset(GameInstance.GetGameInstance().GetDisplay().GetViewportOffset().add(result.CorrectionMove));
            }
        }
    }
}
