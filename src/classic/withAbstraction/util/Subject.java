package classic.withAbstraction.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Subject {
	
	private List<Observer> observers;
	private String id;
	
	public Subject(String id) {
		observers = new ArrayList<Observer>();
		this.id = id;
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public String getID() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addObserver(Observer observer) {
		Objects.requireNonNull(observer, "Null observer");
		
		String obsID = observer.getID();
		
		if(observers.contains(observer)) {
			System.out.println("Observer [%s] is already in the list of [%s] subject".formatted(obsID, getID()));
			return;
		}
		
		observers.add(observer);
		System.out.println("Observer [%s] is added in the list of [%s] subject".formatted(obsID, getID()));
	}
	
	public void removeObserver(Observer observer) {
		Objects.requireNonNull(observer, "Null observer");
		
		String obsID = observer.getID();
		
		if(!observers.contains(observer)) {
			System.out.println("Observer [%s] is not in the list of [%s] subject".formatted(obsID, getID()));
			return;
		}
		
		observers.add(observer);
		System.out.println("Observer [%s] is removed from the list of [%s] subject".formatted(obsID, getID()));
	}
	
	public void NotifyObservers(Event event) { 
		observers.forEach((observer) -> observer.onNotify(event));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return Objects.equals(id, other.id);
	}
	
}
