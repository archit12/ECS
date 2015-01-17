package dutyAllocator;
/* Allocate maximum faculty availability from each department according to requirement.
 * 
 * If requirement is met in each department, allocate duties to faculties randomly with least duty_count
 * 
 * If requirement is not met in each department, allocate maximum faculties while compensating for the remaining faculties
 * by adding all unallocated faculties from all other departments and then randomly selecting the remaining faculties 
 * from the room, preferring those with least duty_count
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.sql.*;

import connection.MysqlConnection;
import data.Department;
import data.Faculty;
import data.FacultyDepartment;
import data.ExcelWriter;

public class ExamDutyAllocator {
	
	private final int RESERVE_FACULTY_PERCENTAGE = 30;
	private Date date;
	private int faculties_per_room = 2;
	private int no_of_rooms;
	
	public int getNoOfRooms() {
		return no_of_rooms;
	}
	public void setNoOfRooms(int no_of_rooms) {
		this.no_of_rooms = no_of_rooms;
	}

	/* 
	 * if some department is unable to furnish required number of faculties,
	 * then this field contains the number of such faculties that have to be compensated from other departments.
	 */
	private int random_selection_room_count = 0;
	/*
	 * List of Faculties which will compensate for the lack of available faculties in some departments
	 */
	private List<Faculty> random_selection_room = new ArrayList<Faculty>();
	
	private static MysqlConnection connection = new MysqlConnection("root", "");
	
	public ExamDutyAllocator(String date_string, int no_of_rooms) {
		this.setDate(null);
		this.setNoOfRooms(no_of_rooms);
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
	/**
	 * Calculates the total faculties needed including the reserve faculties as defined in RESERVE_FACULTY_PERCENTAGE
	 * @return The total number of faculties needed
	 */
	public int calculateTotalFacultyCount() {
		int faculties_needed,
		reserve_faculties;
		
		faculties_needed = this.no_of_rooms * this.faculties_per_room;
		reserve_faculties = (faculties_needed * this.RESERVE_FACULTY_PERCENTAGE) / 100;
		faculties_needed += Math.ceil(reserve_faculties);
		return faculties_needed;
	}

	/**
	 * Calculates the number of faculties that need to be alloted from each department.
	 * If the number of faculties in the department is less than the requirement, then the maximum number of faculties that can be 
	 * assigned from the department are used; 
	 * The remaining number of needed faculties are added to random_selection_room_count field.
	 * @return Array of Number of faculties required from each department
	 */
	public int[] getFacultyRequirementFromDepartments() {
		// calculate faculty requirements
		int total_faculties, departments_count;
		int faculties_per_department;
		departments_count = Department.getCount();
		total_faculties = calculateTotalFacultyCount();
		System.out.println("dept"+departments_count+"fac"+total_faculties);
		// calculate faculty requirement from each department
		faculties_per_department = (int) Math.floor(total_faculties / departments_count);
		/* if faculty requirement from each department is not a whole number, then convert it  into lowest whole number
		 * and add the remaining faculties into random_selection_room_count
		 */
		int extra_faculties_needed = total_faculties - (faculties_per_department * departments_count);
		this.random_selection_room_count += extra_faculties_needed;
		
		// Calculate the actual count of faculties that can be taken from each department
		int[] facultyCount_from_department = new int[departments_count];
		int department_index = 0;
		int faculties_in_department;
		List<Faculty> faculties;
		FacultyDepartment fd = new FacultyDepartment();
		Department dept = new Department();
		List<Department> departments = dept.getAll();
		for(Department department : departments) {
			faculties = fd.getFacultiesOfDepartment(department);
			faculties_in_department = faculties.size();
			// if faculties in department are greater than requirement then simply assign the requirement;
			// else assign all faculties in the department and add remaining to random_selection_room_count
			if (faculties_in_department >= faculties_per_department) {
				facultyCount_from_department[department_index ++] = faculties_per_department;
			} else {
				facultyCount_from_department[department_index ++] = faculties_in_department;
				this.random_selection_room_count += (faculties_per_department - faculties_in_department);
			}
 		}
		return facultyCount_from_department;
	}
	
	public List<Faculty> allocateDuty() {
		List<Faculty> faculties;
		Department dept = new Department();
		// List of all departments
		List<Department> departments = dept.getAll();
		FacultyDepartment fd = new FacultyDepartment();
		// List of faculties which will have duties
		List<Faculty> selected_faculties = new ArrayList<Faculty>();
		
		// Array containing the number of faculties to be selected from each department
		int[] faculties_from_each_dept = getFacultyRequirementFromDepartments();
		int department_index = 0;
		/*
		 * Iterate through each department and add the required number of faculties from the faculties_from_each_dept array 
		 * to selected_faculties list.
		 */
		for(Department department : departments) {
			faculties = fd.getFacultiesOfDepartment(department);
			// sort faculties by their duty_count so that faculties with minimum no. of duties are selected first
			sortFacultiesByDutyCount(faculties);
			if (faculties_from_each_dept[department_index] > 0) {
				selected_faculties.addAll(faculties.subList(0, faculties_from_each_dept[department_index]));
				faculties.removeAll(selected_faculties);
				/* if more faculties are needed because of lack of required faculties in some departments
				 * then, add all remaining faculties in each department to the random_selection_room
				 */
				if(this.random_selection_room_count > 0) {
					try {
						this.random_selection_room.addAll(faculties);
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}
			}
			department_index ++;
 		}
		if (random_selection_room_count > 0) {
			sortFacultiesByDutyCount(random_selection_room);
			try {
				int faculties_with_lowest_duty_count_in_room = getNumberOfFacultiesWithLowestDutyCount(random_selection_room);
				while (faculties_with_lowest_duty_count_in_room <= random_selection_room_count) {
					selected_faculties.addAll(random_selection_room.subList(0, faculties_with_lowest_duty_count_in_room));
					random_selection_room.removeAll(random_selection_room.subList(0, faculties_with_lowest_duty_count_in_room));
					random_selection_room_count -= faculties_with_lowest_duty_count_in_room;
					faculties_with_lowest_duty_count_in_room = getNumberOfFacultiesWithLowestDutyCount(random_selection_room);
//					faculties_with_lowest_duty_count_in_room += getNumberOfFacultiesWithLowestDutyCount(
//							random_selection_room.subList(
//									faculties_with_lowest_duty_count_in_room, random_selection_room.size()));
				}
				while (random_selection_room_count > 0) {
					int index = (int)Math.floor(Math.random() * faculties_with_lowest_duty_count_in_room);
					selected_faculties.add(random_selection_room.get(index));
					random_selection_room.remove(index);
					random_selection_room_count -= 1;
					faculties_with_lowest_duty_count_in_room -= 1;
				}
//				selected_faculties.addAll(random_selection_room.subList(0, random_selection_room_count));
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		for (Faculty faculty : selected_faculties) {
			faculty.incrementDutyCount();
		}
		return selected_faculties;
	}
	
	public int getNumberOfFacultiesWithLowestDutyCount(List<Faculty> faculties) throws IndexOutOfBoundsException {
		// Assume list is sorted
		int lowest_duty_count = faculties.get(0).getDutyCount();
		int count = 0;
		for (Faculty faculty : faculties) {
			if (faculty.getDutyCount() > lowest_duty_count)
				break;
			else 
				count ++;
		}
		return count;
	}
	public void sortFacultiesByDutyCount(List<Faculty> faculties) {
		Collections.sort(faculties, new Comparator<Faculty>() {
	        @Override
	        public int compare(Faculty  f1, Faculty  f2)
	        {
	            if(f1.getDutyCount() < f2.getDutyCount())
	            	return -1;
	            else if (f1.getDutyCount() < f2.getDutyCount())
	            	return 1;
	            else 
	            	return 0;
	        }
	    });
	}
	public boolean allocate() {
		List<Faculty> shift1 = this.allocateDuty();
		List<Faculty> shift2 = this.allocateDuty();
		return new ExcelWriter().export(shift1, shift2, no_of_rooms);
	}
}
