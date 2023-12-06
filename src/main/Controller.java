package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    // Atribute
    Panel panel;
    int code;
    public boolean up, down, left, right, enter, space;

    // Constructor
    public Controller(Panel panel) {
        this.panel = panel;
    }

    // Method
    @Override
    public void keyTyped(KeyEvent k) {
        // nothing
    }
    @Override
    public void keyPressed(KeyEvent k) {
        code = k.getKeyCode();
        if (panel.state == 0) {
            resume(code);
        }
        else if (panel.state == 1) {
            play(code);
        }
    }
    @Override
    public void keyReleased(KeyEvent k) {
        code = k.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.up = false;
        }
        if (code == KeyEvent.VK_A) {
            this.left = false;
        }
        if(code == KeyEvent.VK_S)
        {
            this.down = false;
        }
        if(code == KeyEvent.VK_D)
        {
            this.right = false;
        }
        if(code == KeyEvent.VK_ENTER)
        {
            this.enter = false;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            this.space = false;
        }
    }

    // Control State
    public void play(int code) {
        if (code == KeyEvent.VK_W) {
            this.up = true;
        }
        if (code == KeyEvent.VK_A) {
            this.left = true;
        }
        if(code == KeyEvent.VK_S)
        {
            this.down = true;
        }
        if(code == KeyEvent.VK_D)
        {
            this.right = true;
        }
        if(code == KeyEvent.VK_ENTER)
        {
            this.enter = true;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            this.space = true;
        }
    }
    public void resume(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            this.panel.state = 1;
        }
    }
    public void dialogue(int code) {
        if (code == KeyEvent.VK_ENTER) {
            this.enter = true;
        }
    }
}
