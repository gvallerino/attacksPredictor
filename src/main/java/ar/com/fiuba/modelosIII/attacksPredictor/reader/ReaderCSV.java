package ar.com.fiuba.modelosIII.attacksPredictor.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksStore;

public class ReaderCSV {
	
	private static String PATH = "./src/main/java/resources/files/";
	private static String FILE_NAME = "ataquesTerroristas.xlsx";
	private TerroristAttacksStore store = TerroristAttacksStore.getInstance();
	
	public void loadFile() {
		
		System.out.println("Comenzando el proceso de carga de datos...");
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		
		try {
			
			FileInputStream excelFile = new FileInputStream(new File(PATH + FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int id = 1;
			
			while (iterator.hasNext()) {
				
				String key = String.valueOf(id);
				Row currentRow = iterator.next();
				if(id != 1) {
					Iterator<Cell> cellIterator = currentRow.iterator();
					List<Integer> values = new ArrayList<Integer>();
					
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						Integer value = new Integer((int) currentCell.getNumericCellValue());
						values.add(value);
					}
					TerroristAttack terroristAttack = new TerroristAttack(key, values);
					System.out.println("Guardando Ataque terrorista " + key);
					store.save(key, terroristAttack);
				}
				id++;
			}
			excelFile.close();
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		time_end = System.currentTimeMillis();
		System.out.println("Ha finalizado el proceso de carga de datos en "+ ( time_end - time_start ) / 1000.0 +" segundos");
	}
}
