package gui;

import java.awt.Dimension;
import javax.swing.JComponent;

public class Helpers {

	public static void wrapContent(JComponent o) {
		Dimension d = o.getPreferredSize();
		o.setSize(d);
	}
	public static void wrapContent(JComponent o, int x_offset, int y_offset) {
		Dimension d = o.getPreferredSize();
		o.setSize(d.width+x_offset, d.height+y_offset);
	}
}
