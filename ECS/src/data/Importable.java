package data;

import java.sql.ResultSet;

public interface Importable {

	public ResultSet getData(String sheet_name) throws Exception;
	
}
