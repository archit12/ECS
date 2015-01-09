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
//	public String[] designations = {"Professor", "Associate Professor", "Assistant Professor"};
	
	public Faculty() {
	}

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
			MysqlConnection connection = new MysqlConnection("root", "");
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
				return this.getId();
			}
			else {
				return 0;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Faculty getFaculty(int id) {
		String query = "SELECT `id`, `faculty name`, `designation`, `contact`, `email` FROM faculties WHERE id = ?";
		PreparedStatement pre_stmt = null;
		try {
			MysqlConnection connection = new MysqlConnection("root", "");
			Connection conn = connection.getConnection();
			pre_stmt = conn.prepareStatement(query);
			pre_stmt.setInt(1, id);
			ResultSet rs = pre_stmt.executeQuery();
			Faculty f = new Faculty();
			rs.next();
			f.setId(rs.getInt("id"));
			f.setName(rs.getString("Name"));
			f.setDesignation(rs.getString("designation"));
			f.setContact(rs.getInt("contact"));
			f.setEmail(rs.getString("email"));
			return f;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Faculty();
		}
	}
	
	public void incrementDutyCount(Faculty f) {
		String query = "UPDATE `faculties` SET `duty_count` = "+(f.getDutyCount()+1)+" WHERE `f_id` = "+f.getId();
		try {
			MysqlConnection connection = new MysqlConnection("root", "");
			Connection conn = connection.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
