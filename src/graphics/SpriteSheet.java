package graphics;

import java.awt.image.BufferedImage;

/**
 * Created by oxana_bs on 9.4.2016 г..
 */
public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    //Method that crops the image from x to width and from y to height
    public BufferedImage cut(int x, int y, int width, int height) {
        //Returns a new image in the area we specified
        return sheet.getSubimage(x, y, width, height);
    }
}