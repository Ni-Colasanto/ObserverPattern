package util;

import java.util.Map;

public interface Subject {
	
	// Register observer
	boolean addObserver(Observer observer);
	
	// Unregister observer
	boolean deleteObserver(Observer observer);
	
	// Notify all observers
	boolean notifyObservers();
	
	Map<?,?> getData();
}
