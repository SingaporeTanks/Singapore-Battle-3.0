package game;

import display.Display;
import graphics.ResourceLoader;
import graphics.SpriteSheet;
import objects.*;
import objects.BasicEnemy;
import objects.HardEnemy;
import objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private String name;
    private int width, height;
    private Display display;

    private Thread thread;
    private Boolean running = false;
    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private BufferedImage ishot;
    private SpriteSheet shObstacles;
    public static Bullet bullet;
    public static Player player;
    public static BasicEnemy basicEnemy1;
    public static BasicEnemy basicEnemy2;
    public static HardEnemy hardEnemy;
    public Rectangle[] obstacles = {
            new Rectangle(0, 300, 51, 51),
            new Rectangle(51, 300, 51, 51),
            new Rectangle(102, 300, 51, 51),
            new Rectangle(749, 300, 51, 51),
            new Rectangle(698, 300, 51, 51),
            new Rectangle(647, 300, 51, 51),
            new Rectangle(360, 249, 51, 51),
            new Rectangle(360, 300, 51, 51)
    };

    public Game(String name) {
        this.name = name;
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    public void init() {
        this.display = new Display(this.name, this.width, this.height);
        this.img = ResourceLoader.loadResource("/texture/grass_logo2.jpg");
        this.shObstacles = new SpriteSheet(ResourceLoader.loadResource("/texture/obstacles_1.png"));
        this.ishot = ResourceLoader.loadResource("/texture/shot.jpg");

        player = new Player(360, 400, Player.Heading.UP);

        bullet = new Bullet(player.getX() + player.PLAYER_WIDTH / 2, player.getY() + player.PLAYER_HEIGHT / 2, 8, player);
        basicEnemy1 = new BasicEnemy(200, 250, BasicEnemy.Heading.DOWN);
        basicEnemy2 = new BasicEnemy(520, 250, BasicEnemy.Heading.DOWN);
        hardEnemy = new HardEnemy(360, 50);
        this.inputHandler = new InputHandler(this.display);
    }

    //The method that will update all the variables
    private void tick() {
        basicEnemy1.move(obstacles);
        basicEnemy2.move(obstacles);

        if (player.shooting) {
            bullet.renderBulletAfterMoving(g, ishot);
            bullet.moveBullet();
        }

        player.move(obstacles);
        player.keepInBounds();
        basicEnemy1.keepInBounds();
        basicEnemy2.keepInBounds();
    }

    public void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }

        this.g = this.bs.getDrawGraphics();
        this.g.drawImage(ResourceLoader.loadResource("/texture/grass_logo2.jpg"), 0, 0, 800, 600, null);

        BufferedImage imgPlayer = null;
        if (player.heading == Player.Heading.UP) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankUp.png");
        } else if (player.heading == Player.Heading.DOWN) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankDown.png");
        } else if (player.heading == Player.Heading.LEFT) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankLeft.png");
        } else if (player.heading == Player.Heading.RIGHT) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankRight.png");
        }
        if (player.goingDown) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankDown.png");
            player.heading = Player.Heading.DOWN;
        } else if (player.goingLeft) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankLeft.png");
            player.heading = Player.Heading.LEFT;
        } else if (player.goingRight) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankRight.png");
            player.heading = Player.Heading.RIGHT;
        } else if (player.goingUp) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankUp.png");
            player.heading = Player.Heading.UP;
        }

        BufferedImage imgBasicEnemy1 = null;
        BufferedImage imgBasicEnemy2 = null;
        BufferedImage imgHardEnemy = ResourceLoader.loadResource("/texture/HardEnemyUp.png");
        if (basicEnemy1.heading == BasicEnemy.Heading.UP) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyUp.png");
        } else if (basicEnemy1.heading == BasicEnemy.Heading.DOWN) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyDown.png");
        } else if (basicEnemy1.heading == BasicEnemy.Heading.LEFT) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyLeft.png");
        } else if (basicEnemy1.heading == BasicEnemy.Heading.RIGHT) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyRight.png");
        }

        if (basicEnemy2.heading == BasicEnemy.Heading.UP) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyUp.png");
        } else if (basicEnemy2.heading == BasicEnemy.Heading.DOWN) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyDown.png");
        } else if (basicEnemy2.heading == BasicEnemy.Heading.LEFT) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyLeft.png");
        } else if (basicEnemy2.heading == BasicEnemy.Heading.RIGHT) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyRight.png");
        }

        BufferedImage imgShot = ResourceLoader.loadResource("/texture/shot.jpg");


        if (player.shooting) {
            bullet.renderBulletAfterMoving(g, imgShot);
            bullet.moveBullet();
        }

        player.render(g, imgPlayer);
        basicEnemy1.render(g, imgBasicEnemy1);
        basicEnemy2.render(g, imgBasicEnemy2);
        hardEnemy.render(g, imgHardEnemy);

        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 0, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 51, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 102, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 749, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 698, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 647, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 360, 249, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 360, 300, null);

        this.g.dispose();
        this.bs.show();
    }

    @Override
    public void run() {
        this.init();
        while (running) {
            tick();
            render();
            if (basicEnemy1.enemyDimension.intersects(player.playerDimension) ||
                    basicEnemy2.enemyDimension.intersects(player.playerDimension)) {
                running = false;
                System.exit(0);
            }
        }
        this.stop();
    }

    //Creating a start method for the Thread to start our game
    public synchronized void start() {

        //If the game is running exit the method
        //This is done in order to prevent the game to initialize
        //more than enough threads
        if (running) {
            return;
        }
        //Setting the while-game-loop to run
        running = true;
        //Initialize the thread that will work with "this" class (game.Game)
        thread = new Thread(this);
        //The start method will call start the new thread and it will call
        //the run method in our class
        thread.start();
    }

    //Creating a stop method for the Thread to stop our game
    public synchronized void stop() {
        //If the game is not running exit the method
        //This is done to prevent the game from stopping a
        //non-existing thread and cause errors
        if (!running) {
            return;
        }
        running = false;
        //The join method stops the current method from executing and it
        //must be surrounded in try-catch in order to work
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}