package graphics;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {
    //Method that returns a resource
    public static BufferedImage loadResource(String path) {
        try {
            return ImageIO.read(ResourceLoader.class.getResource(path));
        } catch (IOException e) {
            //e.printStackTrace();
            //Exits the application
            System.exit(1);
        }

        //Ensures that no errors are passed
        return null;
    }


}
