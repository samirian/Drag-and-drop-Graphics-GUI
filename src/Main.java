import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.DrawPad;
import gui.Message;
import gui.Point;
import gui.Router;

public class Main extends JPanel{
	private static final long serialVersionUID = 1L;
	public char state = 'n';
    private static JFrame jFrame = null;
	
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
}
