package gui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import algorithm.routerTable;

public class RoutingTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n = 1;
	private Router router;
	private int width = 400;
	private int height = 10;
	
	public RoutingTable(Router router) {
		this.router = router;
		setOpaque(true);
		setBackground(Color.darkGray);
		setLayout(null);
	}
	
	public void addRow(String data) {
		JLabel l = new JLabel(data);
		l.setForeground(Color.WHITE);
		this.add(l);
		l.setBounds(0, n*10, width, 10);
		height = height + 20;
		n = n + 2;
	}
	public void populate(routerTable table) {
		if(table == null)
			return;
		
		for(int i=0 ; i < table.tableOfrouter.length; i++)
		{
			String st = "";
			for(int j=1 ; j < table.tableOfrouter[i].numOfnodes;j++) {
				st += table.tableOfrouter[i].nodes[j];
				if(j < table.tableOfrouter[i].numOfnodes-1)
					st += " -> ";
			}
			System.out.println(st);
			addRow(st);
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
