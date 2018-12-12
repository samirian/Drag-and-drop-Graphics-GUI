import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.MainWindow;

public class Main extends JPanel{
	private static final long serialVersionUID = 1L;
    private static JFrame jFrame;
    private static MainWindow mainWindow;
	
    public static void main(String[] args) {
		jFrame = new JFrame();
		jFrame.setTitle("Routing by Samir");
		jFrame.setSize(1000, 500);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow = new MainWindow();
		jFrame.add(mainWindow);
		jFrame.setExtendedState(jFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		//mainWindow.showOff();
	}
}
