package classic.withInterface.main;

import classic.withInterface.model.Fan;
import classic.withInterface.model.Star;

public class Client {

	public static void main(String[] args) {
		
		// Create the subject to be tracked
		Star star = new Star("Madonna");
		// Create the observers
		Fan fan1 = new Fan("George");
		Fan fan2 = new Fan("Alex");
		Fan fan3 = new Fan("Susy");
		Fan fan4 = new Fan("Bob");
		Fan fan5 = new Fan("Alex");
		
		// Register the observers
		star.addObserver(fan1);
		star.addObserver(fan2);
		star.addObserver(fan3);
		star.addObserver(fan3);
		star.addObserver(fan5);
		
		
		star.deleteObserver(fan3);
		star.addObserver(fan4);
		star.deleteObserver(fan4);
		
		System.out.println(star.getObservers());
		
		star.postMessage("Good Morning fans!!");
		star.deleteObserver(fan2);
		star.postMessage("Damn!!");
	}

}
