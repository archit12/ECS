package data;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import connection.MysqlConnection;

public class Department {
	private int dept_id;
	private String department_name;
	
	public int getId() {
		return dept_id;
	}
	
	public List<Department> getAll() {
		List<Department> departments = new ArrayList<Department>();
		String query = "SELECT * FROM departments";
		MysqlConnection connection = new MysqlConnection("root", "");
		try {
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Department dept = new Department();
				dept.dept_id = rs.getInt("dept_id");
				dept.department_name = rs.getString("department_name");
				departments.add(dept);
			}
			return departments;
		}
		catch(Exception e) {
			e.printStackTrace();
			return departments;
		}
	}
	
	public static Department get(int id) {
		Department dept = new Department();
		String query = "SELECT * FROM departments where dept_id = ?";
		MysqlConnection connection = new MysqlConnection("root", "");
		try {
			Connection conn = connection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				dept.dept_id = rs.getInt("dept_id");
				dept.department_name = rs.getString("department_name");
			}
			return dept;
		}
		catch(Exception e) {
			e.printStackTrace();
			return dept;
		}
	}
	
	public String toString() {
		return this.department_name;
	}
}
