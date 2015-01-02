package data;

import java.sql.*;
import connection.MysqlConnection;

public class ExcelToMysqlDataExporter extends DataExporter {

	@Override
	public ResultSet getData() throws Exception {
		ExcelImporter importer = new ExcelImporter();
		try {
			ResultSet rs = importer.getData("Sheet1");
			return rs;
		}
		catch (Exception e) {
			throw new Exception("Unable to Read Data");
		}
	}

	@Override
	public void setData(ResultSet rs) throws Exception {
		MysqlExporter exporter = new MysqlExporter();
		try {
			exporter.saveData(rs);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to save data");
		}
	}

	public static void main(String[] args) throws Exception {
		ExcelToMysqlDataExporter o = new ExcelToMysqlDataExporter();
		ResultSet rs = o.getData();
		
		MysqlConnection obj = new MysqlConnection("root", "");
		String query = "";
		PreparedStatement pre_stmt = null;
		try {
			Connection conn = obj.getConnection();
			query = "INSERT INTO Students (`Name`, `Address`) VALUES (?, ?)";
			pre_stmt = conn.prepareStatement(query);
			while(rs.next()) {
				pre_stmt.setString(1, rs.getString("Name"));
				pre_stmt.setString(2, rs.getString("Address"));
				pre_stmt.execute();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Save Data");
		}
		//o.setData(rs);
	}
}
