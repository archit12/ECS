package dutyAllocator;

import java.util.*;
import data.ExcelWriter;
import data.Faculty;

public class FriskingDutyAllocator {

	private int count;
	
	public FriskingDutyAllocator() {
		this(4);
	}
	public FriskingDutyAllocator(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public List<Faculty> allocateDuty() {
		List<Faculty> faculties;
		List<Faculty> selected_faculties = new ArrayList<Faculty>();
		faculties = Faculty.getFaculties();
		int size;
		int index;
		while (selected_faculties.size() < count) {
			size = faculties.size();
			index = (int) Math.floor(Math.random() * size);
			selected_faculties.add(faculties.get(index));
			faculties.remove(index);
		}
		return selected_faculties;
	}
	
	public boolean allocate() {
		List<Faculty> faculties = this.allocateDuty();
		return new ExcelWriter().export(faculties);
	}
}
