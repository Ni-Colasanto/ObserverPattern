package classic.withAbstraction.model;

import classic.withAbstraction.util.Event;
import classic.withAbstraction.util.Observer;

public class Narrator implements Observer{

	private String id;
	
	public Narrator(String id) { 
		this.id = id;
	}

	@Override
	public String getID() {
		return id;
	}
	
	@Override
	public void onNotify(Event action) {
		System.out.println("Player Narration System: PLAYER HAS %s".formatted(action.toString()));
	}

}
