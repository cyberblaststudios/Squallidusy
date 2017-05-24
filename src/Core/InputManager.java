package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jaden on 5/13/2017.
 */
public class InputManager implements KeyListener, MouseListener{

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

    // keeps track of pressed keys
    ArrayList<Integer> Pressedkeys = new ArrayList<Integer>();

    public void OnInputEvent(InputEvent e)
    {
        Subscribers.add(e);
    }

    public void keyTyped(KeyEvent e) {

        for (int i = Subscribers.size() - 1; i >= 0; i--)
        {
            Subscribers.get(i).KeyTyped(e);
        }
    }

    public void keyPressed(KeyEvent e) {

        System.out.println("oh yes baby");

        if (!isKeyDown(e.getKeyCode())) {
            Pressedkeys.add(new Integer(e.getKeyCode()));
        }

        for (int i = Subscribers.size() - 1; i >= 0; i--)
        {
            Subscribers.get(i).KeyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {

        // removes the items
        for (int i = Pressedkeys.size() - 1; i >= 0; i--)
        {
            if (Pressedkeys.get(i).intValue() == e.getKeyCode())
            {
                Pressedkeys.remove(i);
            }
        }

        for (int i = Subscribers.size() - 1; i >= 0; i--)
        {
            Subscribers.get(i).KeyReleased(e);
        }
    }

    // gets whether a key is pressed down or not
    public boolean isKeyDown(int keyCode)
    {
        boolean isFound = false;
        for (Integer i : Pressedkeys)
        {
            if (i.intValue() == keyCode)
            {
                isFound = true;
                break;
            }
        }

        return isFound;
    }

    public void mouseClicked(MouseEvent e) {

        for (int i = Subscribers.size() - 1; i >= 0; i--)
        {
            Subscribers.get(i).mouseClicked(e);
        }

    }

    public void mousePressed(MouseEvent e) {

        for (int i = Subscribers.size() - 1; i >= 0; i--)
        {
            Subscribers.get(i).mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {

        for (int i = Subscribers.size() - 1; i >= 0; i--)
        {
            Subscribers.get(i).mouseReleased(e);
        }

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
