package eventBased.view;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import eventBased.model.Student;
import eventBased.util.EventListener;
import eventBased.util.EventManager;
import eventBased.util.EventType;

public class ClassroomView implements EventListener{
	private Set<Student> students;
	private String name;
	private EventManager eventManager;
	
	private Map<EventType, Consumer<Student>> methods;
	
	
	public ClassroomView(String name, List<Student> students) {
		this.students = new TreeSet<>(Comparator.comparing(Student::getId));
		this.students.addAll(students);
		this.name = name;
		methods = new HashMap<>();
		eventManager = EventManager.getInstance();
		
		subscribeAndAssociateMethod(Student.Action.NO_LONGER_STUDENT, student -> {
			if(removeStudent(student)) {
				System.out.println("The student [%s] is removed because is no student anymore".formatted(student));
				System.out.println("Printing new classroom...");
				printClassroom();
			}
		});
		
		subscribeAndAssociateMethod(Student.Action.UPADTE_COURSE, student -> {
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
		try {
			Student student = (Student) data;
			if(students.contains(student)) {
				System.out.println("Event happened: %s".formatted(eventType));
				methods.get(eventType).accept(student);
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	private void subscribeAndAssociateMethod(EventType eventType, Consumer<Student> method) {
		eventManager.subscribe(eventType, this);
		methods.put(eventType, student -> {
			method.accept(student);
		});
	}
}
