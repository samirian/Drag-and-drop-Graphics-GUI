package gui;
public class Rectangle {
	public Point p1 = null;
	public Point p2 = null;
	public Point p3 = null;
	public Point p4 = null;
	
	public Rectangle(Point p1, Point p4) {
		/*
		 * p1		p2
		 * p3		P4
		 */
		
		this.p1 = new Point(p1);
		this.p2 = new Point(p1.add(new Point(p4.x- p1.x, 0)));
		p1.set(this.p1);
		this.p3 = new Point(p1.add(new Point(0, p4.y-p1.y)));
		this.p4 = new Point(p4);
	}
	
	public boolean isInRectangleArea(Point p) {
		if ((p.isGreaterThan(p1) || p.isEqualTo(p1) )&&(p.isSmallerThan(p4)|| p.isEqualTo(p4))){
			return true;
		}
		return false;
	}
}
