package util;

import java.awt.Font;

import javax.swing.JLabel;


public class Utilities {
	
	public static final Font TITLE = new Font("Old English Text MT", Font.PLAIN, 17);
	public static final Font OTHER_TEXT = new Font("Modern No. 20", Font.PLAIN, 17);
	
	
	public static JLabel voidLabel() {
		return new JLabel();
	}
}
