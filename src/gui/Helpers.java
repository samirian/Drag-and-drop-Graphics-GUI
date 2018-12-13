package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Helpers {

	public static void wrapContent(JComponent o) {
		Dimension d = o.getPreferredSize();
		o.setSize(d);
	}
	
	public static void wrapContent(JComponent o, int x_offset, int y_offset) {
		Dimension d = o.getPreferredSize();
		o.setSize(d.width+x_offset, d.height+y_offset);
	}
	
	public static void setIcon(JButton c, String img, int width, int height) {

        try {
            Image icon = ImageIO.read(new File("src/resources/" + img + ".png"));           
            ImageIcon imageIcon = new ImageIcon(icon.getScaledInstance(width, height, Image.SCALE_SMOOTH));
            c.setIcon(imageIcon);
            c.setBorderPainted(false);
            c.setFocusPainted(false);
            c.setContentAreaFilled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void setIcon(JButton c, String img){
        try {
            Image icon = ImageIO.read(new File("src/resources/" + img + ".png"));
            ImageIcon imageIcon = new ImageIcon(icon);
            c.setIcon(imageIcon);
            c.setBorderPainted(false);
            c.setFocusPainted(false);
            c.setContentAreaFilled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public static Color parseColor(String colorStr) {
		return new Color(
			Integer.valueOf(colorStr.substring(1, 3), 16),
			Integer.valueOf(colorStr.substring(3, 5), 16),
			Integer.valueOf(colorStr.substring(5, 7), 16)
		);
	}
}
