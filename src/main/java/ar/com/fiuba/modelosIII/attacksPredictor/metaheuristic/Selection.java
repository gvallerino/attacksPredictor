package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Logger;

public class Selection {

	public static void execute() {
		List<Integer> position = ConfigurationsDataSet.getPositionsByKey("selection");
		boolean execute = false;
		if (position != null && position.size() == 1) {
			int selected = position.get(0);
			if (selected == 0) {
				GeneticAlgorithmRandom.execute();
				execute = true;
			} else if (selected == 1) {
				GeneticAlgorithmElite.execute();
				execute = true;
			}
		}
		
		if(!execute) {
			Logger.printError("No se encontró configuración para la Selección");
		}
		Logger.printHeader("finalizó proceso de algoritmo genético");
	}
}
