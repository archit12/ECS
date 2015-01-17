package dutyAllocator;

import java.util.*;
import data.ExcelWriter;
import data.Faculty;

public class FlyingSquadDutyAllocator {

	private int floors = 3;
	private final int FACULTIES_PER_FLOOR = 4;
	
	public FlyingSquadDutyAllocator() {
		this(3);
	}
	public FlyingSquadDutyAllocator(int floors) {
		this.floors = floors;
	}
	
	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}
	
	public List<Faculty> allocateDuty() {
		List<Faculty> faculties;
		List<Faculty> selected_faculties = new ArrayList<Faculty>();
		int faculties_needed = FACULTIES_PER_FLOOR * floors;
		faculties = Faculty.getFaculties();
		int size;
		int index;
		while (selected_faculties.size() < faculties_needed) {
			size = faculties.size();
			index = (int) Math.floor(Math.random() * size);
			selected_faculties.add(faculties.get(index));
			faculties.remove(index);
		}
		return selected_faculties;
	}
	
	public boolean allocate() {
		List<Faculty> faculties = this.allocateDuty();
		return new ExcelWriter().export(faculties, floors);
	}
}
