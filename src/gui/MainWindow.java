package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawPad drawPad;
	Router r;
	Router r2;
	
    public MainWindow(){
        super.setLayout(new BorderLayout());
        setGraphPanel();
        setOpaque(false);
		r = new Router(new gui.Point(50,50), 0, drawPad);
		r2 = new Router(new gui.Point(200,200), 1, drawPad);
    }

    public void showOff() {
		Message m = new Message(r,r2, drawPad);
		m.move();
    }
    private void setGraphPanel(){
        drawPad = new DrawPad();
		drawPad.setLayout(null);
        drawPad.setPreferredSize(new Dimension(9000, 4096));
        JScrollPane scroll = new JScrollPane();
        scroll.setOpaque(false);
        scroll.setViewportView(drawPad);
        scroll.setPreferredSize(new Dimension(750, 500));
        scroll.getViewport().setViewPosition(new Point(0, 0));
        add(scroll, BorderLayout.CENTER);
        setTopPanel();
        setButtons();
    }

    private void setTopPanel() {
        JLabel info = new JLabel("yahaaaaaaahaaaaaaaaaaaaa yaaaaaaaaa leeeeeeeeeeeeeeel");
        info.setForeground(new Color(230, 220, 250));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(130, 50, 250));
        panel.add(info);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.NORTH);
    }

    private void setButtons(){
    	BufferedImage delet_image;

        JButton connect = new JButton();
        JButton delete = new JButton();
    	try {
			delet_image = ImageIO.read(new File("/home/samir/eclipse-workspace/Routing/src/resources/delet_symbol.png"));
			
            ImageIcon imageIcon = new ImageIcon(delet_image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            connect.setIcon(imageIcon);
            connect.setBorderPainted(false);
            connect.setFocusPainted(false);
            connect.setContentAreaFilled(false);
            delete.setIcon(imageIcon);
            delete.setBorderPainted(false);
            delete.setFocusPainted(false);
            delete.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton run = new JButton();
        setupIcon(run, "run");
        JButton stop = new JButton();
        setupIcon(stop, "reset");
        final JButton info = new JButton();
        setupIcon(info, "info");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(DrawUtils.parseColor("#DDDDDD"));
        buttonPanel.add(stop);
        buttonPanel.add(run);
        buttonPanel.add(info);
        buttonPanel.add(connect);
        buttonPanel.add(delete);

        connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPad.mode = "connect";
				System.out.println("connect");
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

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupIcon(JButton button, String img){
        try {
            Image icon = ImageIO.read(getClass().getResource(
                    "/resources/" + img + ".png"));
            ImageIcon imageIcon = new ImageIcon(icon);
            button.setIcon(imageIcon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
