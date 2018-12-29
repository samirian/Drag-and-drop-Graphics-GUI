package gui;
import algorithm.*;
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
                        "-Click on any empty space inside the drawPad to create new router.\n" +
                        "-Click on connect button to connect any two routers.\n" +
                        "-Click on the weight of any connection to change its value.\n" +
                        "-Click on the delete button to delete any router or connection." +
                        "-Click on run button to run the simulation."
                );
            }
        });

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Dijkstra myDijkstra =new Dijkstra(drawPad);
                ;
            }
        });

      //  run.addActionListene;
    }
}
