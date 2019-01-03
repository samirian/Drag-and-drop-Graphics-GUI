package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

import algorithm.Dijkstra;
import algorithm.routerTable;

public class Router extends JPanel implements MouseMotionListener, MouseListener {
	/**
	 * 
	 */
	private boolean f = false;
	private Connection[] connection = new Connection[100];
	private static final long serialVersionUID = 1L;
	private Point offset = new Point(0, 0);
	private RoutingTable routingTable;
	private int routerImageWidth = 50;
	private int routerImageHeight = 50;
	public Point position = new Point(0, 0);
	public Point cursorPosition = new Point(0, 0);
	public int index;
	private JLabel label;
	private JLabel positionLabel;
	private DrawPad drawPad;
	private BufferedImage router_image;
	public Rectangle routerImageRectangle;
	private int connectionNum = 0;
	public String mylabel;

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
			router_image = ImageIO.read(new File("src/resources/router_symbol.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		label.setText("router" + String.valueOf(index));
		String s = "(" + String.valueOf(position.x) + ", " + String.valueOf(position.y) + ")";
		positionLabel.setText(s);
		Helpers.wrapContent(positionLabel);
		mylabel = label.getText();

		routingTable = new RoutingTable(this);
		routingTable.setVisible(false);
		this.drawPad = drawPad;
		positionLabel.setOpaque(true);
		positionLabel.setBackground(Color.YELLOW);
		Helpers.wrapContent(label);

		this.setBounds(position.x, position.y, 50, 70);
		positionLabel.setLocation(0, 0);
		label.setLocation(0, 50);

		positionLabel.setVisible(false);
		repaint();
		routerImageRectangle = new Rectangle(new Point(0, 0), new Point(50, 50));
		add(label);
		add(positionLabel);
		this.drawPad.add(this);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(router_image.getScaledInstance(routerImageWidth, routerImageHeight, Image.SCALE_SMOOTH), 0, 0,
				this);
	}

	public void setPosition(Point p) {
		position.x = p.x;
		position.y = p.y;
		setLocation(position.x, position.y);
		repaint();
	}

	public void addConnection(Connection line) {
		connection[connectionNum] = line;
		connectionNum++;
	}

	public void removeConnection(Connection line) {
		for (int i = 0; i < connectionNum; i++) {
			if (connection[i] == line) {
				for (int k = i; k < connectionNum - 1; k++) {
					connection[k] = connection[k + 1];
				}
				connection[connectionNum - 1] = null;
				connectionNum--;
			}
		}
	}

	public void repaintConnection(Connection line) {
		line.setPosition();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == 1) {
			// left click
			Connection line = drawPad.currentLine;

			if (drawPad.mode == "connect") {
				if (line == null) {
					line = new Connection(drawPad);
					line.router1 = this;
					drawPad.currentLine = line;
				} else {
					line.router2 = this;
					line.setPosition();
					repaintConnection(line);
					addConnection(line);
					line.router1.addConnection(line);
					drawPad.addConnection(line);
					drawPad.currentLine = null;
				}
			} else if (drawPad.mode == "delete") {
				remove();
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
	public void mouseReleased(MouseEvent e) {
	}

	int activeSizeW = 500, activeSizeH = 500;
	int inactiveSizeW = 50, inactiveSizeH = 70;

	@Override
	public void mouseEntered(MouseEvent e) {
		setSize(activeSizeW, activeSizeH);
		drawPad.currentRouterIndex = index;
		Helpers.wrapContent(positionLabel);
		positionLabel.setVisible(true);
		f = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setSize(inactiveSizeW, inactiveSizeH);
		drawPad.currentRouterIndex = -1;
		positionLabel.setVisible(false);
		routingTable.setVisible(false);
		f = false;
		drawPad.removeHighlightPaths();
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		try {
			Point position = new Point(drawPad.getMousePosition().x, drawPad.getMousePosition().y);
			Point rp = new Point(position);
			rp.add(-offset.x, -offset.y);
			setPosition(rp);
			routingTable.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < connectionNum; i++)
			repaintConnection(connection[i]);
		String s = "(" + String.valueOf(position.x) + ", " + String.valueOf(position.y) + ")";
		positionLabel.setText(s);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (drawPad.mode == "connect") {
			drawPad.repaint();
		}

		if (routerImageRectangle.isInRectangleArea(new Point(e.getX(), e.getY())) && f == true) {
			setSize(activeSizeW, activeSizeH);

			if (drawPad.mode == "none") {
				routingTable.setVisible(false);
				remove(routingTable);
				routingTable = new RoutingTable(this);
				Dijkstra myDijkstra = new Dijkstra(drawPad);
				routerTable rt = myDijkstra.getRouterTable(mylabel);
				routingTable.populate(rt);
				routingTable.setVisible(true);
				add(routingTable);
				drawPad.highlightPaths(rt);
			}
		} else {
			routingTable.setVisible(false);
			drawPad.removeHighlightPaths();
			positionLabel.setVisible(false);
			setSize(inactiveSizeW, inactiveSizeH);
		}
		cursorPosition.set(x, y);
		if (routerImageRectangle.isInRectangleArea(cursorPosition)) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else {
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

	public Connection[] getConnectionsArray() {
		return connection;
	}

	public int getConnectionsCount() {
		return connectionNum;
	}
}