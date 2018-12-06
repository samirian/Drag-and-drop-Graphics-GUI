import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Router extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RoutingTable routingTable = null;
	private int routerImageWidth = 50;
	private int routerImageHeight = 50;
	public Point position = null; 
	public int index;
	public JLabel label = null;
	public JLabel list = null;
	private DrawSketch ds = null;
	private BufferedImage router_image = null;
	
	public Router(Point position, int index, DrawSketch ds) {
		try {
			router_image = ImageIO.read(new File("/home/samir/eclipse-workspace/Routing/src/router_symbol.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.position = new Point(position);
		this.index = index;
		label = new JLabel("router" + String.valueOf(index));
		list = new JLabel("router" + String.valueOf(index) + " connected to" + "-");
		list.setVisible(false);
		this.ds = ds;
		this.ds.add(label);
		this.ds.add(list);
	}
	
	public void draw(Graphics g) {
		g.drawImage (router_image.getScaledInstance(routerImageWidth, routerImageHeight, Image.SCALE_SMOOTH), position.x, position.y, this);
		label.setBounds(position.x, position.y + 10, 100, 100);
	}
	
	public void setPosition(Point p) {
		position.x = p.x;
		position.y = p.y;
	}
}