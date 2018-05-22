package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class PopulationRandom {

	private TerroristAttacksDataSet dataSet = TerroristAttacksDataSet.getInstance();
	
	public List<TerroristAttack> populate(Object filter) {
		if (filter == null) {
			return this.populate(null);
		}
		List<TerroristAttack> list = new ArrayList<TerroristAttack>();
		list.add((TerroristAttack) filter);
		return this.populate(list);
	}
	
	public List<TerroristAttack> populate(List<TerroristAttack> filter) {
		List<TerroristAttack> dataSetFiltered = dataSet.filter(filter);

		Double limitSup = dataSetFiltered.size() * Constants.POPULATION_RANDOM_MAX;
		Double limitInf = dataSetFiltered.size() * Constants.POPULATION_RANDOM_MIN;
		
		List<TerroristAttack> population = new ArrayList<TerroristAttack>();
		Double limitRandom = (double) Constants.getRandom(limitInf.intValue(), limitSup.intValue());
		
		while(population.size() < limitRandom) {
			int indexRandom = Constants.getRandom(0, population.size());
			population.add(dataSetFiltered.get(indexRandom));
		}
		
		return population;
	}
}


/*while (limitInf > population.size()) {
for (TerroristAttack attack : dataSetFiltered) {
	double random = Math.random();
	boolean include = (proba >= random);
	if (include) {
		if ((population.size() + 1) > limitSup) {
			return population;
		}
		population.add(attack);
	}
}
}*/