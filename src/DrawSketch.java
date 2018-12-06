import java.awt.BasicStroke;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawSketch extends JPanel implements MouseMotionListener, MouseListener {
	
	/**
	 * 
	 */

	private RoutingTable rt = new RoutingTable();
	private int connectionIndexInList = -1;
	private static final long serialVersionUID = 1L;
	private Point offsetPoint = new Point(0, 0);
	private static final int MAX = 100;
	private int indexInList = 0;
	private Line line[] = new Line[MAX];
	private int indexer = 0;
	private Router[] router = new Router[MAX];
	public char state = 'n';
	private int numOfRouters = 0;
	private int currentRouterIndex = -1;
	private Point startPoint = new Point(0, 0);
	private Point endPoint = new Point(0, 0);
	private char position_state = 's';
	private Point tempPoint = new Point(0, 0);
    private static Container cPane = null;
    private static JFrame jFrame = null;
    private int routerImageWidth = 50;
    private int routerImageHeight = 50;
    private int numOfLines = 0;
	private BufferedImage delete_image = null;
	JLabel l = new JLabel("3aaaaaaaaaa");

	public static void main(String[] args) {
		jFrame = new JFrame();
		jFrame.setTitle("Routing by Samir");
		jFrame.setSize(1000, 500);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    	System.exit(0);
			}
		});
		cPane = jFrame.getContentPane();
		cPane.add(new DrawSketch());
		jFrame.setVisible(true);
		jFrame.setExtendedState(jFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	public DrawSketch() {
		addMouseListener(this);
		addMouseMotionListener(this);

		try {
			delete_image = ImageIO.read(new File("/home/samir/eclipse-workspace/Routing/src/router_symbol.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.add(rt);
	}
	
	@Override
	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	for (int i = 0; i < numOfRouters; i++) {
    		router[i].draw(g);
    	}
    	for (int i = 0 ; i < numOfLines ; i++) {
    		line[i].draw(g);
    	}
    	if(state =='d') {
	    	((Graphics2D) g).setStroke(new BasicStroke(3));
	    	
			g.drawLine(startPoint.x, startPoint.y, tempPoint.x, tempPoint.y);
    	}

		g.drawImage (delete_image.getScaledInstance(routerImageWidth, routerImageHeight, Image.SCALE_SMOOTH), 0, 0, this);
    }
    
    public int getRouterIndexInList(Point position) {
    	Point p = new Point(routerImageWidth, routerImageHeight);
    	for (int i = 0; i < numOfRouters; i++) {
    		tempPoint = router[i].position;
    		p.set(routerImageWidth, routerImageHeight);
    		p.add(tempPoint);
    		if (position.isGreaterThan(tempPoint) && position.isSmallerThan(p)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public void addRouter(Point position) {
		rt.addRow(this);
    	if (numOfRouters < MAX) {
    		router[numOfRouters] = new Router(position, indexer, this);
    		indexer++;
    		numOfRouters++;
    		repaint();
    	}
    }

    public void addConnection(Point startPoint, Point endPoint) {
		line[numOfLines] = new Line(router[getRouterIndexInList(startPoint)], router[getRouterIndexInList(endPoint)]);
		numOfLines++;
    }
     
    public void removeConnection(int indexInList) {
    	if(indexInList > -1) {
	    	for (int k = indexInList ; k < numOfLines ;k++) {
	    		line[k] = line[k+1];
	    	}
	    	line[numOfLines - 1] = null;
	    	numOfLines--;
    	}
    	repaint();
    }
    
    @Override
    public void remove(int n) {
    	if (n < 0 || n >= numOfRouters) {
    		return;
    	}
    	
    	//delete the connection
    	for (int i = 0 ; i < numOfLines ; i++) {
    		if (line[i].router1.index == router[n].index || line[i].router2.index == router[n].index) {
    			//the connection is related to the current router to be deleted
    			//delete the connection
    	    	for (int k = i ; k < numOfLines ;k++) {
    	    		line[k] = line[k+1];
    	    	}
    	    	line[numOfLines - 1] = null;
    	    	numOfLines--;
    		}
    	}
    	
    	router[n].label.setText("");
    	for (int i = n ; i < numOfRouters ;i++) {
    		router[i] = router[i+1];
    	}
    	router[numOfRouters - 1] = null;
    	numOfRouters--;
    	System.out.println("Nmber of router : " + String.valueOf(numOfRouters));
    	repaint();
    }

    public boolean equation(Point p1, Point p2, Point p) {
    	double x1 = p1.x;
    	double x2 = p2.x;
    	double x = p.x;
    	double y1 = p1.y;
    	double y2 = p2.y;
    	double y = p.y;
    	double A = (y2-y1)/(x2-x1);
    	double B = -1;
    	double C = y1 - x1*A;
    	double upper = Math.sqrt(Math.pow((A*x + B*y +C), 2));
    	double lower = Math.sqrt(Math.pow(A,2.0) + Math.pow(B, 2.0));
    	double result = upper/lower;
    	return result <= 5;
    }
    
    public int lineTrace(Point p) {
    	for (int i = 0 ; i < numOfLines ; i++) {
    		Point start = new Point(line[i].router1.position);
    		start.add(25, 25);
    		Point end = new Point(line[i].router2.position);
    		end.add(25, 25);
    		if(equation(start, end, p)) {
    			connectionIndexInList = i;
    			return i;
    		}
    	}
    	connectionIndexInList = -1;
    	return -1;
    }
    
    public int routerTrace(Point p) {
		indexInList = getRouterIndexInList(p);
		if(indexInList >=0) {
			currentRouterIndex = router[indexInList].index;
			return indexInList;
		}
		currentRouterIndex = -1;
    	return -1;
    }
    
    public int iconTrace(Point p) {
    	return -1;
    }
    
    @Override
    public void mouseMoved(MouseEvent event) {
    	int x = event.getX();
    	int y = event.getY();
    	Point position = new Point(x, y);
    	//free area, icon, router, or connection
    	if(routerTrace(position) >= 0 ) {
    		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	}else if(lineTrace(position)>=0) {
    		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    	}else {
    		setCursor(Cursor.getDefaultCursor());
    	}
    	
    	if(state == 'd') {
			tempPoint = position;
			repaint();
		}
    }

    @Override
    public void mouseDragged(MouseEvent event) {
    	int x = event.getX();
    	int y = event.getY();
    	Point position = new Point(x, y);
		if (currentRouterIndex >= 0) {
	    	if (position_state == 's') {
	    		offsetPoint.x = router[indexInList].position.x - x;
	    		offsetPoint.y = router[indexInList].position.y - y;
	    		tempPoint.set(position);
	    		tempPoint.add(offsetPoint);
	    		position_state = 'm';
	    	}
	    	if (state == 'n') {
				router[indexInList].setPosition(tempPoint);
				tempPoint.set(position);
				tempPoint.add(offsetPoint);
				repaint();
			}
		}
	}
    
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point position = new Point(x, y);
		if (e.getButton() == 1) {
			if (e.getClickCount() == 1) {
				//adding new router
				if (currentRouterIndex < 0) { 
					//no router exists in this area
					Point midPoint = new Point(-25, -25);
					position.add(midPoint);
					addRouter(position);
				}
			}else if (e.getClickCount() == 2) {
				//draw line
				if (currentRouterIndex >= 0){	
					if(state == 'n') {
						//n : indicated that no line is being dragged
						startPoint.set(router[indexInList].position);
						startPoint.add(25, 25);
						state = 'd';
					}else if (state == 'd') {
						//d : indicates that a line is being dragged
						endPoint.set(router[indexInList].position);
						endPoint.add(25, 25);
						state = 'n';
						addConnection(startPoint, endPoint);
						repaint();
					}
				}
			}
		}else if (e.getButton() == 3) {
			if (indexInList >= 0) {
				//delete item on right click
				remove(indexInList);
			}
			if (connectionIndexInList > -1) {
				//delete item on right click
				removeConnection(connectionIndexInList);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}