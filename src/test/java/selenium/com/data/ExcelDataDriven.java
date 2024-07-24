package selenium.com.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {

	public Map<String, String> getData() throws IOException {
		Map<String, String> dataMap = new HashMap<String, String>();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\selenium\\com\\data\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cellIterator = firstRow.iterator();

		int colIndex = 0;
		Map<String, Integer> headerMap = new HashMap<>();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			headerMap.put(cell.getStringCellValue().trim(), colIndex);
			colIndex++;
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row currentRow = sheet.getRow(i);
			if (currentRow != null) {
				for (String key : headerMap.keySet()) {
					Cell cell = currentRow.getCell(headerMap.get(key));
					String cellValue = "";
					if (cell != null) {
						if (cell.getCellType() == CellType.STRING) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == CellType.NUMERIC) {
							cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
						}
					}
					dataMap.put(key, cellValue);
				}
			}
		}

		workbook.close();
		fis.close();

		return dataMap;
	}
}