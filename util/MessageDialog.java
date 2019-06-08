package util;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessageDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel messageLabel;
	
	public MessageDialog(JFrame frame) {
		super(frame);
		setLayout(new BorderLayout());
		setLocationRelativeTo(frame);
		setSize(400, 200);
		initComponents();
	}

	private void initComponents() {
		messageLabel = new JLabel();
		messageLabel.setFont(Utilities.OTHER_TEXT);
		add(messageLabel);
	}
	
	public void setMessage(String message) {
		this.messageLabel.setText(message);
	}
	
}
