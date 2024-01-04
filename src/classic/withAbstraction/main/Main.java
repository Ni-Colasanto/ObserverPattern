package classic.withAbstraction.main;

import classic.withAbstraction.model.Narrator;
import classic.withAbstraction.model.Player;

public class Main {

	public static void main(String[] args) {
		Player player = new Player("player","hero", 100f);
		Narrator narrator = new Narrator("narrator");
		player.addObserver(narrator);
		player.takeDamage(10);
	}
}
