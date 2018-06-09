package ar.com.fiuba.modelosIII.attacksPredictor.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class ClustersGrapher {
	
	private static List<Integer> valuesToView;
	private final static String SEPARATOR = "|";
	
	public static void loadConfiguration() {
		valuesToView = ConfigurationsDataSet.getAllByKey("view");
	}
	
	public static void saveClusters(int generacion, Map<Integer, List<Double>> clusters) {
		
		String enter = "\n";
		if (generacion == 0) enter = "";
		String genStr = "Gen " + String.format("% 2d",generacion);
		String titles = getTitle();
		String header = enter + genStr + titles + "\n";
		ManagementFile.write(header);
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			List<Double> valuesClustersToSave = new ArrayList<Double>();
			for (int j = 0; j < clusters.get(i).size(); j++) {
				if (valuesToView.contains(j)) {
					valuesClustersToSave.add(clusters.get(i).get(j));
				}
			}
			ManagementFile.write(i, valuesClustersToSave);
		}
	}
	
	public static void printClusters(Map<Integer, List<Double>> clusters) {
		System.out.println("Clusters: ");
		System.out.println("      " + getTitle());
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			System.out.print(" " + i + " ->"); print(clusters.get(i));
			System.out.println("");
		}
	}
	
	private static void print(List<Double> lista) {
		System.out.print(" | ");
		int position = 0;
		for (Double value : lista) {
			if (valuesToView.contains(position)) {
				String valueSingle = String.valueOf(value);
				int number = valueSingle.split("\\.")[0].length();
				int diff = Constants.COUNT_DIGITS_PRINT_CLUSTERS - number;
				String valueStr = String.format("%." + diff + "f", value);
				System.out.print(valueStr + " | ");
			}
			position++;
		}
	}
	
	private static String getTitle() {
		String[] title = "   year  | region  | multiple| success | suicide | attack  | target  | weapon  |  kills  |  wound  |".split("\\"+SEPARATOR);
		StringBuilder titleSb = new StringBuilder(SEPARATOR);
		for (int i = 0; i < title.length; i++) {
			if (valuesToView.contains(i)) {
				titleSb.append(title[i]);
				titleSb.append(SEPARATOR);
			}
		}
		return titleSb.toString();
	}

}
