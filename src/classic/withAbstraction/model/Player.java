package classic.withAbstraction.model;

import classic.withAbstraction.util.Event;
import classic.withAbstraction.util.Subject;

public class Player extends Subject{

	enum Action implements Event{
		TAKE_DAMAGE,
		JUMP,
		DIE,
	}
	
	private String name;
	private float health;
	
	public Player(String subId, String name, float health) {
		super(subId);
		this.name = name;
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public float getHealth() {
		return health;
	}
	
	public void takeDamage(float howMuch) {
		health -= howMuch;
		notifyObservers(Action.TAKE_DAMAGE);
	}
}
