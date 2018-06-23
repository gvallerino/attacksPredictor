package ar.com.fiuba.modelosIII.attacksPredictor.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class ClustersGrapher {
	
	private static List<Integer> valuesToView;
	private final static String SEPARATOR = "|";
	
	public static void loadConfiguration() {
		valuesToView = ConfigurationsDataSet.getPositionsByKey("view");
		valuesToView.add(10);
		valuesToView.add(11);
	}
	
	public static void saveClusters(int generacion, Map<Integer, List<Double>> clusters, Map<Integer, List<TerroristAttack>> store, int total, Map<Integer, Double> inercias) {
		
		String enter = "\n";
		if (generacion == 0) enter = "";
		String genStr = "Gen " + String.format("% 2d",generacion);
		String titles = getTitle();
		String header = enter + genStr + titles + "\n";
		ManagementFile.write(header);
		
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			List<Double> valuesClustersToSave = new ArrayList<Double>();
			Double porcentaje = calculatePorcentajeByCluster(store.get(i), total, i);
			
			for (int j = 0; j < clusters.get(i).size(); j++) {
				if (valuesToView.contains(j)) {
					valuesClustersToSave.add(clusters.get(i).get(j));
				}
			}
			valuesClustersToSave.add(porcentaje);
			Double inercia = calculatePorcentajeInercia(inercias.get(i), store.get(i).size());
			valuesClustersToSave.add(inercia);
			ManagementFile.write(i, valuesClustersToSave);
		}
	}

	
	
	public static void printClusters(Map<Integer, List<Double>> clusters, Map<Integer, List<TerroristAttack>> store, int total, Map<Integer, Double> inercias) {
		System.out.println("Clusters: ");
		System.out.println("      " + getTitle());
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			Double porcentaje = calculatePorcentajeByCluster(store.get(i), total, i);
			Double inercia = calculatePorcentajeInercia(inercias.get(i), store.get(i).size());
			System.out.print(" " + i + " ->"); print(clusters.get(i), porcentaje, inercia);
			System.out.println("");
		}
	}
	
	private static void print(List<Double> lista, double porcentaje, Double inercia) {
		System.out.print(" | ");
		int position = 0;
		for (Double value : lista) {
			if (valuesToView.contains(position)) {
				String valueStr = getValueFormatedToPrint(value);
				System.out.print(valueStr + " | ");
			}
			position++;
		}
		
		String valueStr = getValueFormatedToPrint(porcentaje);
		System.out.print(valueStr + " | ");
		valueStr = getValueFormatedToPrint(inercia);
		System.out.print(valueStr + " | ");
	}

	private static String getTitle() {
		String[] title = "   year  | region  | multiple| success | suicide | attack  | target  | weapon  |  kills  |  wound  |    %    | inercia |".split("\\"+SEPARATOR);
		StringBuilder titleSb = new StringBuilder(SEPARATOR);
		for (int i = 0; i < title.length; i++) {
			if (valuesToView.contains(i)) {
				titleSb.append(title[i]);
				titleSb.append(SEPARATOR);
			}
		}
		return titleSb.toString();
	}
	
	private static Double calculatePorcentajeByCluster(List<TerroristAttack> attacks, int total, int i) {
		Double num = attacks.size() * 100D;
		Double den = new Double(total);
		Double porcentaje = num.doubleValue() / den.doubleValue();
		return porcentaje;
	}
	
	private static Double calculatePorcentajeInercia(Double inercia, int i) {
		if (i == 0) return 0D;
		return inercia / (double)i;
	}
	
	private static String getValueFormatedToPrint(Double value) {
		String valueSingle = String.valueOf(value);
		int number = valueSingle.split("\\.")[0].length();
		int diff = Constants.COUNT_DIGITS_PRINT_CLUSTERS - number;
		if (diff < 0) diff = 0;
		try {
			String valueStr = String.format("%." + diff + "f", value);
			return valueStr;
			
		} catch(Exception e) {
			System.out.println("");
			return "";
		}
	}
	
}
