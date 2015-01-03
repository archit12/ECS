package data;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
public class ExcelReader {
	public static void main(String[] args) {
		try {
			File excel =  new File ("F:\\Installed\\xampp\\htdocs\\examination control system\\Students.xlsx");
	        FileInputStream fs = new FileInputStream(excel);
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator(); 
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				System.out.println("\n");
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {

					XSSFCell cell = (XSSFCell) cells.next();
					if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType())
						System.out.print(cell.getNumericCellValue() + "     ");
					else if (XSSFCell.CELL_TYPE_STRING == cell.getCellType())
						System.out.print(cell.getStringCellValue() + "     ");
					else if (XSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType())
						System.out.print(cell.getBooleanCellValue() + "     ");
					else if (XSSFCell.CELL_TYPE_BLANK == cell.getCellType())
						System.out.print("BLANK     ");
					else
						System.out.print("Unknown cell type");

				}
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
