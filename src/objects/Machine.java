package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Machine {

    protected int x, y;
    // coordinates
    protected int velocity;
    //speed

    public Machine(int x, int y, int velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVel() {
        return velocity;
    }

    public abstract void move(Rectangle[] obstacles);

    public abstract void render(Graphics g, BufferedImage img);

    public enum Heading {
        UP, DOWN, RIGHT, LEFT;
    }

    public abstract void keepInBounds ();
}