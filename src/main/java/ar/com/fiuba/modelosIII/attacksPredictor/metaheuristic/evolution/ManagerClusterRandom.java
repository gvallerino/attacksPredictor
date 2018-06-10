package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.*;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.ClustersGrapher;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class ManagerClusterRandom {
	
	private Map<Integer, List<Double>> clusters;
	private Map<Integer, List<TerroristAttack>> store;
	
	public ManagerClusterRandom (List<TerroristAttack> population) {
		clusters = new HashMap<Integer, List<Double>>();
		store = new HashMap<Integer, List<TerroristAttack>>();
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			int random = Constants.getRandom(0, population.size());
			TerroristAttack cluster = population.get(random);
			clusters.put(i, Constants.toDouble(cluster.getValues()));
			
			List<TerroristAttack> storeList = new ArrayList<TerroristAttack>();
			storeList.add(cluster);
			store.put(i, storeList);
		}
		
	}
	
//	private void loadClusters() {
//		List<Integer> values = new ArrayList<Integer> ();
//		values.add(2008);
//		values.add(8);
//		values.add(0);
//		values.add(0);
//		values.add(0);
//		values.add(3);
//		values.add(14);
//		values.add(6);
//		values.add(0);
//		values.add(1);
//		
//		
//	}
	
	public void put(TerroristAttack attack) {
		double distance = calculateDistance(attack, clusters.get(0));
		double minDistance = distance;
		int keyMin = 0;
		for (int i = 1; i < Constants.COUNT_CLUSTERS; i++) {
			distance = calculateDistance(attack, clusters.get(i));
			if (distance < minDistance) {
				keyMin = i;
			}
		}
		store.get(keyMin).add(attack);
	}
	
	public void updateCentroides(int generacion) {
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			List<TerroristAttack> attacks = store.get(i);
			if (attacks.size() > 0) {
				List<Double> promedio = calculatePromedio(attacks);
				clusters.put(i, promedio);
			}
		}
	}
	
	private Double calculateDistance(TerroristAttack attack, List<Double> centroide) {
		Double module = 0D;
		for (int i = 0; i < Constants.COUNT_DATA_TYPE; i++) {
			if (i != 1 && i != 5 && i!=6 && i != 7) {
				double base = attack.getValues().get(i) - centroide.get(i);
				double potencia = Math.pow(base, 2);
				module += potencia;
			}
		}
		module = Math.sqrt(module);
		return module;
	}
	
	private List<Double> calculatePromedio(List<TerroristAttack> attacks) {
		List<Double> sumaTotal = initListaPromedios();
		Double countTotal = (double) attacks.size();
		for (TerroristAttack attack : attacks) {
			sumaTotal = sumarListas(sumaTotal, attack.getValues());
		}
		List<Double> promedio = new ArrayList<Double>();
		for (Double value : sumaTotal) {
			promedio.add(value / countTotal);
		}
		return promedio;
	}
	
	private List<Double> initListaPromedios() {
		List<Double> lista = new ArrayList<Double>();
		for (int i = 0; i < 10; i++) {
			lista.add(0D);
		}
		return lista;
	}
	
	private List<Double> sumarListas(List<Double> listaDeSuma, List<Integer> listaAsumar) {
		List<Double> nuevaLista = new ArrayList<Double>();
		for (int i = 0; i < listaDeSuma.size(); i++) {
			try {
				nuevaLista.add(listaDeSuma.get(i) + listaAsumar.get(i));
			} catch (Exception e) {
				System.out.println("error");
			}
		}
		return nuevaLista;
		
	}
	
	public void saveClusters(int generacion) {
		int total = getCountDataTotal();
		ClustersGrapher.saveClusters(generacion, clusters, store, total);
		ClustersGrapher.printClusters(clusters, store, total);
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			store.get(i).clear();
		}
	}
	
	private Integer getCountDataTotal() {
		Integer total = 0;
		
		for (int i = 0; i < Constants.COUNT_CLUSTERS; i++) {
			total += store.get(i).size();
		}
		return total;
	}
	
	public void finalize() {
		clusters = null;
		store = null;
		ManagementFile.closeFile();
	}
	
}
