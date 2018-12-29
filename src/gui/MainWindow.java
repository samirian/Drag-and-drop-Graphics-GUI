package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import algorithm.Dijkstra;

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
        //r = new Router(new gui.Point(50,50), 0, drawPad);
		//r2 = new Router(new gui.Point(200,200), 1, drawPad);
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
        IconsBar iconBar = new IconsBar(this, drawPad);
        add(iconBar,BorderLayout.SOUTH);
    
        
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
}
