package util;

public enum Messages {

	USER_NAME("Ingresa tu nombre de usuario"), USER_PASSWORD("Ingresa tu contrasenia"), LOGIN("Ingresar"),
	SIGN_IN("Registrarse"), DONT_ACCOUNT("Aun no tienes una cuenta?. Que esperas!!"),
	VERIFY_YOUR_PASSWORD("Vuelve a escribir tu contrasenia"), CAPTCHA("Ingresa las letras que estan en la imagen"),
	BACK("Atras"), USER_NOT_FOUND("Usuario o contrasenia incorrectos"), CLASIC_GAME("Modo clasico"),
	CUSTOM_GAME("Modo personalizado"), LOG_OUT("Cerrar Sesion"),
        INSTRUCTIONS("<html><body>¡¡ORGANIZA TU ARMADA!!  <br>  - coloca todos tus barcos  <br> - deben estar a un cuadro de distancia entra ellos <html><body>");

	String text = "";

	private Messages(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
