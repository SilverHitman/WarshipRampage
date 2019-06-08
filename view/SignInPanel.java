package view;

import controller.Actions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Messages;
import util.Utilities;

public class SignInPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextHolder userName,captchaField;
	private JPassworHolder userPassword, verifyPassword;
	private JButton signIn, back;
	private JPanel formulerPanel;
	private JPanel centerPanel;
	private JPanel pageStartPanel;
	private JLabel emptyLabel;
	private JLabel emptyLabel2;
	private JLabel captchaImage;

	public SignInPanel(ActionListener listener) {
		setLayout(new BorderLayout());
		setOpaque(false);
		initComponents(listener);
	}

	private void initComponents(ActionListener listener) {

		emptyLabel = new JLabel();
		emptyLabel2 = new JLabel();

		pageStartPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pageStartPanel.setOpaque(false);
		pageStartPanelComponents(listener);
		add(pageStartPanel, BorderLayout.PAGE_START);

		centerPanel = new JPanel(new GridLayout(1, 3));
		centerPanel.setOpaque(false);
		centerPanelComponents(listener);
		add(centerPanel, BorderLayout.CENTER);
	}

	private void pageStartPanelComponents(ActionListener listener) {
		back = new JButton(Messages.BACK.toString());
//		back.setContentAreaFilled(false);
//		back.setBorder(BorderFactory.createLineBorder(Color.black));
		back.setFont(Utilities.OTHER_TEXT);
		back.addActionListener(listener);
		back.setActionCommand(Actions.SHOW_LOGIN_IN_PANEL.toString());
		pageStartPanel.add(back);
	}

	private void formulerPanelComponents(ActionListener listener) {
		userName = new JTextHolder();
                userName.setPlaceHolder("Nombre de usuario");
		userName.setFont(Utilities.OTHER_TEXT);
		formulerPanel.add(userName);

		userPassword = new JPassworHolder();
                userPassword.setPlaceHolder(TOOL_TIP_TEXT_KEY);
		userPassword.setFont(Utilities.OTHER_TEXT);
		formulerPanel.add(userPassword);

		verifyPassword = new JPassworHolder();
                verifyPassword.setPlaceHolder("Vuelve a escribir tu contrase�a");
		verifyPassword.setFont(Utilities.OTHER_TEXT);
		formulerPanel.add(verifyPassword);

		captchaImage = new JLabel();
		formulerPanel.add(captchaImage);

		captchaField = new JTextHolder();
                captchaField.setPlaceHolder("Ingresa las letras que estan en la imagen");
                
		captchaField.setFont(Utilities.OTHER_TEXT);
		formulerPanel.add(captchaField);

		signIn = new JButton(Messages.SIGN_IN.toString());
		signIn.setContentAreaFilled(false);
		signIn.addActionListener(listener);
		signIn.setActionCommand(Actions.SING_IN.toString());
		signIn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		signIn.setFont(Utilities.OTHER_TEXT);
		formulerPanel.add(signIn);

	}

	private void centerPanelComponents(ActionListener listener) {
		centerPanel.add(emptyLabel);
		formulerPanel = new JPanel(new GridLayout(8, 1, 10, 25));
		formulerPanel.setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(20, 5, 80, 5));
		formulerPanelComponents(listener);
		centerPanel.add(formulerPanel);
		centerPanel.add(emptyLabel2);
	}

	public void setCaptchaImage(BufferedImage image) {
		captchaImage.setIcon(new ImageIcon(image));
		captchaImage.setHorizontalAlignment(JLabel.CENTER);
		captchaImage.setVerticalAlignment(JLabel.CENTER);
	}

	public String getCaptcha() {
		return captchaField.getText();
	}

	public String getSingInUserName() {
		return userName.getText();
	}

	public String getSingInPass() {
		return String.valueOf(userPassword.getPassword());
	}

	public String getVerifyPass() {
		return String.valueOf(verifyPassword.getPassword());
	}

}
