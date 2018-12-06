
public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		x = p.x;
		y = p.y;
	}
	
	public void set(Point p) {
		x = p.x;
		y = p.y;
	}
	
	public void set(int x , int y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Point p) {
		x = x + p.x;
		y = y + p.y;
	}

	public void add(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	public boolean isGreaterThan(Point p) {
		return (x > p.x && y > p.y);
	}
	
	public boolean isSmallerThan(Point p) {
		return (x < p.x && y < p.y);
	}
	
	public boolean isEqualTo(Point p) {
		return (x == p.x && y == p.y);
	}
}
