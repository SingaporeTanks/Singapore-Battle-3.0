package objects;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BasicEnemy extends Enemy {

    public static final int ENEMY_WIDTH = 60;
    public static final int ENEMY_HEIGHT = 60;
    public static int velocity;
    public int dx;
    public int dy;
    public int time = 0;
    public Heading heading;
    public Rectangle enemyDimension;

    public BasicEnemy(int x, int y, Heading heading) {
        super(x, y);
        velocity = 2;
        this.heading = heading;
        this.enemyDimension = new Rectangle(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
    }

    public void move(Rectangle[] obstacles) {

        Random r = new Random();
        int n = r.nextInt(4);
        time++;

        this.x = this.x + dx;
        this.y = this.y + dy;

        if (time % 50 == 0) {
            switch (n) {
                case 0:
                    dx = 0;
                    dy = 2;
                    this.heading = Heading.DOWN;
                    break;
                case 1:
                    dx = 2;
                    dy = 0;
                    this.heading = Heading.RIGHT;
                    break;
                case 2:
                    dx = -2;
                    dy = 0;
                    this.heading = Heading.LEFT;
                    break;
                case 3:
                    dx = 0;
                    dy = -2;
                    this.heading = Heading.UP;
                    break;
            }
        }
        this.enemyDimension.setBounds(this.x, this.y, ENEMY_WIDTH, ENEMY_HEIGHT);

        for (int i = 0; i < obstacles.length; i++) {
            if (this.enemyDimension.intersects(obstacles[i])) {
                if (heading == Heading.UP) {
                    this.y = obstacles[i].y + ENEMY_HEIGHT + 14;
                }
                if (heading == Heading.DOWN) {
                    this.y = obstacles[i].y - ENEMY_HEIGHT - 25;
                }
                if (heading == Heading.RIGHT) {
                    this.x = obstacles[i].x - ENEMY_WIDTH - 25;
                }
                if (heading == Heading.LEFT) {
                    this.x = obstacles[i].x + ENEMY_WIDTH + 14;
                }
                return;
            }
        }
    }

    public enum Heading {
        UP, DOWN, RIGHT, LEFT;
    }

    public void render(Graphics g, BufferedImage imgEnemy) {
        g.drawImage(imgEnemy, this.x, this.y, null);

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