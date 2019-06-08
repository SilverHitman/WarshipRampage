/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Actions;
import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.entity.Player;

/**
 *
 * @author Andres Gonzalez
 */
public class PanelScores extends JPanel{

    private JTable jtablePlayers;
    private DefaultTableModel dtmElements;
    private JScrollPane jscrollTable;
    private JButton back;
    
    public PanelScores(ActionListener action) {
        setBorder(BorderFactory.createEmptyBorder(20, 300, 80, 300));
        setOpaque(false);
        
        setLayout(new BorderLayout());
        dtmElements = new DefaultTableModel();
        
        String[] headers = {"Jugador", "victorias"};
        dtmElements.setColumnIdentifiers(headers);
        
        back = new JButton("volver");
            back.setFont(Constants.timesBold16);
            back.addActionListener(action);
            back.setBackground(Color.decode(Constants.ColorWood));
            back.setActionCommand(Actions.SHOW_LOGIN_IN_PANEL.toString());
        add(back, BorderLayout.NORTH);
        
        jtablePlayers = new JTable(dtmElements);
        jtablePlayers.getTableHeader().setBackground(Color.decode(Constants.ColorWood));
	jtablePlayers.getTableHeader().setFont(Constants.timesBold16);
        jscrollTable = new JScrollPane(jtablePlayers);
      
        jscrollTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jscrollTable, BorderLayout.CENTER);
    }
    
    public void fillTableEspecie(Player[] lsitplayer) {
        dtmElements.setRowCount(0);
        for (int i = 0; i < lsitplayer.length; i++) {

            dtmElements.addRow(new Object[] { lsitplayer[i].getUserName(), 0});
        }
        repaint();
        revalidate();
    }
    
    
    
}
