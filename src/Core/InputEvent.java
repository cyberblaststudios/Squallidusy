package Core;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Jaden on 5/13/2017.
 */
public interface InputEvent {

    void KeyPressed(KeyEvent e);
    void KeyReleased(KeyEvent e);
    void KeyTyped(KeyEvent e);

    void mouseClicked(MouseEvent e);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
}
