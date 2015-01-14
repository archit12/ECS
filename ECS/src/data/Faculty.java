package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.MysqlConnection;

public class Faculty implements Storable {
	
	private int id;
	private String name;
	private String designation;
	private long contact;
	private String email;
	private int duty_count = 0;
	private Department department;
//	public String[] designations = {"Professor", "Associate Professor", "Assistant Professor"};
	private static MysqlConnection connection = new MysqlConnection("root", "");

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	
	public int getDutyCount() {
		return this.duty_count;
	}
	public void setDutyCount(int duty_count) {
		this.duty_count = duty_count;
	}

	@Override
	public int store() {
		String query = "";
		PreparedStatement pre_stmt = null;
		try {
			Connection conn = connection.getConnection();
			query = "INSERT INTO faculties (`faculty name`, `designation`, `contact`, `email`) VALUES (?, ?, ?, ?)";
			pre_stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pre_stmt.setString(1, this.getName());
			pre_stmt.setString(2, this.getDesignation());
			pre_stmt.setLong(3, this.getContact());
			pre_stmt.setString(4, this.getEmail());
			pre_stmt.execute();
			ResultSet rs = pre_stmt.getGeneratedKeys();
			rs.next();
			this.setId(rs.getInt(1));
			if(pre_stmt.getUpdateCount() > 0) {
				conn.close();
				return this.getId();
			}
			else {
				conn.close();
				return 0;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Faculty getFaculty(int id) {
		String query = "SELECT `id`, `faculty name`, `designation`, `contact`, `email`, `duty_count`, `departments`.`dept_id`, `department_name`, `duty_count` "
				+ "FROM `faculties` "
				+ "inner join `faculty_department` "
				+ "on `faculties`.`id` = `faculty_department`.`f_id`"
				+ " inner join `departments` "
				+ "on `departments`.`dept_id` = `faculty_department`.`dept_id` "
				+ "WHERE id=?";
		PreparedStatement pre_stmt = null;
		try {
			Connection conn = connection.getConnection();
			pre_stmt = conn.prepareStatement(query);
			pre_stmt.setInt(1, id);
			ResultSet rs = pre_stmt.executeQuery();
			Faculty f = new Faculty();
			rs.next();
			f.setId(rs.getInt("id"));
			f.setName(rs.getString("faculty name"));
			f.setDesignation(rs.getString("designation"));
			f.setContact(rs.getLong("contact"));
			f.setEmail(rs.getString("email"));
			f.setDutyCount(rs.getInt("duty_count"));
			Department d = Department.get(rs.getInt("dept_id"));
			f.setDepartment(d);
			conn.close();
			return f;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Faculty();
		}
	}
	
	public void incrementDutyCount() {
		String query = "UPDATE `faculties` SET `duty_count` = "+(this.getDutyCount()+1)+" WHERE `id` = "+this.getId();
		try {
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}
