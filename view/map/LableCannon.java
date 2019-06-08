/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import controller.Constants;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andres Gonzalez
 */
public class LableCannon extends JLabel{
    
    private ImageIcon imageFace;

    public LableCannon() {
        setBackground(Color.decode(Constants.ColorWood));
        setOpaque(true);
        
        loadImage(Constants.cannon);
        
        this.setIcon(imageFace);
    
    }
    
    public void loadImage(String linkImage) { 
        if (linkImage == null) { 
            imageFace = null; 
        } else {            
            
            ImageIcon tmpIcon = new ImageIcon(getClass().getResource(linkImage)); 
            if(tmpIcon.getIconWidth() > 280) { 

                imageFace = new ImageIcon( tmpIcon.getImage().getScaledInstance(280, -1, Image.SCALE_DEFAULT)); 
            } else { 
                imageFace = tmpIcon; 
            } 
        }
        
        repaint();
    } 
    
    
    
}
