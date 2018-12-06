import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoutingTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n = 0;
	public RoutingTable() {
	}
	
	public void addRow(DrawSketch ds) {
		JLabel l = new JLabel("test");
		ds.add(l);
		l.setBounds(0, n*20, 100, 100);
		n++;
	}
}
