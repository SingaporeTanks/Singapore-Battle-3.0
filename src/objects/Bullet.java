package objects;

import graphics.ResourceLoader;
import objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Bullet {
    public static final int BULLET_WIDTH = 42;
    public static final int BULLET_HEIGHT = 42;

    private int x;
    private int y;
    public int count = 100;
    private int energy = 30;

    public static boolean shooting;

    public Rectangle bulletDimension;
    public BufferedImage ishot;
    public Player player;

    public Bullet(int x, int y, int energy, Player player) {
        setX(x);
        setY(y);
        setEnergy(energy);
        Create();
        this.bulletDimension = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);
        this.player = player;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean Create() {
        try {
            ishot = ResourceLoader.loadResource("/texture/shot.jpg");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void renderBulletAfterMoving(Graphics g, BufferedImage img) {
        g.drawImage(img, this.x, this.y, null);
    }

    public void moveBullet() {
        //Update the bounding box's position
        this.bulletDimension.setBounds(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);

        if (player.heading == Player.Heading.UP) {
            this.y -= this.energy;
        }
        if (player.heading == Player.Heading.DOWN) {
            this.y += this.energy;
        }
        if (player.heading == Player.Heading.LEFT) {
            this.x -= this.energy;
        }
        if (player.heading == Player.Heading.RIGHT) {
            this.x += this.energy;
        }
    }
}


