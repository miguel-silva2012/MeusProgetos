package src.resources;

import java.awt.Image;
import javax.swing.ImageIcon;

public class render_images
{
    public static String current_folder = System.getProperty("user.dir") + "\\astronautGame\\src\\resources";

    public static String path_image = current_folder + "\\images\\";

    public static Image images[][] = {
        {
            new ImageIcon(path_image + "caracter.png").getImage(),
            new ImageIcon(path_image + "caracterInverse.png").getImage(),
            new ImageIcon(path_image + "jet_caracter.png").getImage(),
            new ImageIcon(path_image + "jet_Inversecaracter.png").getImage()
        },
        {
            new ImageIcon(path_image + "coin.gif").getImage()
        },
        {
            new ImageIcon(path_image + "background.png").getImage()
        },
        {
            new ImageIcon(path_image + "icon.png").getImage()
        }
    };    
}
