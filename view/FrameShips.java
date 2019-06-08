
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameShips extends JFrame{

    private PanelMap mapUser;
    private PanelInfoShips panelInfo;
    
    
    public FrameShips() {
        
        
        setTitle("Warship Rampage");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        
        setLayout(new BorderLayout());
        
        panelInfo = new PanelInfoShips();
        
        add(panelInfo, BorderLayout.WEST);
        
        //add(panup, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    
    public void createMapPlayer(int we, int hei){
        mapUser = new PanelMap(we,hei);
        
        add(mapUser, BorderLayout.CENTER);
    }


    public void setnewShipsPlayer(ArrayList listShips) {
        mapUser.setShips(listShips);
    }
    
    public void setMarkPlayer(ArrayList listmark){
        mapUser.setListMarks(listmark);
    }
    
    
}
