package eventBased.main;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import eventBased.model.Student;
import eventBased.model.Student.Course;
import eventBased.view.ClassroomView;
import eventBased.view.StudentView;

public class Main {

	public static void main(String[] args) {
		Student stu1 = new Student("Alex", 22, Student.Course.MATH);
		Student stu2 = new Student("Bob", 15, Student.Course.LECTURE);
		Student stu3 = new Student("Truce", 12, Student.Course.MATH);
		Student stu4 = new Student("Susy", 18, Student.Course.ITPS);
		Student stu5 = new Student("Baldrog", 18, Student.Course.MATH);
		
		StudentView stuView1 = new StudentView(stu1);
		StudentView stuView2 = new StudentView(stu2);
		StudentView stuView3 = new StudentView(stu3);
		StudentView stuView4 = new StudentView(stu4);
		StudentView stuView5 = new StudentView(stu5);
		
		var studentList = new ArrayList<Student>(List.of(stu5, stu3, stu2, stu4,stu1));
		var studentViewList = new ArrayList<StudentView>(List.of(stuView1, stuView2, stuView3, stuView4,stuView5));
		
		for(StudentView stuV : studentViewList) {
			stuV.printStudent();
		}
		
		ClassroomView classroomView = new ClassroomView("science", studentList);
		classroomView.removeStudent(stu3);
		classroomView.printClassroom();
		
		System.out.println("NOW I WILL CHANGE FOLLOWING STUDENT ATTRIBUTES");
		stu3.setCourse(Course.ITPS);
		stu4.setCourse(Course.LECTURE);
		
		System.out.println("NOW STUDENTS WILL NOT STUDENT ANYMORE");
		stu1.noActiveStudent();
		stu5.noActiveStudent();
		stu3.noActiveStudent();
	}

}
