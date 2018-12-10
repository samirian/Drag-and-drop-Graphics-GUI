package gui;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoutingTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n = 1;
	private Router router;
	private int width = 100;
	private int height = 10;
	
	public RoutingTable(Router router) {
		this.router = router;
		setOpaque(true);
		setBackground(Color.yellow);
		setLayout(null);
		addRow();
		addRow();
		addRow();
		addRow();
	}
	
	public void addRow() {
		JLabel l = new JLabel("test");
		this.add(l);
		l.setBounds(0, n*10, width, 10);
		height = height + 20;
		n = n + 2;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
