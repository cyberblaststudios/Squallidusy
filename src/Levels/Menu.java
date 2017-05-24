package Levels;

import Core.GameInstance;
import Core.InputEvent;
import Core.InputManager;
import Entities.UI.MainMenuUI;
import Utils.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jaden on 5/23/2017.
 */
public class Menu extends Level {

    boolean continued = false;

    public void StartLevel()
    {
        try {
            SpawnEntity(MainMenuUI.class, new Vector2D(0,0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        InputManager.GetInputManager().OnInputEvent(new InputEvent() {
            public void KeyPressed(KeyEvent e) {
                if (continued) return;

                // advance to the next screen
                GameInstance.GetGameInstance().OpenLevel(MainLevel.class);

                continued = true;
            }

            public void KeyReleased(KeyEvent e) {

            }

            public void KeyTyped(KeyEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }
        });
    }

    public void Destroy()
    {

    }

}
