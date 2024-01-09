package eventBased.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EventManager {
	private static EventManager instance;
	Map<EventType, List<EventListener>> events; 
	
	private EventManager() {
		events = new HashMap<>(); 
	}
	
	public static EventManager getInstance() { 
		if(instance == null) { 
			instance = new EventManager();
		}
		return instance;
	}

	public boolean addEventType(EventType eventType) {
		if(Objects.nonNull(events.putIfAbsent(eventType, new ArrayList<>()))) {
			System.out.println("Event type [%s] is alread registered".formatted(eventType));
			return false;
		}
		System.out.println("Event type [%s] added".formatted(eventType));
		return true;
	}
	
	public boolean subscribe(EventType eventType, EventListener listener) {
		if(!events.containsKey(eventType)) {
			System.out.println("No such event type [%s]".formatted(eventType));
			return false;
		}
		events.get(eventType).add(listener);
		System.out.println("Listener [%s] added to the event type [%s]".formatted(listener, eventType));
		return true;	
	}

	public boolean unsubscribe(EventType eventType, EventListener listener) {
		List<EventListener> listeners = events.get(eventType);
		if(!Objects.nonNull(listeners)) {
			System.out.println("No such event type [%s]".formatted(eventType));
			return false;
		}
		if(!listeners.remove(listener)) {
			System.out.println("No such listener [%s] is registered".formatted(listener));
			return false;
		}
		System.out.println("Listener [%s] removed from the event type [%s]".formatted(listener, eventType));
		return true;
	}

	public void notify(EventType eventType, Object data) {
		events.get(eventType).forEach(listener -> listener.onNotify(eventType, data));
	}
}
