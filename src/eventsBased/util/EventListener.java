package eventsBased.util;


/**
 * All the listeners have to implement this interface and subscribe to the event that want to be notified
 * 
 * @author Nicola Colasanto
 */
public interface EventListener {
	/**
	 * Invoked when that event occurs  
	 *
	 * @param eventType
	 * @param data
	 */
	void onNotify(EventType eventType, Object data);
}
