/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import view.BtnStandar;

/**
 *
 * @author Andres Gonzalez
 */
public class PanelAttacks extends JPanel{
    
    private BtnStandar btnAttack1, btnAttack2, btnAttack3;

    public PanelAttacks(ActionListener action) {
        setLayout(new BorderLayout());
        LableCannon lalbea = new LableCannon();
            
        add(lalbea, BorderLayout.NORTH);
        
        JPanel panelButtons = new JPanel();
            panelButtons.setLayout(new GridLayout(3, 1));
            btnAttack1 = new BtnStandar("Lluvia de ballas", "");
                btnAttack1.setFont(Constants.timesBold16);
                btnAttack1.setForeground(Color.BLACK);
                btnAttack1.setBackground(Color.decode(Constants.ColorWood));
            panelButtons.add(btnAttack1);

            btnAttack2 = new BtnStandar("Bombardeo", "");
                btnAttack2.setFont(Constants.timesBold16);
                btnAttack2.setBackground(Color.decode(Constants.ColorWood));
                btnAttack2.setForeground(Color.BLACK);
            panelButtons.add(btnAttack2);

            btnAttack3 = new BtnStandar("Racimo", "");
                btnAttack3.setFont(Constants.timesBold16);
                btnAttack3.setBackground(Color.decode(Constants.ColorWood));
                btnAttack3.setForeground(Color.BLACK);
            panelButtons.add(btnAttack3);
        add(panelButtons, BorderLayout.CENTER);
    }

    
    
   
    
    
}
