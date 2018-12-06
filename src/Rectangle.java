import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Rectangle extends JPanel{
        private static final long serialVersionUID = 1L;

        Image img;
        Timer timer;
        int count=0;
        public Rectangle() {

            setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
            setPreferredSize(new Dimension(100,100));
            timer=new Timer(800, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    loadPic(count%5);
                    count++;
                    revalidate();
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    count=0;
                    timer.start();
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    timer.stop();
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, 120, 100,this);
        }

        public void loadPic(int number){

            String address="";

            switch(number){
            case 0: address="img1.jpg";
            break;
            case 1: address="img2.jpg";
            break;
            case 2: address="img3.jpg";
            break;
            case 3: address="img4.jpg";
            break;
            default: address="img5.jpg";
            break;
            }

            try  
            {  
                img = ImageIO.read(getClass().getResourceAsStream(address));

            }  
            catch(Exception e){
                System.out.println("error in loading image");
            }

        }
    }