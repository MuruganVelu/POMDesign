package mrgn.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataExtraction {

	public static FileOutputStream fos;
	public static FileInputStream fis;
	public static XSSFWorkbook excelWorkBook;
	public static XSSFSheet sheet;
	public static Row row;
	public static Cell cellValue;
	public static String headerValue = null;
	public static List<Object> header = new ArrayList<Object>();
	public static List<Object> cellValues = new ArrayList<Object>();
	public static Map<Object, Object> mapData = new HashMap<Object, Object>();
	public static String data;

	public static String getData(String fileName, String sheetName, int dataIndex, String dataKey) {
		try {

			fis = new FileInputStream(fileName);

			excelWorkBook = new XSSFWorkbook(fis);

			sheet = excelWorkBook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum();

			int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();

			System.out.println("Total Row Number:" + rowCount);

			System.out.println("Total Cell Number:" + cellCount);

			row = sheet.getRow(0);

			for (int i = 0; i < cellCount; i++) {

				cellValue = row.getCell(i);

				headerValue = cellValue.getStringCellValue().trim();

				System.out.println("Header Value are :" + headerValue);

				header.add(headerValue);

			}

			System.out.println("Header Size:" + header.size());

			for (int i = dataIndex; i <= rowCount; i++) {

				if (i == dataIndex) {

					for (int j = 0; j < cellCount; j++) {

						row = sheet.getRow(i);

						cellValue = row.getCell(j);

						String cellContents = cellValue.getStringCellValue().trim();

						System.out.println("Cell Values are :" + cellContents);

						cellValues.add(cellContents);
					}
				}
			}

			System.out.println("CellContent Size:" + cellValues.size());

			for (int i = 0; i < header.size(); i++) {
				
				mapData.put(header.get(i), cellValues.get(i));
			}
			
			System.out.println(mapData);

			System.out.println("the value for the key (UserName)is==>" + mapData.get(dataKey));

			data = (String) mapData.get(dataKey);

			System.out.println("Final Test data Result :" + data);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return data;
	}

}
