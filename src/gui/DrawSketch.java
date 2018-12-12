package gui;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSketch extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAX = 100;
	private int indexInList = 0;
	private Line line[] = new Line[MAX];
	private Router[] router = new Router[MAX];
	public char state = 'n';
	private int numOfRouters = 0;
	private Point tempPoint = new Point(0, 0);
    private static JFrame jFrame = null;
    private int routerImageWidth = 50;
    private int routerImageHeight = 50;
    private int numOfLines = 0;

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
		//cPane = jFrame.getContentPane();
		DrawPad p = new DrawPad();
		p.setLayout(null);
		//cPane.setLayout(null);
		//cPane.add(new DrawSketch());
		Router r = new Router(new Point(50,50),0,p);
		//p.add(r);
		Router r2 = new Router(new Point(200,200),1,p);
		//p.add(r2);
		//cPane.add(r2);
		//cPane.add(r);
		Message m = new Message(r,r2, p);
		jFrame.add(p);
		jFrame.setVisible(true);
		jFrame.setExtendedState(jFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		m.move();
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
		//rt.addRow(this);
    	if (numOfRouters < MAX) {
    		//router[numOfRouters] = new Router(position, indexer, this);
    		//indexer++;
    		numOfRouters++;
    		repaint();
    	}
    }

    public void addConnection(Point startPoint, Point endPoint) {
		//line[numOfLines] = new Line(router[getRouterIndexInList(startPoint)], router[getRouterIndexInList(endPoint)]);
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
    			//connectionIndexInList = i;
    			return i;
    		}
    	}
    	//connectionIndexInList = -1;
    	return -1;
    }
    
    public int routerTrace(Point p) {
		indexInList = getRouterIndexInList(p);
		if(indexInList >=0) {
			//currentRouterIndex = router[indexInList].index;
			return indexInList;
		}
		//currentRouterIndex = -1;
    	return -1;
    }
    
    public int iconTrace(Point p) {
    	return -1;
    }
    

}