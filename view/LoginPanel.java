package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Actions;
import model.entity.Player;
import util.Messages;
import util.Utilities;

public class LoginPanel extends JPanel {

private static final long serialVersionUID = 1L;

	private JTextHolder userName;
	private JPassworHolder userPassword;
	private JButton login, signIn;
	private JLabel imageLabel, dontAccount;
	private JPanel centerPanel;

	public LoginPanel(ActionListener listener) {
		setLayout(new GridLayout(1, 3));
		setBorder(BorderFactory.createEmptyBorder(20, 5, 80, 5));
		setOpaque(false);
		initComponents(listener);
	}

	private void initComponents(ActionListener listener) {
		add(Utilities.voidLabel());
		centerPanel = new JPanel(new GridLayout(8, 1, 10, 30));
		centerPanel.setOpaque(false);

		centerPanelComponents(listener);
		add(centerPanel);
		add(Utilities.voidLabel());
	}

	private void centerPanelComponents(ActionListener listener) {
		imageLabel = new JLabel();
		centerPanel.add(imageLabel);

		userName = new JTextHolder();
                userName.setPlaceHolder("Nombre de usuario");
		userName.setFont(Utilities.OTHER_TEXT);
		centerPanel.add(userName);

		userPassword = new JPassworHolder();
                userPassword.setPlaceHolder("Contrasenia");
		userPassword.setFont(Utilities.OTHER_TEXT);
		centerPanel.add(userPassword);

		login = new JButton(Messages.LOGIN.toString());
		login.setFont(Utilities.OTHER_TEXT);
		login.setContentAreaFilled(false);
		login.setBorder(BorderFactory.createLineBorder(Color.black));
		login.setActionCommand(Actions.LOGIN.toString());
		login.addActionListener(listener);
		centerPanel.add(login);

		dontAccount = new JLabel(Messages.DONT_ACCOUNT.toString());
		dontAccount.setFont(Utilities.OTHER_TEXT);
		dontAccount.setHorizontalAlignment(JLabel.CENTER);
		centerPanel.add(dontAccount);

		signIn = new JButton(Messages.SIGN_IN.toString());
		signIn.setContentAreaFilled(false);
		signIn.setBorder(BorderFactory.createLineBorder(Color.black));
		signIn.addActionListener(listener);
		signIn.setActionCommand(Actions.SHOW_SIGN_IN_PANEL.toString());
		signIn.setFont(Utilities.OTHER_TEXT);
		centerPanel.add(signIn);
	}

	public String getLoginUserName() {
		return userName.getText();
	}

	public String getLoginPassword() {
		return String.valueOf(userPassword.getPassword());
	}

}
