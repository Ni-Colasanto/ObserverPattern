package eventsBased.model;

import java.util.Objects;

import eventsBased.util.EventManager;
import eventsBased.util.EventType;

/**
 * Represent the student. 
 * On instantiation, the Actions event type will be automatically added on the Event Manager.
 * In some cases, it notifies events through Event Manager class. 
 * 
 * @author Nicola Colasanto
 */
public class Student {
	
	/**
	 * The action specified by this class, this action represent the type of event. 
	 * They will be used to notify all the listeners that are tracking the specific event.
	 */
	public enum Action implements EventType{
		UPADTE_COURSE,
		NO_LONGER_STUDENT
	}
	
	public enum Course{ 
		ITPS,
		MATH,
		LITERATURE
	}
	
	private String name;
	private int age;
	private int id;
	private Course course;
	private boolean isActiveStudent;
	// Is used to create a unique id for each instance of this class
	private static int counter = 0;
	
	// Single instance, used to add events and notify all listeners tracking that events
	private EventManager eventManager;
	
	public Student(String name, int age, Course course) {
		this.name = name;
		this.age = age;
		this.course = course;
		isActiveStudent = true;
		id = counter++;
		eventManager = EventManager.getInstance();
		// Add all the Actions event to the event manager, so a listener can subscribe to them
		for(var event : Action.values()) {
			eventManager.addEventType(event);
		}
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	/**
	 * Update the course and lunches the appropriate event to notify that.
	 * 
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
		eventManager.notify(Action.UPADTE_COURSE, this);
	}
	
	/**
	 * To indicate that this is no longer an active student.
	 * It launches the appropriate event to notify that.
	 * 
	 */
	public void noActiveStudent() {
		isActiveStudent = false;
		eventManager.notify(Action.NO_LONGER_STUDENT, this);
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
		Student other = (Student) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + ", Age: " + age + ", ID: " + id + ", Course: " + course;
	}
	
}
