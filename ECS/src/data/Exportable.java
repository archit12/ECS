package data;

import java.sql.ResultSet;

public interface Exportable {
	
	public void saveData(ResultSet rs) throws Exception;

}
