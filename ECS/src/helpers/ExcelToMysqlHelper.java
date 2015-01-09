package helpers;

import java.util.List;

import data.Department;
import data.ExcelReader;
import data.MysqlPeopleExporter;
import data.Storable;
import data.Faculty;

public class ExcelToMysqlHelper {
	public boolean export(String path, Department d) {
		ExcelReader reader = new ExcelReader(path);
		MysqlPeopleExporter exporter = new MysqlPeopleExporter();
		List<Storable> people = reader.getPeople();
 		if(exporter.save(people, d)){
			return true;
		}
		else {
			return false;
		}
	}
}
