package eventsBased.model;

import eventsBased.util.EventType;

/**
 * Example of action that will be not tracked. It's used to show that only tracked actions can be notified
 * 
 * @author Nicola Colasanto
 */
public enum NoTrackedAction implements EventType{
	DEFAULT
}
