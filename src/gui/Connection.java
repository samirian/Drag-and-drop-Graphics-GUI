package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Connection extends JPanel implements MouseMotionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Router router1 = null;
	public Router router2 = null;
	private Point startPoint = new Point(0,0);
	private Point endPoint = new Point(0,0);
	public DrawPad drawPad;
	private int delta_x;
	private int delta_y;
	private JTextField textField = new JTextField();
	private JLabel weightLabel = new JLabel("1000", JLabel.CENTER);
	private int weight = 1000;
	
	public Connection(Router router1, Router router2, DrawPad drawPad) {
		this.router1 = router1;
		this.router2 = router2;
		init(drawPad);
		
	}
	
	public Connection(DrawPad drawPad) {
		init(drawPad);
	}
	
	private void init(DrawPad drawPad) {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.drawPad = drawPad;
		this.drawPad.add(this);
		setOpaque(false);

		setLayout(null);
		weightLabel.setBackground(Color.white);
		weightLabel.setOpaque(true);
		Helpers.wrapContent(weightLabel);
		weightLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				weightLabel.setVisible(false);
				textField.setVisible(true);
			}
		});
		textField.setBackground(Color.gray);
		textField.setText("1000");
		Helpers.wrapContent(textField);
		textField.setOpaque(true);
		textField.setVisible(false);
        textField.setBorder(null);
        
		Helpers.wrapContent(textField, 10, 10);
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//e.getKeyChar();
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					textField.setVisible(false);
					Helpers.wrapContent(textField, 10, 10);
					weightLabel.setVisible(true);
					System.out.println(textField.getText());
					setWeight(Integer.valueOf(textField.getText()));
				}
			}
		});
		add(weightLabel);
		add(textField);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		weightLabel.setLocation(delta_x/2 , delta_y/2);
		textField.setLocation(delta_x/2 , delta_y/2);
	}
	
	public void setPosition() {
		delta_x = router2.position.x - router1.position.x;
		delta_y = router2.position.y - router1.position.y;
		
		if(delta_x < 0) {
			//the first point is in the upper or lower right corner
			if(delta_y < 0 ) {
				//the first point is in the lower right corner
				delta_y = delta_y*-1;
				delta_x = delta_x*-1;
				startPoint.set(0, 0);
				endPoint.set(delta_x, delta_y);
				setLocation(router2.position.x+25, router2.position.y+25);
			}else {
				//the first point is in the upper right corner
				delta_x = delta_x*-1;
				startPoint.set(0, delta_y);
				endPoint.set(delta_x, 0);
				setLocation(router2.position.x+25, router1.position.y+25);
			}
		}else if(delta_x >0){
			//the first point is in the upper or lower left corner
			if(delta_y < 0 ) {
				//the first point is in the lower left corner
				delta_y = delta_y*-1;
				startPoint.set(0, delta_y);
				endPoint.set(delta_x, 0);
				setLocation(router1.position.x+25, router2.position.y+25);
			}else {
				//the first point is in the upper left corner
				startPoint.set(0, 0);
				endPoint.set(delta_x, delta_y);
				setLocation(router1.position.x+25, router1.position.y+25);
			}
		}
		if(delta_x == 0) {
			delta_x = 50;
			System.out.println("-------------------zero x-----------------");
			startPoint.set(25, 0);
			if (delta_y < 0) {
				//first point is in the lower middle
				delta_y = delta_y*-1;
				setLocation(router2.position.x, router2.position.y);
			}else {
				setLocation(router1.position.x, router1.position.y);
				
			}
			endPoint.set(25, delta_y);
		}
		if(delta_y == 0) {
			delta_y = 50;
			System.out.println("-------------------zero y-----------------");
			startPoint.set(0, 25);
			if (delta_x < 0) {
				//first point is in the lower middle
				delta_x = delta_x*-1;
				setLocation(router2.position.x, router2.position.y);
			}else {
				setLocation(router1.position.x, router1.position.y);
				
			}
			endPoint.set(delta_x, 25);
		}
		setSize(delta_x+50, delta_y+50);
		repaint();
	}
	
	public void setWeight(int w) {
		weight = w;
		weightLabel.setText(String.valueOf(w));
	}
	
	public int getWeight() {
		return weight;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (drawPad.mode == "delete") {
			drawPad.remove(this);
			drawPad.repaint();
			router1.removeConnection(this);
			router2.removeConnection(this);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
}	