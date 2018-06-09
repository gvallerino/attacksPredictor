package ar.com.fiuba.modelosIII.attacksPredictor.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class ManagementFile {
	
	//Reader
	private static String PATH = "./src/main/java/resources/files/";
	private static String FILE_NAME = "ataquesTerroristas.xlsx";
	private static Workbook workbook;
	
	//Writer
	private static String SEPARATOR = " | ";
	private static FileWriter writer = null;
	
	//Properties
	private static String SEPARATOR_PROPERTIES = "=";
	private static String FILE_PROPERTIES = "configuration.properties";
	
	public static void read() {
		
		System.out.println("Comenzando el proceso de carga de datos...");
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		
		try {
			
			FileInputStream excelFile = new FileInputStream(new File(PATH + "input/" + FILE_NAME));
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int id = 0;
			
			while (iterator.hasNext()) {
				
				String key = String.valueOf(id);
				Row currentRow = iterator.next();
				if(id != 0) {
					Iterator<Cell> cellIterator = currentRow.iterator();
					List<Integer> values = new ArrayList<Integer>();
					
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						Integer value = new Integer((int) currentCell.getNumericCellValue());
						values.add(value);
					}
					TerroristAttack terroristAttack = new TerroristAttack(key, values);
					//System.out.println("Guardando Ataque terrorista " + key);
//					store.save(key, terroristAttack);
					TerroristAttacksDataSet.save(key, terroristAttack);
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
	
	public static void loadConfigurations() {
		try {
			FileReader file = new FileReader(new File(PATH + "properties/" + FILE_PROPERTIES));
			BufferedReader buffer = new BufferedReader(file);
			String lineFile;

			while ((lineFile = buffer.readLine()) != null) {
				String line = lineFile.replace(" ", "");
				if (!"".equals(line) && !"#".equals(String.valueOf(line.charAt(0))) && line.contains(SEPARATOR_PROPERTIES)) {
					String[] keyValue = line.split(SEPARATOR_PROPERTIES);
					ConfigurationsDataSet.put(keyValue[0], keyValue[1]);
				}
			}
			buffer.close();
			file.close();
		} catch (Exception e) {
			System.out.println("Error | Se produjo un error procesando el archivo properties " + e.getMessage());
		}
	}
	
	public static void write(Integer numberCluster, List<Double> data) {
		try {
			loadWriter();
			writeLine(numberCluster, data);
		} catch (Exception e) {
			System.out.println("ERROR | Se produjo un error almacenando el csv" + e.getMessage());
		} 
	}
	
	public static void write(String header) {
		try {
			loadWriter();
			writer.append(header);
		} catch (Exception e) {
			System.out.println("ERROR | Se produjo un error almacenando el csv" + e.getMessage());
		} 
	}
	
	private static void writeLine(Integer numberCluster, List<Double> values) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + numberCluster + " ->" + print(values));
        sb.append("\n");
        writer.append(sb.toString());
    }
	
	private static String print(List<Double> lista) {
		//System.out.print(" | ");
		StringBuilder sb = new StringBuilder(SEPARATOR);
		for (Double value : lista) {
			String valueSingle = String.valueOf(value);
			int number = valueSingle.split("\\.")[0].length();
			int diff = Constants.COUNT_DIGITS_PRINT_CLUSTERS - number;
			String valueStr = String.format("%." + diff + "f", value);
			sb.append(valueStr).append(SEPARATOR);
		}
		return sb.toString();
	}
	
	private static void loadWriter() {
		if (writer == null) {
			String fileName = getTimeNow();
			String csvFile = PATH + "output/" + fileName + ".csv";
			try {
				writer = new FileWriter(csvFile);
			} catch (IOException e) {
				System.out.println("ERROR | Se produjo un error al abrir el csv" + e.getMessage());
			}
		}
	}
	
	public static void closeFile() {
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("ERROR | Se produjo un error al cerrar el csv" + e.getMessage());
		}
	}
	
	private static String getTimeNow() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month =  String.format("%02d",(cal.get(Calendar.MONTH) + 1));
		String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
		String hours =  String.format("%02d",cal.get(Calendar.HOUR_OF_DAY));
		String minutes =  String.format("%02d",cal.get(Calendar.MINUTE));
		String seconds =  String.format("%02d",cal.get(Calendar.SECOND));
		return year + month + day + "_" + hours + ":" + minutes + ":" + seconds;
	}
	
}
