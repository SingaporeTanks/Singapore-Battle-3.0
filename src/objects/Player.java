package objects;

import game.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Machine {
    public static final int PLAYER_WIDTH = 53;
    public static final int PLAYER_HEIGHT = 53;

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;
    public static boolean shooting;
    public static int velocity;
    public Rectangle playerDimension;
    public Heading heading;

    public Player(int x, int y, Heading heading) {
        super(x, y, velocity);
        this.velocity = 3;
        this.goingUp = false;
        this.goingDown = false;
        this.goingLeft = false;
        this.goingRight = false;
        this.shooting = false;
        this.playerDimension = new Rectangle(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.heading = heading;
    }

    public enum Heading {
        UP, DOWN, RIGHT, LEFT;
    }

    public void setVel(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public void move(Rectangle[] obstacles) {
        //Update the bounding box's position
        this.playerDimension.setBounds(this.x, this.y, PLAYER_WIDTH, PLAYER_HEIGHT);

        //Checks if the player hits an obstacle
        for (int i = 0; i < obstacles.length; i++) {
            if (this.playerDimension.intersects(obstacles[i])) {
                if (goingUp) {
                    this.y = obstacles[i].y + PLAYER_HEIGHT + 14;
                }
                if (goingDown) {
                    this.y = obstacles[i].y - PLAYER_HEIGHT - 25;
                }
                if (goingRight) {
                    this.x = obstacles[i].x - PLAYER_WIDTH - 25;
                }
                if (goingLeft) {
                    this.x = obstacles[i].x + PLAYER_WIDTH + 14;
                }
                return;
            }
        }

        //Update the movement of the player
        if (goingUp) {
            this.y -= this.velocity;
        }
        if (goingDown) {
            this.y += this.velocity;
        }
        if (goingLeft) {
            this.x -= this.velocity;
        }
        if (goingRight) {
            this.x += this.velocity;
        }
    }

    public void render(Graphics g, BufferedImage img) {
        g.drawImage(img, this.x, this.y, null);
    }

    @Override
    public void keepInBounds() {
        int newX = this.x;
        int newY = this.y;
        if (newX < 0) {
            newX = 0;
        } else if (newX >= Game.WIDTH - 68) {
            newX = Game.WIDTH - 68;
        }
        if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.HEIGHT - 68) {
            newY = Game.HEIGHT - 68;
        }
        this.x = newX;
        this.y = newY;
    }
}