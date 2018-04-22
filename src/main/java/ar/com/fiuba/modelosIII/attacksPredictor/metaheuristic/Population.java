package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;

public class Population {

	private TerroristAttacksDataSet dataSet = TerroristAttacksDataSet.getInstance();
	
	public List<TerroristAttack> populate(List<TerroristAttack> filter) {
		List<TerroristAttack> population = new ArrayList<TerroristAttack>();
		List<TerroristAttack> dataSetFiltered = dataSet.getAll();
		double proba = 0.5D;
		if (filter != null) {
			dataSetFiltered = dataSet.filter(filter);
			proba = (double)dataSetFiltered.size() / (double)dataSet.getSize();
		}	
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
