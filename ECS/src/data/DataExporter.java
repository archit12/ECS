package data;

import java.sql.*;

public abstract class DataExporter {

	public abstract ResultSet getData() throws Exception;
	public abstract void setData(ResultSet rs) throws Exception;
}
