import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

/*************************************
 .....Class   × KeyManager
 .....Created × 23.02.2020
 .....Author  × N~ @ Leon Diedrichs
 *************************************/

public class KeyManager implements KeyListener
{
    private static class KeyEventPair
    {
        private final int KEYCODE;
        private final int ACTION;

        public KeyEventPair(int keycode, int action)
        {
            this.KEYCODE = keycode;
            this.ACTION = action;
        }
    }

    private final static Queue<KeyEventPair> EVENTQUEUE = new LinkedList<>();
    private final static int[] KEYSTATE = new int[120 + 1];

    public static final int FREE = 0;
    public static final int PRESSED = 1;
    public static final int DOWN = 2;
    public static final int RELEASED = 3;

    public static void update()
    {
        for (int i = 0; i < KEYSTATE.length; i++)
        {
            if (KEYSTATE[i] == PRESSED)
                KEYSTATE[i] = DOWN;
            else if (KEYSTATE[i] == RELEASED)
                KEYSTATE[i] = FREE;
        }

        KeyEventPair e = EVENTQUEUE.poll();

        if (e != null)
        {
            if (e.ACTION == PRESSED && KEYSTATE[e.KEYCODE] != DOWN)
                KEYSTATE[e.KEYCODE] = PRESSED;
            else if (e.ACTION == RELEASED)
                KEYSTATE[e.KEYCODE] = RELEASED;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() <= 120)
            EVENTQUEUE.add(new KeyEventPair(e.getKeyCode(), PRESSED));
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() <= 120)
            EVENTQUEUE.add(new KeyEventPair(e.getKeyCode(), RELEASED));
    }

    public static int getKey(int keycode)
    {
        return KEYSTATE[keycode];
    }

    public static int getKey(char c)
    {
        return getKey(KeyStroke.getKeyStroke(c, 0).getKeyCode());
    }
}
