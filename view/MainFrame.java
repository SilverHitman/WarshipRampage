package view;

import view.map.PanelMap;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import model.entity.Player;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;

	public MainFrame(ActionListener listener) {

		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents(listener);
		setVisible(true);

	}
        
        public String getLoginUserName() {
		return mainPanel.getLoginUserName();
	}
        
        public String getLoginPassword() {
		return mainPanel.getLoginPassword();
	}
        
        public void setCaptchaImage(BufferedImage captcha) {
		mainPanel.setCaptchaImage(captcha);
	}
        
        public String getSignInUserName() {
		return mainPanel.getSignInUserName();
	}

	public String getSingInPass() {
		return mainPanel.getSingInPass();
	}
        
        public void setNumbersShips(String ships1x1,String ships1x2,String ship1x3, String ship1x4){
            mainPanel.getPanelMainShips().getPanelInfo().getPaenlBtn().setNewInfoShips(ships1x1, ships1x2, ship1x3, ship1x4);
        }
//
//	public void setCaptchaMessage(String captchaMessage) {
//		mainPanel.setCaptchaMessage(captchaMessage);
//	}

	private void initComponents(ActionListener listener) {
		mainPanel = new MainPanel(listener);
		add(mainPanel);
	}

	public void addSignInPanel() {
		mainPanel.addSingInPanel();
	}
        
        public void addPanelMainShips(int weidth, int heigth, MouseListener mouse, ActionListener action){
            mainPanel.addPanelMainShips(weidth, heigth, mouse, action);
        }

	public void addLoginPanel() {
		mainPanel.addLoginPanel();
	}
        
        public PanelMap getPanelmap(){
            return mainPanel.getPanelMap();
        }

//	public Player login() {
//		return mainPanel.login();
//	}
//
//        
        public String getCaptcha() {
		return mainPanel.getCaptcha();
	}
        
	public void addNewGamePanel() {
		mainPanel.addNewGamePanel();
	}
//	
//	public String getUserName() {
//		return mainPanel.getUserName();
//	}
//        
//        
//
//	public String getUserPass() {
//		return  mainPanel.getUserPass();
//	}

	public String getVerifyPass() {
		return  mainPanel.getVerifyPass();
	}

    public void addPanelBtn() {
        mainPanel.getPanelMainShips().getPanelInfo().addPanelBtn();
    }


    public void addCannon() {
        mainPanel.getPanelMainShips().getPanelInfo().addCannoLable();
    }

    public void showInfoTurn(String namePla1,String namePla2,String nameActualTurn) {
        mainPanel.getPanelMainShips().getTopPanel().SetInfoTurn(namePla1, namePla2, nameActualTurn);
    }

    public void addSpecialCannon() {
        mainPanel.getPanelMainShips().getPanelInfo().addEspecialAttackpanel();
    }

    public void addPanelScores() {
        mainPanel.addBestPlayersPanel();
    }
}
