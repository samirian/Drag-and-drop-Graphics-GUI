package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class IconsBar extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DrawPad drawPad;
	MainWindow mainWindow;
	
	public IconsBar(MainWindow mainWindow, DrawPad drawPad) {
		this.mainWindow = mainWindow;
		this.drawPad = drawPad;
		setButtons();
	}
	
	private void setButtons(){
		JButton connect = new JButton();
		Helpers.setIcon(connect, "connect_symbol", 50, 50);
		JButton delete = new JButton();
		Helpers.setIcon(delete, "delete_symbol", 50, 50);
		JButton run = new JButton();
		Helpers.setIcon(run, "run");
		JButton stop = new JButton();
		Helpers.setIcon(stop, "reset");
		final JButton info = new JButton();
		Helpers.setIcon(info, "info");

        setBackground(Helpers.parseColor("#DDDDDD"));
        add(stop);
        add(run);
        add(info);
        add(connect);
        add(delete);

        connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPad.mode = "connect";
			}
		});
        
        delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPad.mode = "delete";
			}
		});

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //drawPad.reset();
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Click on empty space to create new node\n" +
                        "Drag from node to node to create an edge\n" +
                        "Click on edges to set the weight\n\n" +
                        "Combinations:\n" +
                        "Shift + Left Click       :    Set node as source\n" +
                        "Shift + Right Click     :    Set node as destination\n" +
                        "Ctrl  + Drag               :    Reposition Node\n" +
                        "Ctrl  + Click                :    Get Path of Node\n" +
                        "Ctrl  + Shift + Click   :    Delete Node/Edge\n");
            }
        });

        //run.addActionListener();
    }
}
