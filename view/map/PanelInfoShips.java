
package view.map;

import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PanelInfoShips extends JPanel{
    
    private JLabel Title;
    private PanelBtnShips paenlBtn;
    private PanelAttacks panelAttacks;
    private LableCannon lableCannon;
    private int width,heigth;

    public PanelInfoShips(ActionListener action) {
        repaint();
        setOpaque(false);
        width = 0;
        heigth = 0;
        panelAttacks = new PanelAttacks(action);
        
        
        setLayout(new BorderLayout());
        Title = new JLabel("ORGANIZA TU FLOTA");
            Title.setBackground(new Color(0,0,0,0));
            Title.setBorder(new EmptyBorder(10, 10, 10, 10));
            Title.setOpaque(true);
            Title.setFont(Constants.timesBold30);
        add(Title, BorderLayout.NORTH);
        
        this.setBorder(new EmptyBorder(30, 50, 50, 50));
        lableCannon = new LableCannon();
        paenlBtn = new PanelBtnShips(action);
            
            
        add(paenlBtn, BorderLayout.CENTER);
    }
    
    @Override
    public void paint(Graphics graphics){
        Graphics2D g2 = (Graphics2D) graphics;
        width = this.getWidth();
        heigth = this.getHeight();
        Image img2 = new ImageIcon(getClass().getResource(Constants.oldBackpaper)).getImage();
        graphics.drawImage(img2, 0, 0,this.getWidth(),this.getHeight(), null);
        Image img = new ImageIcon(getClass().getResource(Constants.border)).getImage();
        graphics.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
        
        graphics.setFont(Constants.timesBold30);
        super.paint(graphics);
        
    }
    
    public void addEspecialAttackpanel(){
        remove(paenlBtn);
        
        add(panelAttacks, BorderLayout.CENTER);
        repaint();
        revalidate();
        
    }

    public PanelBtnShips getPaenlBtn() {
        return paenlBtn;
    }
    
    public void addCannoLable(){
        remove(paenlBtn);
        Title.setText("Espera tu turno");
        add(lableCannon, BorderLayout.CENTER);
    }

    public void addPanelBtn() {
        remove(panelAttacks);
        remove(lableCannon);
        add(paenlBtn, BorderLayout.CENTER);
    }
}
