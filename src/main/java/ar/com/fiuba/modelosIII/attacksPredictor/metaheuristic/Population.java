package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;

public class Population {

	private TerroristAttacksDataSet dataSet = TerroristAttacksDataSet.getInstance();
	
	public List<TerroristAttack> populate(List<TerroristAttack> filter) {
		List<TerroristAttack> population = new ArrayList<TerroristAttack>();
		List<TerroristAttack> dataSetFiltered = dataSet.filter(filter);
		double proba = (double)dataSetFiltered.size() / (double)dataSet.getSize();
		
		for (TerroristAttack attack : dataSetFiltered) {
			double random = Math.random() * 100.0;
			boolean include = (proba > random);
			if (include) {
				population.add(attack);
			}
		}
		return population;
	}
}
