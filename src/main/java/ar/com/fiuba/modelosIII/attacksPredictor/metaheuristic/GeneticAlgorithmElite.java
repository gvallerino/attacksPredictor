package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.GeneticAlgorithm;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public class GeneticAlgorithmElite extends GeneticAlgorithm {

	public static void execute() {
		
		loadConfigurations();
		loadPopulation();
		
		loadManagerCluster();
		loadCountGenerations();
		
		printHeader("ELITE", poblacion.size());
		
		for (int generacion = 0; generacion < cantidadGeneraciones; generacion++) {
			
			Collections.sort(poblacion);
			List<TerroristAttack> proximaPoblacion = new ArrayList<TerroristAttack>();
			printGeneration(generacion);
			managerCluster.restartInercia();
			int repeticiones = poblacion.size();
			
			for (int i = 0; i < (repeticiones-1); i++) {

				TerroristAttack son = null;
				if (mutate()) {
					TerroristAttack terroristAttackToMutate = poblacion.get(i);
					son = mutation.mutate(terroristAttackToMutate);
				} else {
					TerroristAttack father = poblacion.get(i);
					TerroristAttack mother = poblacion.get(i+1);
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
