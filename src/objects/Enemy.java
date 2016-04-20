package objects;

/**
 * Created by oxana_bs on 10.4.2016 г..
 */
public abstract class Enemy extends Machine {

    public static int velocity;

    public Enemy(int x, int y) {
        super(x, y, velocity);
        velocity = 2;
    }

    public abstract void keepInBounds();
}