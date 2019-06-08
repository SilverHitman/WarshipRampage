package view;

import view.map.PanelMainShips;
import view.map.PanelMap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.entity.Player;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel;
	private SignInPanel signInPanel;
	private NewGamePanel newGamePanel;
        private PanelMainShips panelMainShips;
        private PanelScores panelScores;
	private Image backgroundImg;

	public MainPanel(ActionListener listener) {
		setLayout(new BorderLayout());
		backgroundImg = new ImageIcon(getClass().getResource("/images/oldpaper.jpg")).getImage();
		initComponents(listener);
	}

	private void initComponents(ActionListener listener) {
                panelScores = new PanelScores(listener);
                panelMainShips = new PanelMainShips(listener);
		loginPanel = new LoginPanel(listener);
		add(loginPanel);

		signInPanel = new SignInPanel(listener);
		newGamePanel = new NewGamePanel(listener);
	}
        
        public PanelMap getPanelMap(){
            return panelMainShips.getMapUser();
        }

    public PanelMainShips getPanelMainShips() {
        return panelMainShips;
    }
        

        public void addPanelMainShips(int weidth, int heigth, MouseListener mouse, ActionListener action){
            removeAll();
            panelMainShips.createMapPlayer(weidth, heigth, mouse, action);
            add(panelMainShips);
            repaint();
            revalidate();
            
        }

	public void addSingInPanel() {
		removeAll();
		add(signInPanel);
		repaint();
		revalidate();
	}

	public void addLoginPanel() {
		removeAll();
		add(loginPanel);
		repaint();
		revalidate();
	}
        
        public void addBestPlayersPanel(){
            removeAll();
            add(panelScores);
            repaint();
            revalidate();
        }

	public void addNewGamePanel() {
		removeAll();
		add(newGamePanel);
		repaint();
		revalidate();
	}


//	
//	public String getUserName() {
//		return signInPanel.getUserName();
//	}
//
//	public String getUserPass() {
//		return  signInPanel.getUserPass();
//	}

	public String getVerifyPass() {
		return  signInPanel.getVerifyPass();
	}
        
        public String getCaptcha() {
		return signInPanel.getCaptcha();
	}

	public String getLoginUserName() {
		return loginPanel.getLoginUserName();
	}
	
	public String getLoginPassword() {
		return loginPanel.getLoginPassword();
	}
	
	public String getSignInUserName() {
		return signInPanel.getSingInUserName();
	}

	public String getSingInPass() {
		return  signInPanel.getSingInPass();
	}
	
	public void setCaptchaImage(BufferedImage captcha) {
		signInPanel.setCaptchaImage(captcha);
	}
	@Override
	protected void paintComponent(Graphics g) {
		  super.paintComponent(g);
	        setPreferredSize(getParent().getSize());
	        g.drawImage(new ImageIcon(
	                backgroundImg.getScaledInstance(getWidth(),
	                        getHeight(), Image.SCALE_SMOOTH)).getImage(), 0, 0, this);
	}

}
