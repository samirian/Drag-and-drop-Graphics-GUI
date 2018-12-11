package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private Line[] connection = new Line[100];
	private static final long serialVersionUID = 1L;
	private Point offset = new Point(0,0);
	public RoutingTable routingTable;
	private int routerImageWidth = 50;
	private int routerImageHeight = 50;
	public Point position = new Point(0,0);
	public Point cursorPosition = new Point(0,0); 
	public int index;
	public JLabel label;
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

		try {
			router_image = ImageIO.read(new File("/home/samir/eclipse-workspace/Routing/src/resources/router_symbol.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.position = new Point(position);
		this.index = index;

		label = new JLabel();
		label.setText("router" + String.valueOf(index));
		positionLabel = new JLabel();
		positionLabel.setText("(" + String.valueOf(position.x) + ", " + String.valueOf(position.y) + ")");
		routingTable = new RoutingTable(this);
		routingTable.setVisible(false);
		add(routingTable);
		this.drawPad = drawPad;
		add(label);
		add(positionLabel);
		positionLabel.setOpaque(true);
		positionLabel.setBackground(Color.YELLOW);
		
		this.setBounds(position.x,position.y,50,70);
		label.setBounds(0, 60, 100, 10);
		positionLabel.setBounds(0, 0, 100, 10);
		positionLabel.setVisible(false);
		
		this.drawPad.add(this);
		routerImageRectangle = new Rectangle(new Point(0,0),new Point(50,50));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage (router_image.getScaledInstance(routerImageWidth, routerImageHeight, Image.SCALE_SMOOTH), 0, 0, this);
	}
	
	public void draw(Graphics g) {
		g.drawImage (router_image.getScaledInstance(routerImageWidth, routerImageHeight, Image.SCALE_SMOOTH), position.x, position.y, this);
		label.setBounds(position.x, position.y + 10, 100, 100);
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
	
	public void repaintConnection(Line line) {
		line.setPosition();
		line.repaint();
		//line.router1.repaint();
		//line.router2.repaint();
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
        			drawPad.currentLine = null;
        			drawPad.mode = "none";
    			}
    		}
    	}
		//label.setText("<html>This is<br>a multi-line string");
    	else if(event.getButton() == 3) {
    		//Right click
    		System.out.println("--------------------right click-----------------");
    		remove();
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
		positionLabel.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBounds(position.x, position.y, 50,70);
		drawPad.currentRouterIndex = -1;
		positionLabel.setVisible(false);
		routingTable.setVisible(false);
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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(drawPad.mode == "connect") {
			Graphics g = drawPad.getGraphics();
		    ((Graphics2D) g).setStroke(new BasicStroke(5));
	        repaint();
	        drawPad.repaint();
	        Line currentLine = drawPad.currentLine; 
	        g.drawLine(currentLine.router1.position.x + 25, currentLine.router1.position.y + 25, drawPad.getMousePosition().x, drawPad.getMousePosition().y);
		
		}
		if(routerImageRectangle.isInRectangleArea(new Point(e.getX(),e.getY()))) {
	    	//System.out.println("---------current router index--------");
	    	//System.out.println(drawPad.currentRouterIndex);
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

		positionLabel.setText("(" + String.valueOf(position.x) + ", " + String.valueOf(position.y) + ")");
	}
	
	public void remove() {
		drawPad.remove(this);
		drawPad.Remove(index);
		drawPad.repaint();
		drawPad.currentRouterIndex = -1;
	}
}