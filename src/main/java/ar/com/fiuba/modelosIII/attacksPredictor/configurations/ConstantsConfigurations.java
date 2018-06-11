package ar.com.fiuba.modelosIII.attacksPredictor.configurations;

import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class ConstantsConfigurations {

	public static void loadConstants() {
		Map<String, String> values = ConfigurationsDataSet.getValuesByKey("constants");
		Constants.COUNT_CLUSTERS = Integer.parseInt(values.get("clusters"));
		Constants.COUNT_GENERATIONS = Integer.parseInt(values.get("generations"));
		
		Boolean useExact = Boolean.parseBoolean(values.get("exactPopulation.active"));
		Boolean useRandom = Boolean.parseBoolean(values.get("randomPopulation.active"));
		
		if (useExact) {
			Constants.POPULATION_EXACT = 0D;
			Constants.POPULATION_RANDOM_MAX = -1D;
			Constants.POPULATION_RANDOM_MIN = -1D;
		}
		
		if (useRandom) {
			Constants.POPULATION_EXACT = -1D;
			Constants.POPULATION_RANDOM_MAX = Double.parseDouble(values.get("randomPopulation.rangeMax")) / 100D;
			Constants.POPULATION_RANDOM_MIN = Double.parseDouble(values.get("randomPopulation.rangeMin")) / 100D;
		}
		
		Constants.PORCENTAJE_MUTATION = Double.parseDouble(values.get("mutation.porcentaje"));
	}
}
