package data;

import java.sql.*;
import connection.ExcelConnection;

public class ExcelImporter implements Importable {
	public ResultSet getData(String sheet_name) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		ExcelConnection ob = new ExcelConnection();
		try {
			Connection conn = ob.getConnection();
			String query = "SELECT * FROM ["+sheet_name+"$]";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception e) {
			throw new Exception("Unable to Read Data");
		}
	}
}
