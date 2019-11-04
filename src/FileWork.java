
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileWork {
	
	// хэш-функция 
	private static int hashCode(String hashValue) {
		int hashFileSize = 100;
		int hash = 0;
		
		for (int i = 0; i < hashValue.length(); i++) {
			hash += (int)hashValue.charAt(i);
		}
		hash = hash % hashFileSize;
		return hash;
	}
	
	// поиск значения по полю
	protected static int search(String dataBaseName, String searchField, String searchValue) throws FileNotFoundException, IOException{
		String fullDataDaseName = String.format("%s.xlsx",dataBaseName);
		Workbook book = new XSSFWorkbook(new FileInputStream(fullDataDaseName));
		Sheet sheet = book.getSheetAt(0);
		
		Workbook hashFile = new XSSFWorkbook(new FileInputStream(String.format("%s_hashFile.xlsx",dataBaseName)));
		Sheet hashSheet = hashFile.getSheet(searchField);
		int hash = hashCode(searchValue);
		
		Cell rowValue = hashSheet.getRow(hash).getCell(0);
		int row = (int) rowValue.getNumericCellValue();
		
		
		hashFile.close();
		book.close();
		
		return row;
	}

	
		
	
	
	//записать данные в файл
	protected static void writeIntoFile(String dataBaseName, String value, int columnNumber) throws FileNotFoundException, IOException {
		String fullDataDaseName = String.format("%s.xlsx",dataBaseName);
		Workbook book = new XSSFWorkbook(new FileInputStream(fullDataDaseName));
		Sheet sheet = book.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum();
		Row row;
		if (columnNumber==0 || columnNumber==5) {
			row = sheet.createRow(rowCount+1);
		}
		else {
			row = sheet.getRow(rowCount);
		}
		
		Cell newCell = row.createCell(columnNumber);
		
		newCell.setCellValue(value);
		
		sheet.autoSizeColumn(columnNumber);
		
		book.write(new FileOutputStream(fullDataDaseName));
		book.close();
		
		if (columnNumber != 0) {
			Workbook hashFile = new XSSFWorkbook(new FileInputStream(String.format("%s_hashFile.xlsx",dataBaseName)));
			
			Sheet hashSheet = hashFile.getSheetAt(columnNumber-1);
			
			int hash = hashCode(value);
			Row hashRow = hashSheet.createRow(hash);
			
			Cell hashCell = hashRow.createCell(0);
			
			if (columnNumber == 4) {
				hashCell.setCellValue(rowCount+1);
			}
			else {
				hashCell.setCellValue(rowCount);
			}
			
			hashFile.write(new FileOutputStream(String.format("%s_hashFile.xlsx",dataBaseName)));
			hashFile.close();
		}

	}
	
	//проверка на существование объекта с таким же айди
	protected static boolean alreadyExist(String dataBaseName, String value) throws FileNotFoundException, IOException {
		String fullDataDaseName = dataBaseName + ".xlsx";
		Workbook book = new XSSFWorkbook(new FileInputStream(fullDataDaseName));
		Sheet sheet = book.getSheetAt(0);

		int rowCount = sheet.getLastRowNum() + 1;
		
		boolean flag = false;
		
		for (int i = 1; i < rowCount; i++) {
			Cell cell = sheet.getRow(i).getCell(0);
			String oldValue = cell.getStringCellValue();
			if(value.equals(oldValue))
				flag = true;
		}
		book.close();
		if(flag== true)
			return true;
		else
			return false;
		
	}
	
	//чтение файла
	protected static String[][] getField(String dataBaseName) throws FileNotFoundException, IOException{
		String fullDataDaseName = dataBaseName + ".xlsx";
		Workbook book = new XSSFWorkbook(new FileInputStream(fullDataDaseName));
		Sheet sheet = book.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum() + 1;
		int columnCount = 5;
		
		String[][] values = new String [rowCount][columnCount];
		
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				Cell cell = sheet.getRow(i).getCell(j);
				values[i][j] = cell.getStringCellValue();
			}
		}
		book.close();
		return values;
	}
	
	//получить шапку таблицы
	protected static String[] getHeader(String dataBaseName) throws FileNotFoundException, IOException{
		String fullDataDaseName = dataBaseName + ".xlsx";
		Workbook book = new XSSFWorkbook(new FileInputStream(fullDataDaseName));
		Sheet sheet = book.getSheetAt(0);
		
		int columnCount = 5;
		
		String[] values = new String [columnCount];
		
		for (int j = 0; j < columnCount; j++) {
			Cell cell = sheet.getRow(0).getCell(j);
			values[j] = cell.getStringCellValue();
		}
		
		book.close();
		return values;
		
	}
	
	//удалить файл
	protected static boolean deleteFile(String dataBaseName) throws FileNotFoundException, IOException{
		String fullDataDaseName = dataBaseName + ".xlsx";
		File book = new File(fullDataDaseName);
		if (book.delete()) {
		    return true;
		} else {
		    return false;
		}
	}
	
	//очистить файл
	protected static void clearFile(String dataBaseName) throws FileNotFoundException, IOException {
		String fullDataDaseName = dataBaseName + ".xlsx";
		Workbook book = new XSSFWorkbook(new FileInputStream(fullDataDaseName));
		Sheet sheet = book.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum() + 1;
		
		for (int i = 1; i < rowCount; i++) {
			sheet.removeRow(sheet.getRow(i)); 
		}
		
		FileOutputStream fileOut = new FileOutputStream(fullDataDaseName);
		book.write(fileOut);
		book.close();
		fileOut.close();
	}
	

	
	//создать файл
	public static void createFile(String fileName, String tableName) throws FileNotFoundException, IOException{
		//String fullDataDaseName = fileName + ".xlsx";
		Workbook book = new XSSFWorkbook();
		Sheet sheet = book.createSheet(tableName);
		Row row = sheet.createRow(0);

		Workbook hashBook = new XSSFWorkbook();
		
		Cell id = row.createCell(0);
		id.setCellValue("id");
		sheet.autoSizeColumn(0);
		
		Cell name = row.createCell(1);
		name.setCellValue("Название");
		sheet.autoSizeColumn(1);
		hashBook.createSheet("Название");
		
		Cell genre = row.createCell(2);
		genre.setCellValue("Жанр");
		sheet.autoSizeColumn(2);
		hashBook.createSheet("Жанр");
		
		Cell dateOfRelease = row.createCell(3);
		dateOfRelease.setCellValue("Дата выхода");
		sheet.autoSizeColumn(3);
		hashBook.createSheet("Дата выхода");
		
		Cell country = row.createCell(4);
		country.setCellValue("Страна");
		sheet.autoSizeColumn(4);
		hashBook.createSheet("Страна");
		
		String fullFileName = String.format("%s.xlsx",fileName);
		File file = new File(fullFileName);
		book.write(new FileOutputStream(file));
		book.close();
		
		String hashFileName = String.format("%s_hashFile.xlsx",fileName);
		File hashFile = new File(hashFileName);
		hashBook.write(new FileOutputStream(hashFile));
		hashBook.close();
	}
	
	
	
	//public static void main(String[] args) throws FileNotFoundException, IOException{

		
	//}
	
}
