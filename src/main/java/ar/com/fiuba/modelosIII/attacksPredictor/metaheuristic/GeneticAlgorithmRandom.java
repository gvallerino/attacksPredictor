package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.GeneticAlgorithm;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class GeneticAlgorithmRandom extends GeneticAlgorithm {
	
	public static void execute() {
		
		loadConfigurations();
		loadPopulation();
		
		loadManagerCluster();
		loadCountGenerations();
		
		printHeader("RANDOM", poblacion.size());
		
		for (int generacion = 0; generacion < cantidadGeneraciones; generacion++) {
			
			List<TerroristAttack> proximaPoblacion = new ArrayList<TerroristAttack>();
			printGeneration(generacion);
			managerCluster.restartInercia();
			int repeticiones = poblacion.size();

			for (int i = 0; i < repeticiones-1; i++) {

				TerroristAttack son = null;
				if (mutate()) {
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
			closeGeneration(generacion);
			poblacion = proximaPoblacion;
		}
		managerCluster.finalize();
	}
	
}