package connection;

import java.sql.*;

public final class ExcelConnection implements Connectable{
	public Connection getConnection() throws Exception {
		String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		String url = "jdbc:odbc:Students";
		Class.forName(driver);
		return DriverManager.getConnection(url);
	}

//	public static void main(String args[]) throws Exception {
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		conn = getConnection();
//		stmt = conn.createStatement();
//		String excelQuery = "select * from [Sheet1$]";
//		rs = stmt.executeQuery(excelQuery);
//
////		 while (rs.next()) {
////		 System.out.println(rs.getString("Name") + " " +
////		 rs.getString("Address"));
////		 }
//
//		rs.close();
//		stmt.close();
//		conn.close();
//	}
}