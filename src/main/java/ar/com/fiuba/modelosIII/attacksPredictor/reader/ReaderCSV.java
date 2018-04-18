package ar.com.fiuba.modelosIII.attacksPredictor.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReaderCSV {
	
	private static String PATH = "../attacksPredictor/src/main/java/resources/files/ataquesTerroristas.xlsx";
	private static String FILE_NAME = "ataquesTerroristas.xlsx";
	
	public void loadFile() {
		
		try {
			FileInputStream excelFile = new FileInputStream(new File(PATH + FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			while (iterator.hasNext()) {
				
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				
				while (cellIterator.hasNext()) {
					
					Cell currentCell = cellIterator.next();
					//getCellTypeEnum shown as deprecated for version 3.15
					//getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}
					
				}
				System.out.println();
			}		
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
