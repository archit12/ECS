package data;

import java.sql.*;
import connection.MysqlConnection;

public class MysqlExporter implements Exportable {

	public void saveData(ResultSet rs) throws Exception {
		MysqlConnection obj = new MysqlConnection("root", "");
		String query = "";
		PreparedStatement pre_stmt = null;
		try {
			Connection conn = obj.getConnection();
			query = "INSERT INTO Students VALUES (?, ?)";
			pre_stmt = conn.prepareStatement(query);
			while(rs.next()) {
				pre_stmt.setString(1, rs.getString("Name"));
				pre_stmt.setString(2, rs.getString("Address"));
				pre_stmt.execute();
			}
		}
		catch(Exception e) {
			throw new Exception("Unable to Save Data");
		}
	}
}
