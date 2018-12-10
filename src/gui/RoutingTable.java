package gui;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoutingTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n = 0;
	private Router router;
	public RoutingTable(Router router) {
		this.router = router;
		setOpaque(true);
		setBackground(Color.yellow);
		setLayout(null);
		addRow();
	}
	
	public void addRow() {
		JLabel l = new JLabel("test");
		this.add(l);
		l.setBounds(0, n*10, 100, 100);
		n++;
	}
}
