package com.uptc.pgr3.clientWarShipRampage.comunication;

import java.awt.image.BufferedImage;

public interface IObserver {
	
	public void receiveValidationRegistry(boolean userName, boolean captcha);
	public void receiveValidationLogin(long points);
	public void addCaptchaMessage(BufferedImage captcha);
}
