package data;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import connection.MysqlConnection;

public class Department {
	private int dept_id;
	private String department_name;
	private static MysqlConnection connection = new MysqlConnection("root", "");
	
	public int getId() {
		return dept_id;
	}
	
	public List<Department> getAll() {
		List<Department> departments = new ArrayList<Department>();
		String query = "SELECT * FROM departments";
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
			conn.close();
			return departments;
		}
		catch(Exception e) {
			e.printStackTrace();
			return departments;
		}
	}
	
	public static Department get(int id) {
		Department dept = new Department();
		String query = "SELECT `dept_id`, `department_name` FROM departments WHERE dept_id = ?";
		try {
			Connection conn = connection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				dept.dept_id = rs.getInt("dept_id");
				dept.department_name = rs.getString("department_name");
			}
			conn.close();
			return dept;
		}
		catch(Exception e) {
			e.printStackTrace();
			return dept;
		}
	}
	
	public static int getCount() {
		String query = "SELECT COUNT(*) FROM departments";
		int count = 0;
		try {
			Connection con = connection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			count = rs.getInt(1);
			con.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
	}
	
	public String toString() {
		return this.department_name;
	}
}
