
package view.map;


import controller.Actions;
import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import view.BtnStandar;

public class TopPanel extends JPanel{

    private BtnStandar btnFlag;
    
    private JLabel lblArrow;
    
    private JLabel lablePlayer1;
    private JLabel lablePlayer2;
    
    private String namePla1, namePla2;
    private ImageIcon imageFace;
    
    
    public TopPanel(ActionListener action) {
        this.setLayout(new BorderLayout());
        this.setBorder(null);
        this.setBackground(Color.decode(Constants.ColorWood));
        imageFace = null;

        JPanel panelnames = new JPanel();
        panelnames.setBorder(new MatteBorder(0, 3,3, 3, Color.GRAY));
        panelnames.setLayout(new GridLayout(1, 5));
        
        namePla1 = "player";
        namePla2 = "player";

        
            panelnames.add(addSpace());
        
            lablePlayer1 = new JLabel(namePla1);
                lablePlayer1.setFont(Constants.timesBold30);
                lablePlayer1.setOpaque(true);
                lablePlayer1.setBorder(null);
                lablePlayer1.setBackground(Color.decode(Constants.ColorWood));
            panelnames.add(lablePlayer1);
            
            loadImage(Constants.left_arrow + "");

            lblArrow = new JLabel();
                lblArrow.setOpaque(true);
                lblArrow.setBackground(Color.decode(Constants.ColorWood));
                lblArrow.setIcon(imageFace);
            panelnames.add(lblArrow);

            lablePlayer2 = new JLabel(namePla2);
                lablePlayer2.setFont(Constants.timesBold30);
                lablePlayer2.setBorder(null);
                lablePlayer2.setBackground(Color.decode(Constants.ColorWood));
                lablePlayer2.setOpaque(true);
            panelnames.add(lablePlayer2);

        add(panelnames, BorderLayout.CENTER);
        
        btnFlag = new BtnStandar("", Constants.flag);
            btnFlag.setBackground(Color.decode(Constants.ColorWood));
            btnFlag.addActionListener(action);
            btnFlag.setActionCommand(Actions.RENDITION.toString());
            
        add(btnFlag, BorderLayout.EAST);
    }
    
    public void setNamesPlayers(String namePla1, String namePla2){
        this.namePla1 = namePla1;
        this.namePla2 = namePla2;
        lablePlayer1.setText(namePla1);
        lablePlayer2.setText(namePla2);
        
    }
    
    
    public void SetInfoTurn(String namePla1, String namePla2, String nameActualTurn){
        if(namePla1.equals(nameActualTurn)){
            loadImage(Constants.left_arrow);
        }else{
            loadImage(Constants.rigth_arrow);
        }
        
    }
    
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
        
        repaint();
    } 
    
    public JLabel addSpace(){
        JLabel generric = new JLabel("         ");
        generric.setOpaque(true);
        generric.setBorder(null);
        generric.setBackground(Color.decode(Constants.ColorWood));
        return generric ; 
    }
    
    
}
