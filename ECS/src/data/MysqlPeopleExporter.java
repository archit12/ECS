package data;

import java.util.*;

public class MysqlPeopleExporter {

	public boolean save(List<Storable> people){
		boolean done = true;
		for(Storable person : people) {
			int a_id = person.store();
			if(a_id <= 0) {
				done = false;
			}
		}
		return done;
	}
	public boolean save(List<Storable> people, Department d){
		boolean done = true;
		for(Storable person : people) {
			int a_id = person.store();
			if(a_id <= 0) {
				done = false;
			}
			else {
				FacultyDepartment fd = new FacultyDepartment();
				person.setId(a_id);
				fd.store((Faculty)person, d);
			}
		}
		return done;
	}
}
