package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Actions;
import controller.Constants;
import util.Messages;
import util.Utilities;

public class NewGamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel, pageStartPanel, buttonPanel, componentsPanel;
	private JButton customGame, clasicGame, logOut;
	private JLabel image;

	public NewGamePanel(ActionListener listener) {
		setLayout(new BorderLayout());
		initComponents(listener);
		setOpaque(false);
	}

	private void initComponents(ActionListener listener) {

		pageStartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pageStartPanel.setOpaque(false);
		pageStartPanelComponents(listener);
		add(pageStartPanel, BorderLayout.PAGE_START);

		centerPanel = new JPanel(new GridLayout(1, 3));
		centerPanel.setOpaque(false);
		centerPanelComponents(listener);
		add(centerPanel);
	}

	public void pageStartPanelComponents(ActionListener listener) {
		pageStartPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,30));
		logOut = new JButton(Messages.LOG_OUT.toString());
		logOut.addActionListener(listener);
		logOut.setActionCommand(Actions.LOG_OUT.toString());
                logOut.setBackground(Color.decode(Constants.ColorWood));
                logOut.setFocusable(false);
		pageStartPanel.add(logOut);
	}

	public void centerPanelComponents(ActionListener listener) {

		centerPanel.add(new JLabel());
		componentsPanel = new JPanel(new BorderLayout());
		componentsPanel.setOpaque(false);
		componentsPanel(listener);
		centerPanel.add(componentsPanel);
		centerPanel.add(new JLabel());

	}

	public void componentsPanel(ActionListener listener) {

		image = new JLabel(new ImageIcon(getClass().getResource("/images/Logo.png")));
		componentsPanel.add(image, BorderLayout.PAGE_START);

		buttonPanel = new JPanel(new GridLayout(5, 1, 10, 20));
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		buttonPanelComponents(listener);
		componentsPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	public void buttonPanelComponents(ActionListener listener) {

		clasicGame = new JButton(Messages.CLASIC_GAME.toString());
                clasicGame.addActionListener(listener);
                clasicGame.setActionCommand(Actions.CLASIC_GAME.toString());
		clasicGame.setFont(Utilities.OTHER_TEXT);
		clasicGame.setContentAreaFilled(false);
		clasicGame.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonPanel.add(clasicGame);

		customGame = new JButton(Messages.CUSTOM_GAME.toString());
		customGame.setFont(Utilities.OTHER_TEXT);
		customGame.addActionListener(listener);
		customGame.setContentAreaFilled(false);
		customGame.setBorder(BorderFactory.createLineBorder(Color.black));
		customGame.setActionCommand(Actions.CUSTOM_GAME.toString());
		buttonPanel.add(customGame);
	}

}
