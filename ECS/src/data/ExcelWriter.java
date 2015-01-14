package data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelWriter {
	public boolean export(List<Faculty> d1, List<Faculty> d2) {
		try {
			int row_index = 1;
			int cell_index = 0;
			FileOutputStream excel = new FileOutputStream("F:\\try.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Shift Duty");
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("S.No");
			XSSFCell cellA2 = row1.createCell(1);
			cellA2.setCellValue("Faculty Name");
			XSSFCell cellA3 = row1.createCell(2);
			cellA3.setCellValue("Department");
			XSSFCell cellA4 = row1.createCell(3);
			cellA4.setCellValue("D1");
			XSSFCell cellA5 = row1.createCell(4);
			cellA5.setCellValue("D2");
			for(Faculty f : d1) {
				cell_index = 0;
				XSSFRow row_next = sheet.createRow(row_index++);
				row_next.createCell(cell_index).setCellValue(row_index - 1);
				row_next.createCell(++cell_index).setCellValue(f.getName());
				row_next.createCell(++cell_index).setCellValue(f.getDepartment().toString());
				row_next.createCell(++cell_index).setCellValue("Y");
			}
			for(Faculty f : d2) {
				cell_index = 0;
				XSSFRow row_next = sheet.createRow(row_index++);
				row_next.createCell(cell_index).setCellValue(row_index - 1);
				row_next.createCell(++cell_index).setCellValue(f.getName());
				row_next.createCell(++cell_index).setCellValue(f.getDepartment().toString());
				row_next.createCell(++cell_index).setCellValue("");
				row_next.createCell(++cell_index).setCellValue("Y");
			}
			wb.write(excel);
			excel.flush();
			excel.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
//	public static void main(String args[]) {
//		try {
//			FileOutputStream excel = new FileOutputStream("F:\\try.xlsx");
//			XSSFWorkbook wb = new XSSFWorkbook();
//			XSSFSheet sheet = wb.createSheet("Shift Duty");
//			XSSFRow row1 = sheet.createRow(0);
//			XSSFCell cellA1 = row1.createCell(0);
//			cellA1.setCellValue("S.No");
//			XSSFCell cellA2 = row1.createCell(1);
//			cellA2.setCellValue("Faculty Name");
//			XSSFCell cellA3 = row1.createCell(2);
//			cellA3.setCellValue("Department");
//			XSSFCell cellA4 = row1.createCell(3);
//			cellA4.setCellValue("D1");
//			XSSFCell cellA5 = row1.createCell(4);
//			cellA5.setCellValue("D2");
//			wb.write(excel);
//			excel.flush();
//			excel.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
