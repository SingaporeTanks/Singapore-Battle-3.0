package game;
import display.Display;
import game.Game;
import graphics.ResourceLoader;
import graphics.Sound;
import objects.Bullet;
import objects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();


        if (keyCode == KeyEvent.VK_SPACE) {
            Sound.playSound("res/audio/fire.wav").join();
            Bullet.shooting = true;
            Player.shooting = true;
        }
        if (keyCode == KeyEvent.VK_UP) {
            Player.goingUp = true;
            Sound.playSound("res/audio/boom.wav").join();
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            Player.goingDown = true;
            Sound.playSound("res/audio/boom.wav").join();
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            Player.goingLeft = true;
            Sound.playSound("res/audio/boom.wav").join();
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            Player.goingRight = true;
            Sound.playSound("res/audio/boom.wav").join();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Player.goingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Player.goingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Player.goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Player.goingRight = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            Bullet.shooting = false;
        }
    }
}