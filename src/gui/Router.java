package gui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Router extends JPanel implements MouseMotionListener, MouseListener {
	/**
	 * 
	 */
	private boolean f = false;
	private Line[] connection = new Line[100];
	private static final long serialVersionUID = 1L;
	private Point offset = new Point(0,0);
	private RoutingTable routingTable;
	private int routerImageWidth = 50;
	private int routerImageHeight = 50;
	public Point position = new Point(0,0);
	public Point cursorPosition = new Point(0,0); 
	public int index;
	private JLabel label;
	private JLabel positionLabel;
	private DrawPad drawPad;
	private BufferedImage router_image;
	public Rectangle routerImageRectangle;
	private int connectionNum = 0;
	
	public Router(Point position, int index, DrawPad drawPad) {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setLayout(null);
		this.setOpaque(false);
		this.position = new Point(position);
		this.index = index;
		label = new JLabel();
		positionLabel = new JLabel();
		
		try {
			router_image = ImageIO.read(new File("/home/samir/eclipse-workspace/Routing/src/resources/router_symbol.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}

		label.setText("router" + String.valueOf(index));
		String s = "(" + String.valueOf(position.x) + ", " + String.valueOf(position.y) + ")";
		positionLabel.setText(s);
		Helpers.wrapContent(positionLabel);
		
		routingTable = new RoutingTable(this);
		routingTable.setVisible(false);
		add(routingTable);
		this.drawPad = drawPad;
		positionLabel.setOpaque(true);
		positionLabel.setBackground(Color.YELLOW);
		Helpers.wrapContent(label);
		
		this.setBounds(position.x,position.y,50,70);
		positionLabel.setLocation(0, 0);
		label.setLocation(0, 50);
		
		positionLabel.setVisible(false);
		repaint();
		routerImageRectangle = new Rectangle(new Point(0,0),new Point(50,50));
		add(label);
		add(positionLabel);
		this.drawPad.add(this);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage (router_image.getScaledInstance(routerImageWidth, routerImageHeight, Image.SCALE_SMOOTH), 0, 0, this);
	}
	
	public void setPosition(Point p) {
		position.x = p.x;
		position.y = p.y;
		this.setBounds(position.x,position.y,50,70);
		repaint();
	}

	public void addConnection(Line line) {
		connection[connectionNum] = line;
		connectionNum++;
	}
	
	public void removeConnection(Line line) {
		for (int i = 0 ; i < connectionNum; i++) {
			if(connection[i] == line) {
				for(int k = i ; k < connectionNum - 1 ; k++) {
					connection[k] = connection[k+1];
				}
				connection[connectionNum -1] = null;
				connectionNum--;
			}
		}
	}
	
	public void repaintConnection(Line line) {
		line.setPosition();
		line.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
    	if(event.getButton() == 1) {
    		//left click
    		Line line = drawPad.currentLine;
    		if(drawPad.mode == "connect") {
    			if(line == null) {
    				line = new Line(drawPad);
	    			line.router1 = this;
	    			drawPad.currentLine = line;
    			}else {
    				line.router2 = this;
        			line.setPosition();
    				repaintConnection(line);
    				addConnection(line);
        			line.router1.addConnection(line);
        			drawPad.addConnection(line);
        			drawPad.currentLine = null;
        			drawPad.mode = "none";
        			System.out.println("---------------a new connection established--------------");
    			}
    		}else if(drawPad.mode == "delete") {
        		remove();
        		drawPad.mode = "none";
    		}
    	}
	}
	@Override
	public void mousePressed(MouseEvent event) {
    	int x = event.getX();
    	int y = event.getY();
    	offset.set(x, y);
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		setBounds(position.x, position.y, 150,500);
		drawPad.currentRouterIndex = index;
		Helpers.wrapContent(positionLabel);
		positionLabel.setVisible(true);
		f = true;
	}
	@Override
	public void mouseExited(MouseEvent e) {
		setBounds(position.x, position.y, 50,70);
		drawPad.currentRouterIndex = -1;
		positionLabel.setVisible(false);
		routingTable.setVisible(false);
		f = false;
	}
	@Override
	public void mouseDragged(MouseEvent event) {
		try {
	    	Point position = new Point(drawPad.getMousePosition().x, drawPad.getMousePosition().y);
	    	Point rp = new Point(position);
	    	rp.add(-offset.x, -offset.y);
	    	setPosition(rp);
	    	routingTable.setVisible(false);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i =0 ; i< connectionNum ;i++)
			repaintConnection(connection[i]);

		positionLabel.setText("(" + String.valueOf(position.x) + ", " + String.valueOf(position.y) + ")");
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(drawPad.mode == "connect") {
	        repaint();
	        drawPad.repaint();
		}
		if(routerImageRectangle.isInRectangleArea(new Point(e.getX(),e.getY()))&& f == true) {
			routingTable.setVisible(true);
		}else {
			routingTable.setVisible(false);
		}
		routingTable.setBounds(e.getX(), e.getY(), routingTable.getWidth(), routingTable.getHeight());
		cursorPosition.set(x, y);
    	if (routerImageRectangle.isInRectangleArea(cursorPosition)) {
    		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	}else {
    		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    	}
	}
	
	public void remove() {
		drawPad.remove(this);
		drawPad.Remove(index);
		drawPad.currentRouterIndex = -1;
	}
	
	public RoutingTable getRoutingTable() {
		return routingTable;
	}
	
	public Line[] getConnectionsArray() {
		return connection;
	}
	
	public int getConnectionsCount() {
		return connectionNum;
	}
}