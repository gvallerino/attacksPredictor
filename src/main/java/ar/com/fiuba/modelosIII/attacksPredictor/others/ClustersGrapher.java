package ar.com.fiuba.modelosIII.attacksPredictor.others;

import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class ClustersGrapher {
	
	public static void saveClusters(int generacion, Map<Integer, List<Double>> clusters) {
		
		String enter = "\n";
		if (generacion == 0) enter = "";
		String genStr = "Gen " + String.format("% 2d",generacion);
		String titles = "|   year  | region  | multiple| success | suicide | attack  | target  | weapon  |  kills  |  wound  |";
		String header = enter + genStr + titles + "\n";
		ManagementFile.write(header);
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			ManagementFile.write(i, clusters.get(i));
		}
	}
	
	public static void printClusters(Map<Integer, List<Double>> clusters) {
		System.out.println("Clusters: ");
		System.out.println("      |   year  | region  | multiple| success | suicide | attack  | target  | weapon  |  kills  |  wound  |");
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			System.out.print(" " + i + " ->"); print(clusters.get(i));
			System.out.println("");
		}
	}
	
	private static void print(List<Double> lista) {
		System.out.print(" | ");
		for (Double value : lista) {
			String valueSingle = String.valueOf(value);
			int number = valueSingle.split("\\.")[0].length();
			int diff = Constants.COUNT_DIGITS_PRINT_CLUSTERS - number;
			String valueStr = String.format("%." + diff + "f", value);
			System.out.print(valueStr + " | ");
		}
	}
	

}
