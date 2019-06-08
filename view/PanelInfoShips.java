
package view;

import controller.Constants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelInfoShips extends JPanel{
    
    private JLabel Title;
    
    private int width,heigth;
    
    private String title, ShipTitle;

    public PanelInfoShips() {
        repaint();
        setLayout(new GridLayout(10, 1, 20,20));
        
        
        
        Title = new JLabel("mmmmmmmmmmmmmmmmmmm");
            Title.setBackground(new Color(0,0,0,0));
            Title.setOpaque(true);
            
        add(Title);
    
    }
    
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        
        width = this.getWidth();
        heigth = this.getHeight();
        try {
            Image img2 = ImageIO.read(new File(Constants.oldBackpaper));
            
            graphics.drawImage(img2, 0, 0,this.getWidth(),this.getHeight(), null);
            
            
            Image img = ImageIO.read(new File(Constants.border));
            
            graphics.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
        } catch (IOException ex) {
            System.out.println("fondo no encotrado");
        }
        
        graphics.setFont(Constants.timesBold16);
        graphics.drawString("Puntajes" , width/4, 2*heigth/20);
        
        
        //drawBar(1,7,"rock", graphics);
        //g.fillRoundRect (250, 270, 50, 70, 6, 6);
        //fillRect (150, 70, 50, 70);
        //drawLinePlane(1.2, 3.5, 4.0, 4.0, graphics);
    }
    
    
    
}
