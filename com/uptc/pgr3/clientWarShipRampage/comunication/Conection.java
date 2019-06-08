package com.uptc.pgr3.clientWarShipRampage.comunication;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Conection extends Thread {

	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private IObserver iObserver;
	private Socket socket;
	private String captchaMessage;

	private boolean disconect;

	public Conection(String ip, int port, IObserver iObserver) throws IOException {
		this.iObserver = iObserver;
		socket = new Socket(ip, port);
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}

	public void requestvalidateRegistry(String userName, String captcha) throws IOException {
		dataOutputStream.writeUTF(Request.VALIDATE_REGISTRY.name());
		dataOutputStream.writeUTF(userName);
		dataOutputStream.writeUTF(captcha);
	}

	public void sentInitValues(String nickName, String passUser) throws IOException {
		dataOutputStream.writeUTF(Request.INIT_VALUES.name());
		dataOutputStream.writeUTF(nickName);
		dataOutputStream.writeUTF(passUser);
	}

	private void receiveValidateRegistry() throws IOException {
		this.iObserver.receiveValidationRegistry(dataInputStream.readBoolean(), dataInputStream.readBoolean());
	}

	public void requestGetCaptcha() throws IOException {
		dataOutputStream.writeUTF(Request.GET_CAPTCHA.name());
	}

	private void receivedCaptcha() throws IOException {
		byte[] byteImage = null;
		int length = dataInputStream.readInt();
		if (length > 0) {
			byteImage = new byte[length];
			dataInputStream.readFully(byteImage, 0, byteImage.length);
		}
		ByteArrayInputStream bytes = new ByteArrayInputStream(byteImage);
		iObserver.addCaptchaMessage(ImageIO.read(bytes));
	}

	public void requesValidateLogin(String userName, String password) throws IOException {
		dataOutputStream.writeUTF(Request.VALIDATE_LOGIN.name());
		dataOutputStream.writeUTF(userName);
		dataOutputStream.writeUTF(password);
	}

	private void receiveValidateLogin() throws IOException {
		iObserver.receiveValidationLogin(dataInputStream.readLong());
	}
        
        public void requestPlayerGame(String playerGame) throws IOException{
            dataOutputStream.writeUTF(Request.PLAYER_GAME.name());
            dataOutputStream.writeUTF(playerGame);
        }

	public synchronized void close() throws IOException {
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
		disconect = true;
	}

	private void manageResponse(String response) throws IOException {
		switch (Response.valueOf(response)) {
		case VALIDATE_LOGIN:
			receiveValidateLogin();
			break;
		case VALIDATE_REGISTRY:
			receiveValidateRegistry();
			break;
		case GET_CAPTCHA:
			receivedCaptcha();
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		while (!disconect) {
			try {
				String response = dataInputStream.readUTF();
				if (response != null) {
					manageResponse(response);
				}
			} catch (IOException e) {
				try {
					close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public String getCaptchaMessage() {
		return captchaMessage;
	}

}
