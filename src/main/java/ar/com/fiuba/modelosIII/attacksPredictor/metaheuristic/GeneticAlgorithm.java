package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.CruzaEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.MutationEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.ManagerClusterRandom;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBinaria;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.Mutation;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class GeneticAlgorithm {
	
	private static Cruzable cruzaBinaria = CruzaEnum.CRUZA_BINARIA.getCruzable();
	private static Cruzable cruzaPorSegmento = CruzaEnum.CRUZA_SEGMENTO.getCruzable();
	private static Cruzable cruzaPorImportancia = CruzaEnum.CRUZA_IMPORTANCIA.getCruzable();
	private static Mutation mutationBinary = MutationEnum.MUTACION_BINARIA.getMutation();
	private static Mutation mutationValue = MutationEnum.MUTACION_POR_VALOR.getMutation();

	public static void execute() {
		
		System.out.println("Procesando Poblacion Inicial => ");

		List<TerroristAttack> filter = createFilters();
		PopulationRandom population = new PopulationRandom();
		List<TerroristAttack> poblacion = population.populate(filter);
		List<TerroristAttack> proximaPoblacion = new ArrayList<TerroristAttack>();
		ManagerClusterRandom managerCluster = new ManagerClusterRandom(poblacion);
		managerCluster.printClusters();
		int generaciones = 20;
		int repeticiones = poblacion.size();
		
		System.out.println("Tamaño de generacion inicial: " + poblacion.size());
		
		for (int generacion = 0; generacion < generaciones; generacion++) {
			
			//List<Double> prom3 = initListaPromedios();
			System.out.println("Procesando Generacion: " + String.valueOf(generacion+1) + " => ");
			int print = poblacion.size() / 100;
			int porcentaje = 0;
			for (int i = 0; i < repeticiones; i++) {
				//System.out.print(".");
				if (i % print == 0) {
					System.out.print(" " + porcentaje + "%");
					porcentaje += 1;
				}
				
				TerroristAttack son = null;
				if (mutate()) {
					//System.out.println("-------- MUTACION --------");
					TerroristAttack terroristAttackToMutate = poblacion.get(Constants.getRandom(0, poblacion.size()));
					son = mutationBinary.mutate(terroristAttackToMutate);
//					son = mutationValue.mutate(terroristAttackToMutate);
				} else {
					TerroristAttack father = poblacion.get(Constants.getRandom(0, poblacion.size()));
					TerroristAttack mother = poblacion.get(Constants.getRandom(0, poblacion.size()));
					son = cruzaBinaria.cruzar(father, mother);
//					son = cruzaPorSegmento.cruzar(father, mother);
				}
				
				managerCluster.put(son);
				proximaPoblacion.add(son);
				
				//prom3 = sumarListas(prom3, son.getValues());
			}
			
			System.out.println("Tamaño de generacion " + generacion + ": " + proximaPoblacion.size());
			//printPromedio(prom3, repeticiones);
			managerCluster.updateCentroides(generacion);
			managerCluster.printClusters();
			poblacion = proximaPoblacion;
			proximaPoblacion = new ArrayList<TerroristAttack>();
		}
		
	}
	
	private static List<Double> sumarListas(List<Double> listaDeSuma, List<Integer> listaAsumar) {
		List<Double> nuevaLista = new ArrayList<Double>();
		for (int i = 0; i < listaDeSuma.size(); i++) {
			nuevaLista.add(listaDeSuma.get(i) + listaAsumar.get(i));
		}
		return nuevaLista;
		
	}
	
	private static List<Double> initListaPromedios() {
		List<Double> lista = new ArrayList<Double>();
		for (int i = 0; i < 10; i++) {
			lista.add(0D);
		}
		return lista;
	}
	
	private static void printList (List<TerroristAttack> lista) {
		System.out.println("Tamano de lista: " + lista.size());
	}
	
	private static void printPromedio (List<Double> lista, double total) {
		System.out.print("Promedios: ");
		for (int i = 0; i < lista.size(); i++) {
			if (i != 1 && i != 5 && i!=6 && i != 7) {
				Double numero = lista.get(i);
				double promedio = numero / total;
				System.out.print(promedio + " | ");
			}
		}
		System.out.println(" ");
	}
	
	private static List<TerroristAttack> createFilterAsia() {
		List<TerroristAttack> filters = new ArrayList<TerroristAttack>();
		List<RegionEnum> regiones = new ArrayList<RegionEnum>();
		regiones.add(RegionEnum.CENTRAL_ASIA);
		regiones.add(RegionEnum.EAST_ASIA);
		regiones.add(RegionEnum.SOUTH_ASIA);
		regiones.add(RegionEnum.SOUTHEAST_ASIA);
		for (RegionEnum region : regiones) {
			TerroristAttack attack = new TerroristAttack();
			attack.setRegion(region);
			filters.add(attack);
		}
		return filters;
	}
	
	private static List<TerroristAttack> createFilterArmasFuego() {
		List<TerroristAttack> filters = new ArrayList<TerroristAttack>();
		List<WeaponTypeEnum> weapons = new ArrayList<WeaponTypeEnum>();
		weapons.add(WeaponTypeEnum.ARMAS_FUEGO);
		weapons.add(WeaponTypeEnum.EXPLOSIVOS);
		weapons.add(WeaponTypeEnum.INCENDIARIAS);
		for (WeaponTypeEnum weaponType : weapons) {
			TerroristAttack attack = new TerroristAttack();
			attack.setWeaponType(weaponType);
			filters.add(attack);
		}
		return filters;
	}
	
	private static List<TerroristAttack> createFilters() {
		List<TerroristAttack> filters = new ArrayList<TerroristAttack>();
		filters.addAll(createFilterArmasFuego());
		filters.addAll(createFilterAsia());
		return filters;
	}
	
	private static boolean mutate() {
		double random = 0;
		while (random == 0) {
			random = Constants.getRandom(0, 100);
		}
		//System.out.println(random);
		return Constants.PORCENTAJE_MUTATION > random;
	}
	
}


//List<TerroristAttack> filter = createFilters();
//Population population = new Population();
//List<TerroristAttack> poblacion1 = population.populate(filter);
//List<TerroristAttack> poblacion2 = population.populate(filter);
//List<TerroristAttack> proximaPoblacion = new ArrayList<TerroristAttack>();
//
//printList(poblacion1);
//printList(poblacion2);
//
//List<Double> prom1 = initListaPromedios();
//List<Double> prom2 = initListaPromedios();
//List<Double> prom3 = initListaPromedios();
//
//int max = poblacion1.size() > poblacion2.size() ? poblacion1.size() : poblacion2.size();
//
//for (int i = 0; i < max; i++) {
//	
//	TerroristAttack father = null;
//	TerroristAttack mother = null;
//	TerroristAttack son = null;
//	
//	if (i< poblacion1.size()) {
//		father = poblacion1.get(i);
//		prom1 = sumarListas(prom1, father.getValues());
//	}
//	
//	if (i < poblacion2.size()) {
//		mother = poblacion2.get(i);
//		prom2 = sumarListas(prom2, mother.getValues());
//	}
//		 
//	if (father != null && mother != null) {
//		son = cruzaPorSegmento.cruzar(father, mother);
//		prom3 = sumarListas(prom3, son.getValues());
//		proximaPoblacion.add(son);
//	}
//}
//
//printList(proximaPoblacion);
//printPromedio(prom1, poblacion1.size());
//printPromedio(prom2, poblacion2.size());
//printPromedio(prom3, proximaPoblacion.size());
