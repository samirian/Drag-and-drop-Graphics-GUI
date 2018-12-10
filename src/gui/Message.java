package gui;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Message extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Router router1 = null;
	public Router router2 = null;
	public Point position=null;
	private BufferedImage message_image = null;
	
	public Message(Router router1, Router router2, DrawPad c) {
		this.router1 = router1;
		this.router2 = router2;
		position = new Point(router1.position);
		setBounds(position.x, position.y,50,50);
		this.setLayout(null);
		c.add(this);
		this.setOpaque(false);
		try {
			message_image = ImageIO.read(new File("/home/samir/eclipse-workspace/Routing/src/resources/message-symbol.jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage (message_image.getScaledInstance(50, 50, Image.SCALE_SMOOTH), 0, 0, this);
	}
	
	public void setPosition(Point p) {
		position.set(p);
		setBounds(position.x, position.y,50,50);
		repaint();
	}
	
    public void move() {
    	Point p1 = router1.position;
    	Point p2 = router2.position;
    	double x1 = p1.x;
    	double x2 = p2.x;
    	double x = 0;
    	double y = 0;
    	double y1 = p1.y;
    	double y2 = p2.y;
    	if((x1-x2)<0) {
    		//x2 is the greater - > increment x1
    		x= x1;
    		y= y1;
    	}else {
    		double temp;
    		temp = x1;
    		x1 = x2;
    		x2 = temp;
    		temp = y1;
    		y1 = y2;
    		y2 = temp;
    		x = x1;
    		y= y1;
    	}
    	
    	double m = (y2-y1)/(x2-x1);
    	double c = y1-x1*m;
    	while(x < x2) {
    		y = m*x +c;
    		setPosition(new Point((int)x,(int)y));
    		x = x + 1;
    		try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	setVisible(false);
    }
}
