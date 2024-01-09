package eventsBased.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * It manages events to which listeners can subscribe.
 * The events it keeps track of are added and are of type EventType. 
 * Through the notify method, all listeners registered to that event will be notified.
 * Implements Singleton Pattern, there will be a single instance of this class.
 * 
 * @author Nicola Colasanto
 */
public class EventManager {
	private static EventManager instance;
	Map<EventType, List<EventListener>> events; 
	
	/**
	 * Singleton constructor
	 */
	private EventManager() {
		events = new HashMap<>(); 
	}
	
	/**
	 * Implement Singleton Pattern
	 * 
	 * @return single instance of the this class
	 */
	public static EventManager getInstance() { 
		if(instance == null) { 
			instance = new EventManager();
		}
		return instance;
	}

	/**
	 * If the event its not already tacked, add the event to the map
	 * 
	 * @param eventType Event to track 
	 * @return true, if the eventType is added 
	 */
	public boolean addEventType(EventType eventType) {
		if(Objects.nonNull(events.putIfAbsent(eventType, new ArrayList<>()))) {
			System.out.println("Event type [%s] is alread registered".formatted(eventType));
			return false;
		}
		System.out.println("Event type [%s] added".formatted(eventType));
		return true;
	}
	
	/**
	 * Subscribe the listener to the passed event type if it is present in the map.
	 * 
	 * @param eventType Event to associate the listener with
	 * @param listener 
	 * @return true, if the listener is subscribed to the event 
	 */
	public boolean subscribe(EventType eventType, EventListener listener) {
		if(!events.containsKey(eventType)) {
			System.out.println("No such event type [%s] is tracked by the event manager".formatted(eventType));
			return false;
		}
		events.get(eventType).add(listener);
		System.out.println("Listener [%s] subscribed to the event type [%s]".formatted(listener, eventType));
		return true;	
	}

	/**
	 * If it is present in the map and subscribed to the event passed, unsubscribe the listener to that event 
	 * 
	 * @param eventType 
	 * @param listener
	 * @return true, if the listener is unsubscribed to that event
	 */
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
		System.out.println("Listener [%s] unsubscribed to the event type [%s]".formatted(listener, eventType));
		return true;
	}

	/**
	 * Notify all the listeners subscribed to the particular event
	 * 
	 * @param eventType
	 * @param data 
	 */
	public void notify(EventType eventType, Object data) {
		events.get(eventType).forEach(listener -> listener.onNotify(eventType, data));
	}
}
