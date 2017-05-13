package Core;

import java.awt.event.KeyEvent;

/**
 * Created by Jaden on 5/13/2017.
 */
public interface InputEvent {

    void KeyPressed(KeyEvent e);
    void KeyReleased(KeyEvent e);
    void KeyTyped(KeyEvent e);

}
