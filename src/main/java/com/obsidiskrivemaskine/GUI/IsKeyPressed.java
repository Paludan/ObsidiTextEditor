package com.obsidiskrivemaskine.GUI;

/**
 * Created by Lee on 11-04-2016.
 */
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    private static boolean isPressed = false;
    private static char pressedKey;

    public  IsKeyPressed ()
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher()
        {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
            synchronized (IsKeyPressed.class) {

                pressedKey = ke.getKeyChar();
                isPressed = true;
                return false;
                }
            }
        });
    }

    public static boolean isAnyKeyPressed()
    {
        synchronized (IsKeyPressed.class)
        {
            return isPressed;
        }
    }

    public static char returnPressedKey ()
    {
        return pressedKey;
    }
}