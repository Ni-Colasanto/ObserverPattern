package model;

import java.util.Objects;

import util.Observer;
import util.Subject;

public class Fan implements Observer{
	private String name;
	
	public Fan(String name) {
		this.name = name;
	}
	
	@Override
	public void update(Subject subject) {
		String msg = (String) subject.getUpdate(this);
		if(Objects.isNull(msg)) 
			System.out.println("[" + name + "] No update -> ");
		else 
			System.out.println("[" + name + "] New update! -> " + msg);
	}

	@Override
	public String toString() {
		return "Fan [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fan other = (Fan) obj;
		return Objects.equals(name, other.name);
	}

}
