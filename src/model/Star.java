package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import util.Observer;
import util.Subject;

public class Star implements Subject{

	private List<Observer> observers;
	private String message;
	private boolean changed;
	private String name;
	
	
	public Star(String name) {
		observers = new ArrayList<Observer>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	@Override
	public boolean addObserver(Observer observer) {
		if(Objects.isNull(observer)) { 
			System.err.println("Null observer");
			return false;
		}
		
		if(observers.contains(observer)) {
			System.out.println("Observer is already registerd");
			return false;
		} 
		
		return observers.add(observer);
	}

	@Override
	public boolean deleteObserver(Observer observer) {
		if(observers.remove(observer)) {
			System.out.println("Observer is removed");
			return true;
		}
		System.out.println("Observer is not in the list of observers");
		return false;
	}

	@Override
	public boolean notifyObservers() {
		if(!changed) {
			System.out.println("The state is not changed");
			return false;
		}
		
		observers.forEach((obs) -> {
			obs.update(this);
		});
		changed = false;
		return true;
	}

	@Override
	public Object getUpdate(Observer observer) {
		return this.message;
	}
	
	public void postMessage(String msg) {
		message = msg;
		System.out.println("Message posted from [%s] -> %s".formatted(name ,message));
		changed = true;
		notifyObservers();
	}
}
