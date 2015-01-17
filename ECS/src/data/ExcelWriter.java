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
	public boolean export(List<Faculty> d1, List<Faculty> d2, int no_of_rooms) {
		int room = 1;
		boolean increment_room = false;
		try {
			int row_index = 1;
			int cell_index = 0;
			FileOutputStream excel = new FileOutputStream("F:\\ExamDuty.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Shift Duty");
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("S.No");
			XSSFCell cellA2 = row1.createCell(1);
			cellA2.setCellValue("Faculty Name");
			XSSFCell cellA3 = row1.createCell(2);
			cellA3.setCellValue("Gender");
			XSSFCell cellA4 = row1.createCell(3);
			cellA4.setCellValue("Department");
			XSSFCell cellA5 = row1.createCell(4);
			cellA5.setCellValue("D1");
			XSSFCell cellA6 = row1.createCell(5);
			cellA6.setCellValue("D2");
			XSSFCell cellA7 = row1.createCell(6);
			cellA7.setCellValue("Room No.");
			for(Faculty f : d1) {
				cell_index = 0;
				XSSFRow row_next = sheet.createRow(row_index++);
				row_next.createCell(cell_index).setCellValue(row_index - 1);
				row_next.createCell(++cell_index).setCellValue(f.getName());
				row_next.createCell(++cell_index).setCellValue(String.valueOf(f.getGender()));
				row_next.createCell(++cell_index).setCellValue(f.getDepartment().toString());
				row_next.createCell(++cell_index).setCellValue("Y");
				row_next.createCell(++cell_index).setCellValue("");
				if (no_of_rooms - room >= 0) {
					row_next.createCell(++cell_index).setCellValue(room);
					if (increment_room) {
						room ++;
						increment_room = false;
					} else {
						increment_room = true;
					}
				}
			}
			room = 1;
			for(Faculty f : d2) {
				cell_index = 0;
				XSSFRow row_next = sheet.createRow(row_index++);
				row_next.createCell(cell_index).setCellValue(row_index - 1);
				row_next.createCell(++cell_index).setCellValue(f.getName());
				row_next.createCell(++cell_index).setCellValue(String.valueOf(f.getGender()));
				row_next.createCell(++cell_index).setCellValue(f.getDepartment().toString());
				row_next.createCell(++cell_index).setCellValue("");
				row_next.createCell(++cell_index).setCellValue("Y");
				if (no_of_rooms - room >= 0) {
					row_next.createCell(++cell_index).setCellValue(room);
					if (increment_room) {
						room ++;
						increment_room = false;
					} else {
						increment_room = true;
					}
				}
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
	
	public boolean export(List<Faculty> faculties) {
		try {
			int row_index = 1;
			int cell_index = 0;
			FileOutputStream excel = new FileOutputStream("F:\\FriskingDuty.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Shift Duty");
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("S.No");
			XSSFCell cellA2 = row1.createCell(1);
			cellA2.setCellValue("Faculty Name");
			XSSFCell cellA3 = row1.createCell(2);
			cellA3.setCellValue("Gender");
			XSSFCell cellA4 = row1.createCell(3);
			cellA4.setCellValue("Department");
			for(Faculty f : faculties) {
				cell_index = 0;
				XSSFRow row_next = sheet.createRow(row_index++);
				row_next.createCell(cell_index).setCellValue(row_index - 1);
				row_next.createCell(++cell_index).setCellValue(f.getName());
				row_next.createCell(++cell_index).setCellValue(String.valueOf(f.getGender()));
				row_next.createCell(++cell_index).setCellValue(f.getDepartment().toString());
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
	
	public boolean export(List<Faculty> faculties, int no_of_floors) {
		try {
			int row_index = 1;
			int cell_index = 0;
			int faculties_per_floor = (int) faculties.size() / no_of_floors;
			int faculty_index = 1, count = 1;
			FileOutputStream excel = new FileOutputStream("F:\\FlyingSquadDuty.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Shift Duty");
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("S.No");
			XSSFCell cellA2 = row1.createCell(1);
			cellA2.setCellValue("Faculty Name");
			XSSFCell cellA3 = row1.createCell(2);
			cellA3.setCellValue("Gender");
			XSSFCell cellA4 = row1.createCell(3);
			cellA4.setCellValue("Department");
			XSSFCell cellA5 = row1.createCell(4);
			cellA5.setCellValue("Floor");
			for(Faculty f : faculties) {
				cell_index = 0;
				XSSFRow row_next = sheet.createRow(row_index++);
				row_next.createCell(cell_index).setCellValue(row_index - 1);
				row_next.createCell(++cell_index).setCellValue(f.getName());
				row_next.createCell(++cell_index).setCellValue(String.valueOf(f.getGender()));
				row_next.createCell(++cell_index).setCellValue(f.getDepartment().toString());
				if (count > faculties_per_floor) {
					faculty_index ++;
					count = 1;
				}
				row_next.createCell(++cell_index).setCellValue(faculty_index);
				count ++;
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
