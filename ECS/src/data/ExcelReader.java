package data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelReader {
	private String path = "";
	public ExcelReader(String path) {
		this.path = path;
	}
	public List<String[]> read() {
		List<String[]> list_of_faculties = new ArrayList<String[]>();
		int counter = 0;
		boolean header_line = true;
		try {
			File excel =  new File (path);
			XSSFWorkbook wb = new XSSFWorkbook(excel);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				String[] faculty_details = new String[4];
				XSSFRow row = (XSSFRow) rows.next();
				if(header_line) {
					header_line = false;
					row = (XSSFRow) rows.next();
				}
				counter = 0;
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					faculty_details[counter++] = cell.getStringCellValue();
				}
				list_of_faculties.add(faculty_details);
			}
			wb.close();
			return list_of_faculties;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return list_of_faculties;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return list_of_faculties;
		} catch (IOException e) {
			e.printStackTrace();
			return list_of_faculties;
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			return list_of_faculties;
		}
	}
	public List<Storable> getPeople() {
		List<String[]> list_of_people = this.read();
		List<Storable> people = new ArrayList<Storable>();
		Faculty f;
		for(String[] faculty : list_of_people) {
			f = new Faculty();
			f.setName(faculty[1]);
			f.setDesignation(faculty[2]);
			f.setEmail("");
			try {
			f.setContact(Long.parseLong(faculty[3]));
			}
			catch(NumberFormatException e) {
				f.setContact(0);
			}
			finally {
			people.add(f);
			}
		}
		return people;
	}
//	public static void main(String[] args) {
//		ExcelReader reader = new ExcelReader("F:\\CS.xlsx");
//		reader.getFaculties();
//		for(Faculty f : reader.getFaculties()){
//			System.out.println(f.getName()+" "+f.getDesignation()+" "+f.getContact());
//		}
//	}
}
