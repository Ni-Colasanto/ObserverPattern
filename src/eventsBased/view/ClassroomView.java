package eventsBased.view;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import eventsBased.model.Student;
import eventsBased.util.EventListener;
import eventsBased.util.EventManager;
import eventsBased.util.EventType;

/**
 * Print to console the class formed by the list of students.
 * When one of his students changes the specific information, print again the classroom with
 * the updated informations.
 * 
 * @author Nicola Colasanto
 */
public class ClassroomView implements EventListener{
	private Set<Student> students;
	private String name;
	private EventManager eventManager;
	// It associate the event type with the appropriate method to execute when that event occurs
	private Map<EventType, Consumer<Student>> methods;
	
	
	public ClassroomView(String name, List<Student> students) {
		this.students = new TreeSet<>(Comparator.comparing(Student::getId));
		this.students.addAll(students);
		this.name = name;
		methods = new HashMap<>();
		eventManager = EventManager.getInstance();
		
		subscribeAndAssociateMethod(Student.Action.NO_LONGER_STUDENT, student -> {
			// If the student is one of this classroom, remove it and print again the classroom
			if(removeStudent(student)) {
				System.out.println("The student [%s] is removed because is no student anymore".formatted(student));
				System.out.println("Printing new classroom...");
				printClassroom();
			}
		});
		
		subscribeAndAssociateMethod(Student.Action.UPADTE_COURSE, student -> {
			// If the student is one of this classroom, print again the classroom
			if(this.students.contains(student)) {
				System.out.println("The student [%s] has changed its value".formatted(student));
				System.out.println("Printing new classroom...");
				printClassroom();
			}
		});
	}
	
	public boolean addStudent(Student student) {
		return students.add(student);
	}
	
	public boolean removeStudent(Student student) {
		return students.remove(student);
	}
	
	public void printClassroom() {
		System.out.println("-".repeat(200));
		System.out.println("The classroom %s has the fallowing students:".formatted(name));
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("-".repeat(200));
	}
	
	@Override
	public void onNotify(EventType eventType, Object data) {
		// Data has to be a Student class
		try {
			// Cast the data to get the Student that launched the event
			Student student = (Student) data;
			// If the student is one of this classroom 
			if(students.contains(student)) {
				System.out.println("Event happened: %s".formatted(eventType));
				// Get and execute the method associate to the specified event 
				methods.get(eventType).accept(student);
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Subscribe this class as a listener to the specified event.
	 * Associate to that event a method to execute when it occurs. 
	 * 
	 * @param eventType
	 * @param method
	 */
	private void subscribeAndAssociateMethod(EventType eventType, Consumer<Student> method) {
		eventManager.subscribe(eventType, this);
		methods.put(eventType, student -> {
			method.accept(student);
		});
	}
}
