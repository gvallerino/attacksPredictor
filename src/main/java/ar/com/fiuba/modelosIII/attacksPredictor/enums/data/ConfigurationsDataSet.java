package ar.com.fiuba.modelosIII.attacksPredictor.enums.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class ConfigurationsDataSet {

	private static ConfigurationsDataSet INSTANCE;
	
	private static Map<String, String> store ;
	
	private ConfigurationsDataSet() {
		store = new LinkedHashMap<String, String>();
	}
	
	public static ConfigurationsDataSet getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConfigurationsDataSet();
			ManagementFile.loadConfigurations();
		}
		return INSTANCE;
	}
	
	public static void put(String key, String value) {
		store.put(key, value);
	}
	
	public static List<Integer> getAllByKey(String key) {
		List<Integer> positions = new ArrayList<Integer>();
		int position = 0;
		for (String keyStore : store.keySet()) {
			String valueStore = store.get(keyStore);
			if (keyStore.contains(key)) {
				if (valueStore.equalsIgnoreCase("true")) {
					positions.add(position);
				}
				position++;
			}
		}
		return positions;
	}
}
