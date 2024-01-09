package eventBased.util;

public interface EventListener {
	void onNotify(EventType eventType, Object data);
}
