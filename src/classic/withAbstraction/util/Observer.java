package classic.withAbstraction.util;

public interface Observer {
	
	// Called by subject to notify the action
	void onNotify(Event event);
	
	// To identify the observer
	String getID();
}
