package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.HashMap;
import java.util.Map;

public class TerroristAttacksStore {

	private static TerroristAttacksStore INSTANCE;
	private Map<String, TerroristAttack> store = new HashMap<String, TerroristAttack>();
	
	private TerroristAttacksStore () {}
	
	public static TerroristAttacksStore getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TerroristAttacksStore();
		}
		return INSTANCE;
	}
	
	public void save(String id, TerroristAttack terroristAttack) {
		store.put(id, terroristAttack);
	}
	
	public TerroristAttack getById(String id) {
		return store.get(id);
	}
}
