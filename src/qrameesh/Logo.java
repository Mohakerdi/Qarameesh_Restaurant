package qrameesh;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Logo{
    private final ImageIcon logo;
    private final Image logoImage;
    private final Image scaledImage;
    private final ImageIcon scaledIcon;
    
    private final JLabel logoLabel;

    public Logo(int x, int y, String path) {
        logo= new ImageIcon(path);
        logoImage = logo.getImage();
        scaledImage = logoImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(scaledIcon);
    }

    public JLabel getLogoLabel() {
        return logoLabel;
    }

    public ImageIcon getScaledIcon() {
        return scaledIcon;
    }

    public Image getScaledImage() {
        return scaledImage;
    }
    
    
        
}