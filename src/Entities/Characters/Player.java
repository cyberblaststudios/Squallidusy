package Entities.Characters;

import Core.InputEvent;
import Core.InputManager;
import Levels.Level;
import Utils.Vector2D;

import java.awt.event.KeyEvent;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Player extends Character{

    public Player(Vector2D Position, Level level) {
        super(Position, level);

        // bind the input
        InputManager.GetInputManager().OnInputEvent(new InputEvent() {
            public void KeyPressed(KeyEvent e) {
                
            }

            public void KeyReleased(KeyEvent e) {

            }

            public void KeyTyped(KeyEvent e) {

            }
        });
    }
}
