import java.awt.BasicStroke;
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
	
	public Line(Router router1, Router router2) {
		this.router1 = router1;
		this.router2 = router2;
	}
	
	public void draw(Graphics g) {
		Point ps = new Point(router1.position);
		Point pe = new Point(router2.position);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(ps.x + 25, ps.y + 25, pe.x + 25, pe.y + 25);
	}
}	