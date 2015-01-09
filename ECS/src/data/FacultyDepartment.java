package data;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import connection.*;

public class FacultyDepartment {

	private int faculty_id;
	private int department_id;
	
	public List<Faculty> getFacultiesOfDepartment(Department dept) {
		List<Faculty> faculties = new ArrayList<Faculty>();
		String query = "SELECT `f_id`, `dept_id` FROM faculty_department";
		MysqlConnection connection = new MysqlConnection("root", "");
		try {
		Connection conn = connection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			Faculty f = Faculty.getFaculty(rs.getInt("f_id"));
			faculties.add(f);
		}
		return faculties;
		} catch (Exception e) {
			e.printStackTrace();
			return faculties;
		}
	}
	
	public void store(Faculty f, Department d) {
		String query = "INSERT INTO faculty_department (`f_id`, `dept_id`) VALUES ("+ f.getId()+","+ d.getId() +")";
//		System.out.println(f.getId()+","+ d.getId());
//		System.exit(0);
		MysqlConnection connection = new MysqlConnection("root", "");
		try {
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Department getDepartmentOfFaculty(Faculty f) {
		String query = "SELECT dept_id FROM faculty_department WHERE f_id = "+f.getId();
		MysqlConnection connection = new MysqlConnection("root", "");
		try {
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			Department d = Department.get(rs.getInt("dept_id"));
			return d;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Department();
		}
	}
}
