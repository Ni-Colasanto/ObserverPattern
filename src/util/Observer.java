package util;

import java.util.Map;

public interface Observer{
	// Update the observer, used by subject
	void update(Map<?,?> message);
	
	String getName();
}
