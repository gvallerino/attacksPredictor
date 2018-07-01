package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;

public class FitnessConfiguration {

	public static Map<String, String> getConfigurations() {
		Map<String, String> configurations = ConfigurationsDataSet.getValuesByKey("aptitud");
		return configurations;
	}
}
