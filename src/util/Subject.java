package util;

public interface Subject {
	
	// Register observer
	boolean addObserver(Observer observer);
	
	// Unregister observer
	boolean deleteObserver(Observer observer);
	
	// Notify all observers
	boolean notifyObservers();
	
	Object getUpdate(Observer observer);
	
}
