/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.Actions;
import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Andres Gonzalez
 */
public class DialogCustomGame extends JDialog{
    
    private JSpinner spinerCells;
    private SpinnerNumberModel numberspiner;
    private JButton btnCerateGame;

    public DialogCustomGame(ActionListener actionListener) {
        
        setBackground(Color.decode(Constants.ColorWood));
        setLocationRelativeTo(null);
        setSize(200, 150);
        setLayout(new GridLayout(3, 1, 0,0));
        
        numberspiner = new SpinnerNumberModel(10, 8, 24, 2);
        
        JLabel lableMessage = new JLabel("          ingresa el ancho");
            lableMessage.setFont(Constants.timesBold16);
            lableMessage.setBackground(Color.decode(Constants.ColorWood));
            lableMessage.setOpaque(true);
        add(lableMessage);
        
        spinerCells = new JSpinner(numberspiner);
            spinerCells.setOpaque(true);
            spinerCells.setBackground(Color.decode(Constants.ColorWood));
        add(spinerCells);
        
        btnCerateGame = new JButton("Crear tablero");
            btnCerateGame.addActionListener(actionListener);
            btnCerateGame.setActionCommand(Actions.CREATE_CUSTOM_GAME.toString());
            btnCerateGame.setOpaque(true);
            btnCerateGame.setBackground(Color.decode(Constants.ColorWood));
        add(btnCerateGame);
        
    }
    
    public int getWidthDialog(){
        return (int) numberspiner.getNumber();
    }
    
    
    
}
