package gui;
import algorithm.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		JButton stop = new JButton();
		Helpers.setIcon(stop, "reset", 50, 50);
		final JButton info = new JButton();
		Helpers.setIcon(info, "info", 50 , 50);
		final JButton normal = new JButton();
		Helpers.setIcon(normal, "normal", 50 , 50);
		JLabel status = new JLabel();
		status.setText("Normal Mode");
		
        setBackground(Helpers.parseColor("#DDDDDD"));
        add(stop);
        add(info);
        add(connect);
        add(delete);
        add(normal);
        add(status);

        connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPad.mode = "connect";
				status.setText("Connect Mode");

			}
		});
        
        delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPad.mode = "delete";
				status.setText("Delete Mode");
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

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status.setText("Normal Mode");
                drawPad.mode ="none";
            }
        });


      //  run.addActionListene;
    }
}
