package eventBased.view;

import eventBased.model.Student;
import eventBased.util.EventListener;
import eventBased.util.EventManager;
import eventBased.util.EventType;

public class StudentView implements EventListener{
	
	private Student student;
	
	public StudentView(Student student) {
		this.student = student;
		EventManager eventManager = EventManager.getInstance();
		eventManager.subscribe(Student.Action.UPADTE_COURSE, this);
	}
	
	public void printStudent() {
		System.out.println("-".repeat(200));
		System.out.println(student);
		System.out.println("-".repeat(200));
	}

	@Override
	public void onNotify(EventType eventType, Object data) {
		try {
			Student studentChanged = (Student) data;
			if(student.equals(studentChanged)) {
				System.out.println("Event happened: %s ".formatted(eventType));			
				printStudent();
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
}
