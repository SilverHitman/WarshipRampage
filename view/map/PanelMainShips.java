
package view.map;

import com.sun.glass.ui.Size;
import controller.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.map.PanelMap;
import view.map.TopPanel;

public class PanelMainShips extends JPanel{
    
    private static final long serialVersionUID = 1L;

    private PanelMap mapUser;
    private PanelInfoShips panelInfo;
    private PanelAttacks panelattacks;
    
    private JPanel panelCenter;
    private TopPanel topPanel;
    
    public PanelMainShips(ActionListener action) {

        setBackground(Color.decode(Constants.ColorWood));
        //setMinimumSize(new Dimension(1000, 600));

        setLayout(new BorderLayout());
        panelInfo = new PanelInfoShips(action);
        add(panelInfo, BorderLayout.WEST);

        panelCenter = new JPanel();
            panelCenter.setLayout(new BorderLayout());
            mapUser = new PanelMap(0,0, null, action );
            panelCenter.add(mapUser, BorderLayout.CENTER);
        add(panelCenter, BorderLayout.CENTER);
            
        topPanel = new TopPanel(action);
            topPanel.setOpaque(true);
        panelCenter.add(topPanel, BorderLayout.NORTH);


        setOpaque(true);
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public void createMapPlayer(int we, int hei, MouseListener mouse, ActionListener action){
        panelCenter.remove(mapUser);
        mapUser = new PanelMap(we,hei, mouse, action );
        
        panelCenter.add(mapUser, BorderLayout.CENTER);
    }

    public PanelMap getMapUser() {
        return mapUser;
    }

    public PanelInfoShips getPanelInfo() {
        return panelInfo;
    }

    public void setnewShipsPlayer(ArrayList listShips) {
        mapUser.setShips(listShips);
    }
    
    public void setMarkPlayer(ArrayList listmark){
        mapUser.setListMarks(listmark);
    }
    
    
}
