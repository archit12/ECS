package data;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import connection.*;

public class FacultyDepartment {

	private int faculty_id;
	private int department_id;
	private static MysqlConnection connection = new MysqlConnection("root", "");
	
	public List<Faculty> getFacultiesOfDepartment(Department dept) {
		List<Faculty> faculties = new ArrayList<Faculty>();
		String query = "SELECT `f_id`, `dept_id` FROM faculty_department WHERE dept_id = ?";
		try {
		Connection conn = connection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, dept.getId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Faculty f = Faculty.getFaculty(rs.getInt("f_id"));
			faculties.add(f);
		}
		conn.close();
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
		try {
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Department getDepartmentOfFaculty(Faculty f) {
		String query = "SELECT dept_id FROM faculty_department WHERE f_id = "+f.getId();
		try {
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			Department d = Department.get(rs.getInt("dept_id"));
			conn.close();
			return d;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Department();
		}
	}
}
