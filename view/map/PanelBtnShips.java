
package view.map;

import controller.Constants;
import controller.Actions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.BtnStandar;
import view.BtnStandar;

public class PanelBtnShips extends JPanel{
    
    private String title, ships1x1,ships1x2, ship1x3,ship1x4,  shipPoints;
    
    private BtnStandar btn1x1, btn1x2V, btn1x2H, btn1x3V,btn1x3H, btn1x4V, btn1x4H;
    private JLabel lableInfo2,lableInfo3,lableInfo4, lableInfo5;
    public PanelBtnShips(ActionListener action) {
        setOpaque(false);
        init(action);
    }
    
    
    public void setNewInfoShips(String ships1x1,String ships1x2,String ship1x3, String ship1x4){
        this.ships1x1 = ships1x1;
        this.ships1x2 = ships1x2;
        this.ship1x3 =ship1x3;
        this.ship1x4 = ship1x4;
        lableInfo2.setText(ships1x1);
        lableInfo3.setText(ships1x2);
        lableInfo4.setText(ship1x3);
        lableInfo5.setText(ship1x4);
        repaint();
        revalidate();
    }
    
    

    
    
    private void init(ActionListener action){
        title = "Punatajes";
        shipPoints = "total points: 1/2";
        ships1x1 = "1x1 ship 1/4";
        ships1x2 = "1x2 ship 1/4";
        ship1x3 = "1x3 ship 1/4";
        
        //setBackground(Color.decode(Constants.ColorWood));
        //setBorder(new MatteBorder(3, 3,3, 3, Color.GRAY));
        
        setOpaque(false);
        
        setLayout(new GridLayout(5, 3,3,3));
    
        JLabel lableInfo = new JLabel("tipo barco");
            lableInfo.setFont(Constants.timesBold16);
            //lableInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        
         add(lableInfo);
        
        JLabel lableInfoVer = new JLabel("vertical");
            lableInfoVer.setFont(Constants.timesBold16);
        add(lableInfoVer);  
        
        JLabel lableInfoHor = new JLabel("horizontal");
            lableInfoHor.setFont(Constants.timesBold16);
        add(lableInfoHor);

        lableInfo2 = new JLabel(ships1x1);
        
        
        add(lableInfo2);

        btn1x1 = new BtnStandar("", "/resources/images/ships/verticalShips/GunShip/Operative.png");
            btn1x1.addActionListener(action);
            btn1x1.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x1.setName("/resources/images/ships/verticalShips/GunShip/Operative.png");
            btn1x1.setBackground(Color.decode(Constants.ColorWood));
            btn1x1.setContentAreaFilled(false);
        add(btn1x1);
        
        
        JLabel lableVoid = new JLabel("");
        
        add(lableVoid);
        
        lableInfo3 = new JLabel(ships1x2);
        
        
        add(lableInfo3);
        
        btn1x2V = new BtnStandar("", "/resources/images/ships/verticalShips/Brig/Operative.png");
            btn1x2V.addActionListener(action);
            btn1x2V.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x2V.setName("/resources/images/ships/verticalShips/Brig/Operative.png");
            btn1x2V.setBackground(Color.decode(Constants.ColorWood));
            btn1x2V.setContentAreaFilled(false);
        add(btn1x2V);
        
        
        btn1x2H = new BtnStandar("", "/resources/images/ships/horizontalShips/Brig/Operative.png");
            btn1x2H.addActionListener(action);
            btn1x2H.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x2H.setName("/resources/images/ships/horizontalShips/Brig/Operative.png");
            btn1x2H.setBackground(Color.decode(Constants.ColorWood));
            btn1x2H.setContentAreaFilled(false);
        add(btn1x2H);
        
        lableInfo4 = new JLabel(ship1x3);
        
        
        add(lableInfo4);

        btn1x3V = new BtnStandar("", "/resources/images/ships/verticalShips/Frigate/Operative.png");
            btn1x3V.addActionListener(action);
            btn1x3V.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x3V.setName("/resources/images/ships/verticalShips/Frigate/Operative.png");
            btn1x3V.setBackground(Color.decode(Constants.ColorWood));
            btn1x3V.setContentAreaFilled(false);
        add(btn1x3V);

        btn1x3H = new BtnStandar("", "/resources/images/ships/horizontalShips/Frigate/Operative.png");
            btn1x3H.addActionListener(action);
            btn1x3H.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x3H.setName("/resources/images/ships/horizontalShips/Frigate/Operative.png");
            btn1x3H.setBackground(Color.decode(Constants.ColorWood));
            btn1x3H.setContentAreaFilled(false);
        add(btn1x3H);
        
        lableInfo5 = new JLabel(ship1x4);
        
        
        add(lableInfo5);

        btn1x4V = new BtnStandar("", "/resources/images/ships/verticalShips/ManOfWar/Operative.png");
            btn1x4V.addActionListener(action);
            btn1x4V.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x4V.setName("/resources/images/ships/verticalShips/ManOfWar/Operative.png");
            //btn1x4V.setBackground(Color.decode(Constants.ColorWood));
            btn1x4V.setContentAreaFilled(false);
        add(btn1x4V);

        btn1x4H = new BtnStandar("", "/resources/images/ships/horizontalShips/ManOfWar/Operative.png");
            btn1x4H.addActionListener(action);
            btn1x4H.setActionCommand(Actions.SHIP_PRESSED.toString());
            btn1x4H.setName("/resources/images/ships/horizontalShips/ManOfWar/Operative.png");
            btn1x4H.setBackground(Color.decode(Constants.ColorWood));
            btn1x4H.setContentAreaFilled(false);
        add(btn1x4H);
        
    }
    
    
}
