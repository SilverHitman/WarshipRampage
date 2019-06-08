
package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import controller.Constants;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.MatteBorder;



public class BtnStandar extends JButton{
    
    private String word;
    private String linkIcon;
    private ImageIcon imageFace;
    private int width,heigth;
    
    public BtnStandar(String word, String linkIcon) {
        this.word = word;
        this.linkIcon = linkIcon;
        
        setForeground(Color.GRAY);
        //setBackground(new Color(0, 0,0,0));
        
        setFocusable(false);
	setText("" + word);
        
        //setContentAreaFilled(false);
        setOpaque(true);
        loadImage(linkIcon);
        
        //setBorder(Constants.STANDAR_BORDER);
        
        setIcon(imageFace);
    }

//    @Override
//    public void paint(Graphics g) {
//            super.paint(g);
//        
//        width = this.getWidth();
//        heigth = this.getHeight();
//        //Graphics2D g2 = (Graphics2D) g;
//        
//        Image img2 = new ImageIcon(getClass().getResource(Constants.oldBackpaper)).getImage();
//        g.drawImage(img2, 0, 0,this.getWidth(),this.getHeight(), null);
//        
//        
//        super.paintComponent(g);
//    }
    
    
    
    public void loadImage(String linkImage) { 
        if (linkImage == null) { 
            imageFace = null; 
        } else {            
            
            ImageIcon tmpIcon = new ImageIcon(getClass().getResource(linkImage)); 
            if(tmpIcon.getIconWidth() > 60) { 

                imageFace = new ImageIcon( tmpIcon.getImage().getScaledInstance(60, -1, Image.SCALE_DEFAULT)); 
            } else { 
                imageFace = tmpIcon; 
            } 
        }
    } 
    
    
}
