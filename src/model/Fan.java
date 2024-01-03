package model;

import java.util.Map;
import java.util.Objects;

import util.Observer;

public class Fan implements Observer{
	private String name;
	
	public Fan(String name) {
		this.name = name;
	}
	
	@Override
	public void update(Map<?,?> data) {
		if(Objects.isNull(data)) 
			System.out.println("[%s] No update");
		else 
			System.out.println("[%s] New update from [%s]! -> %s".formatted(name, data.get("name"), data.get("message")));
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

	@Override
	public String getName() {
		return name;
	}

}
