package com.uptc.pgr3.clientWarShipRampage.exceptions;

public class Exceptions {

	public void validatePasswords(String pass1, String pass2) throws MyException {
		if (!pass1.equals(pass2)) {
			throw new MyException("Las contraseñas no cohinciden");
		}
	}
	
	public void wrongCaptcha(boolean captcha) throws MyException {
		if (!captcha) {
			throw new MyException("Captcha incorrecto");
		}
	}
	
	public void existUserName(boolean isValid) throws MyException {
		if (!isValid) {
			throw new MyException("Nombre de usuario ya en uso");
		}
	}

	public void emptyFields(String text) throws MyException {
		if (text.isEmpty()) {
			throw new MyException("Faltan campos por completar");
		}
	}
	
	public void wrongPlayer(long  points) throws MyException {
		if (points == -1) {
			throw new MyException("Nombre de usuario o contraseña incorrecta");
		}
	}
	
	public void passWordSize(String text) throws MyException {
		if (text.length() < 8) {
			throw new MyException("La contraseña debe contener minimo 8 caracteres");
		}
	}

}
