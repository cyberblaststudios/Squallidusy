package Core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Jaden on 5/13/2017.
 */
public class InputManager implements KeyListener{

    private static InputManager CurrentInputManager;

    public static InputManager GetInputManager()
    {
        if (CurrentInputManager != null)
        {
            return CurrentInputManager;
        }

        CurrentInputManager = new InputManager();

        return CurrentInputManager;
    }

    ArrayList<InputEvent> Subscribers = new ArrayList<InputEvent>();

    public void OnInputEvent(InputEvent e)
    {
        Subscribers.add(e);
    }

    public void keyTyped(KeyEvent e) {
        for (InputEvent event : Subscribers)
        {
            event.KeyTyped(e);
        }
    }

    public void keyPressed(KeyEvent e) {

        for (InputEvent event : Subscribers)
        {
            event.KeyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {
        for (InputEvent event : Subscribers)
        {
            event.KeyReleased(e);
        }
    }
}
