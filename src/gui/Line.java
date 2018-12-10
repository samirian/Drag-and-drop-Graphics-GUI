package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Line extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Router router1 = null;
	public Router router2 = null;
	public DrawPad drawPad;
	
	public Line(Router router1, Router router2, DrawPad drawPad) {
		this.router1 = router1;
		this.router2 = router2;
		this.drawPad = drawPad;
		this.drawPad.add(this);
		setOpaque(false);
		System.out.println("---------------------adsfsdfas---------------");
		setPosition();
	}
	
	public Line(DrawPad drawPad) {
		this.drawPad = drawPad;
		this.drawPad.add(this);
		setOpaque(false);
		System.out.println("---------------------adsfsdfas---------------");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		arrange();
		int delta_x = router2.position.x -router1.position.x;
		int delta_y = router2.position.y-router1.position.y;
		setBounds(router1.position.x + 25, router1.position.y + 25, delta_x, delta_y);
		System.out.println("delta x : " + String.valueOf(delta_x));
		System.out.println("delta y : " + String.valueOf(delta_y));
		((Graphics2D) g).setStroke(new BasicStroke(10));
		g.drawLine(0, 0,delta_x, delta_y);
	}
	
	public void setPosition() {

		int delta_x = router2.position.x -router1.position.x;
		int delta_y = router2.position.y-router1.position.y;
		setBounds(router1.position.x, router1.position.y, delta_x, delta_y);
	}
	private void arrange() {
		if(router1.position.isGreaterThan(router2.position)) {
			Router r = router1;
			router1 = router2;
			router2 = r;
		}
	}
	
	public void draw(Graphics g) {
		Point ps = new Point(router1.position);
		Point pe = new Point(router2.position);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(ps.x + 25, ps.y + 25, pe.x + 25, pe.y + 25);
	}
}	