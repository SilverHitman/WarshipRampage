package model.manager;

import java.util.ArrayList;
import java.util.Random;

import model.entity.Player;

public class PlayerManager {

	private ArrayList<Player> players;

	private static final String[] words = new String[] { "Juan", "Hola", "Hola", "Perro", "Gato", "Silla", "Mesa" };

	public PlayerManager() {
		players = new ArrayList<>();
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public boolean userNameAlready(String userName) {
		for (Player player : players) {
			if (player.getUserName().equalsIgnoreCase(userName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isExist(Player playerLogin) {
		for (Player player : players) {
			if (player.getUserName().equalsIgnoreCase(playerLogin.getUserName())
					&& player.getUserPassword().equals(playerLogin.getUserPassword())) {
				return true;
			}
		}
		return false;
	}

	public String generateWord() {
		Random random = new Random();
		return words[random.nextInt(words.length - 1)];
	}

}
