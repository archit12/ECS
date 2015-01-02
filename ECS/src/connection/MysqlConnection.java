package connection;

import java.sql.*;

public class MysqlConnection implements Connectable {
	protected String username = "root";
	protected String password = "";

	public MysqlConnection(String username, String password) {
		username = this.username;
		password = this.password;
	}
	
	public Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ecs",
					"root", "");
			return conn;
		}
		catch (Exception e) {
			throw new Exception("Not connnected");
		}
	}
	
//	public static void main(String args[]) throws Exception {
//		MysqlConnection obj = new MysqlConnection("root", "");
//		Connection conn = obj.getConnection();
//		String query = "";
//		PreparedStatement statement = null;
//		query = "INSERT INTO students (`Name`, `Address`) VALUES (?, ?)";
//		statement = conn.prepareStatement(query);
//		statement.setString(1, "Archit");
//		statement.setString(2, "home is home");
//		statement.execute();
//		statement.close();
//		conn.close();
//	}
}
