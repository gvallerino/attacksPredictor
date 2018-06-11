package ar.com.fiuba.modelosIII.attacksPredictor.enums.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ConfigurationsDataSet {

	private static ConfigurationsDataSet INSTANCE;
	
	private static Map<String, String> store ;
	
	private ConfigurationsDataSet() {
		store = new LinkedHashMap<String, String>();
	}
	
	public static ConfigurationsDataSet getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConfigurationsDataSet();
		}
		return INSTANCE;
	}
	
	public static void put(String key, String value) {
		store.put(key, value);
	}
	
	public static List<Integer> getPositionsByKey(String key) {
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
	
	public static Map<String, String> getValuesByKey(String key) {
		Map<String, String> values = new HashMap<String, String>();
		for (String keyStore : store.keySet()) {
			if (keyStore.contains(key)) {
				String keyToSave = ConfigurationsDataSet.getKey(key, keyStore);
				values.put(keyToSave, store.get(keyStore));
			}
		}
		return values;
	}
	
	private static String getKey(String keyQuestion, String keyStore) {
		String[] keySplite = keyStore.split("\\.");
		StringBuilder keyReturned = new StringBuilder();
		boolean visiteKey = false;
		for (int i = 0; i < keySplite.length; i++) {
			String singleKeyStore = keySplite[i];
			
			if (visiteKey) {
				keyReturned.append(".");
				keyReturned.append(singleKeyStore);
			}
			if (keyQuestion.equalsIgnoreCase(singleKeyStore)) {
				visiteKey = true;
			}
		}
		return keyReturned.toString().substring(1);
	}
}
