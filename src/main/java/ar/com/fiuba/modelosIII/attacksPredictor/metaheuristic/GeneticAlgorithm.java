package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;

public class GeneticAlgorithm {

	public static void execute() {
		TerroristAttacksDataSet dataSet = TerroristAttacksDataSet.getInstance();
		List<TerroristAttack> dataList = dataSet.getAll();
		
	}
	
	
}
