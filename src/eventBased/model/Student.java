package eventBased.model;

import java.util.Objects;

import eventBased.util.EventManager;
import eventBased.util.EventType;

public class Student {
	
	public enum Course{ 
		ITPS,
		MATH,
		LITERATURE
	}
	
	public enum Action implements EventType{
		UPADTE_COURSE,
		NO_LONGER_STUDENT
	}
	
	private String name;
	private int age;
	private int id;
	
	private Course course;
	
	private boolean isActiveStudent;
	private static int counter = 0;
	
	private EventManager eventManager;
	
	public Student(String name, int age, Course course) {
		this.name = name;
		this.age = age;
		this.course = course;
		isActiveStudent = true;
		id = counter++;
		eventManager = EventManager.getInstance();
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

	public void setCourse(Course course) {
		this.course = course;
		eventManager.notify(Action.UPADTE_COURSE, this);
	}
	
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
