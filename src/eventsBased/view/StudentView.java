package eventsBased.view;

import eventsBased.model.NoTrackedAction;
import eventsBased.model.Student;
import eventsBased.util.EventListener;
import eventsBased.util.EventManager;
import eventsBased.util.EventType;

/**
 * Print to console the information of the student passed in the constructor.
 * It keeps track of the information and print each time a modification occurs.
 * 
 *@author Nicola Colasanto 
 * 
 */
public class StudentView implements EventListener{
	
	private Student student;
	
	public StudentView(Student student) {
		this.student = student;
		// To be notified when the events indicated happens
		EventManager eventManager = EventManager.getInstance();
		eventManager.subscribe(Student.Action.UPADTE_COURSE, this);
		// Used to show that its possible to subscribe only to the actions added on the Event Manager 
		eventManager.subscribe(NoTrackedAction.DEFAULT, this);
	}
	
	public void printStudent() {
		System.out.println("-".repeat(200));
		System.out.println(student);
		System.out.println("-".repeat(200));
	}

	@Override
	public void onNotify(EventType eventType, Object data) {
		// Data has to be a Student class
		try {
			// Cast the data to get the Student that launched the event
			Student studentChanged = (Student) data;
			// If the student that notified the event it's the same of this view, print him again
			if(student.equals(studentChanged)) {
				// In this case i know that the eventType is UPADTE_COURSE, because it's the only one that
				// this class is subscribed to
				System.out.println("Event happened: %s ".formatted(eventType));			
				printStudent();
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
}
