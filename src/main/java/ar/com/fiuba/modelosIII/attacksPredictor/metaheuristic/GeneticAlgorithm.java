package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.configurations.TypeEvolution;
import ar.com.fiuba.modelosIII.attacksPredictor.configurations.TypeFilter;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.ManagerClusterRandom;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.Mutation;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class GeneticAlgorithm {
	
	private static Cruzable cruza = TypeEvolution.getCruza();
	private static Mutation mutation = TypeEvolution.getMutation();

	public static void execute() {
		System.out.println(" -----  COMENZANDO ALGORITMO GENETICO -----");
		
		Population population = new Population();
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
					son = mutation.mutate(terroristAttackToMutate);
				} else {
					TerroristAttack father = poblacion.get(Constants.getRandom(0, poblacion.size()));
					TerroristAttack mother = poblacion.get(Constants.getRandom(0, poblacion.size()));
					son = cruza.cruzar(father, mother);
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
	
}