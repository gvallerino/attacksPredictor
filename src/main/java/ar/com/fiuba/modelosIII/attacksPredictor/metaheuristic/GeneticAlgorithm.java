package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.CruzaEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.MutationEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.filters.TypeFilter;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.ManagerClusterRandom;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBinaria;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.Mutation;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class GeneticAlgorithm {
	
	private static Cruzable cruzaBinaria = CruzaEnum.CRUZA_BINARIA.getCruzable();
	private static Cruzable cruzaPorSegmento = CruzaEnum.CRUZA_SEGMENTO.getCruzable();
	private static Cruzable cruzaPorImportancia = CruzaEnum.CRUZA_IMPORTANCIA.getCruzable();
	private static Mutation mutationBinary = MutationEnum.MUTACION_BINARIA.getMutation();
	private static Mutation mutationValue = MutationEnum.MUTACION_POR_VALOR.getMutation();

	public static void execute() {
		System.out.println(" -----  COMENZANDO ALGORITMO GENETICO -----");
		
		PopulationRandom population = new PopulationRandom();
		List<TerroristAttack> filter = getFilters();
		List<TerroristAttack> poblacion = population.populate(filter);
		
		List<TerroristAttack> proximaPoblacion = new ArrayList<TerroristAttack>();
		ManagerClusterRandom managerCluster = new ManagerClusterRandom(poblacion);
		int generaciones = Constants.COUNT_GENERATIONS;
		int repeticiones = poblacion.size();
		
		System.out.println("\nTamaño de generaciones: " + poblacion.size());
		System.out.println("Procesando Generacion Inicial  =>  "); 
		
		for (int generacion = 0; generacion < generaciones; generacion++) {
			
			System.out.println("Procesando Generacion: " + String.valueOf(generacion+1) + " => ");
			for (int i = 0; i < repeticiones; i++) {

				TerroristAttack son = null;
				if (mutate()) {
					//System.out.println("-------- MUTACION --------");
					TerroristAttack terroristAttackToMutate = poblacion.get(Constants.getRandom(0, poblacion.size()));
//					son = mutationBinary.mutate(terroristAttackToMutate);
					son = mutationValue.mutate(terroristAttackToMutate);
				} else {
					TerroristAttack father = poblacion.get(Constants.getRandom(0, poblacion.size()));
					TerroristAttack mother = poblacion.get(Constants.getRandom(0, poblacion.size()));
//					son = cruzaBinaria.cruzar(father, mother);
					son = cruzaPorSegmento.cruzar(father, mother);
				}
				managerCluster.put(son);
				proximaPoblacion.add(son);
			}
			
			managerCluster.updateCentroides(generacion);
			managerCluster.saveClusters(generacion);
			poblacion = proximaPoblacion;
			proximaPoblacion = new ArrayList<TerroristAttack>();
		}
		managerCluster.finalize();
	}
	
//	private static List<TerroristAttack> createFilterAsia() {
//		List<TerroristAttack> filters = new ArrayList<TerroristAttack>();
//		List<RegionEnum> regiones = new ArrayList<RegionEnum>();
//		regiones.add(RegionEnum.CENTRAL_ASIA);
//		regiones.add(RegionEnum.EAST_ASIA);
//		regiones.add(RegionEnum.SOUTH_ASIA);
//		regiones.add(RegionEnum.SOUTHEAST_ASIA);
//		for (RegionEnum region : regiones) {
//			TerroristAttack attack = new TerroristAttack();
//			attack.setRegion(region);
//			filters.add(attack);
//		}
//		return filters;
//	}
//	
//	private static List<TerroristAttack> createFilterArmasFuego() {
//		List<TerroristAttack> filters = new ArrayList<TerroristAttack>();
//		List<WeaponTypeEnum> weapons = new ArrayList<WeaponTypeEnum>();
//		weapons.add(WeaponTypeEnum.ARMAS_FUEGO);
//		weapons.add(WeaponTypeEnum.EXPLOSIVOS);
//		weapons.add(WeaponTypeEnum.INCENDIARIAS);
//		for (WeaponTypeEnum weaponType : weapons) {
//			TerroristAttack attack = new TerroristAttack();
//			attack.setWeaponType(weaponType);
//			filters.add(attack);
//		}
//		return filters;
//	}
//	
//	private static List<TerroristAttack> createFilters() {
//		List<TerroristAttack> filters = new ArrayList<TerroristAttack>();
//		filters.addAll(createFilterArmasFuego());
//		filters.addAll(createFilterAsia());
//		return filters;
//	}
	
	private static List<TerroristAttack> getFilters() {
		return TypeFilter.getInstance().applyFilters();
	}
	
	private static boolean mutate() {
		if (Constants.PORCENTAJE_MUTATION == 0) {
			return false;
		}
		double random = 0;
		while (random == 0) {
			random = Constants.getRandom(0, 100);
		}
		return Constants.PORCENTAJE_MUTATION > random;
	}
	
//	private static void check(List<TerroristAttack> attacks, String dato) {
//		for (TerroristAttack attack : attacks) {
//			if (attack.getValues().size() > 10) {
//				System.out.println(dato + ": mayor a diez");
//			}
//		}
//	}
	
}