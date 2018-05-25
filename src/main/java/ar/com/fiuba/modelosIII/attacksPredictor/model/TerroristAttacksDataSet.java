package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ReaderCSV;

public class TerroristAttacksDataSet {

	private static TerroristAttacksDataSet INSTANCE;
	
	private static Map<String, TerroristAttack> store ;
	
	private static int maxAmountKill = 0;
	private static int maxAmountWound = 0;
	
	private TerroristAttacksDataSet () {
		store = new HashMap<String, TerroristAttack>();
	}
	
	public static TerroristAttacksDataSet getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TerroristAttacksDataSet();
			ReaderCSV reader = new ReaderCSV();
			reader.loadFile();
		}
		Constants.AMOUNT_KILLS_MAX = maxAmountKill;
		Constants.AMOUNT_WOUND_MAX = maxAmountWound;
		return INSTANCE;
	}
	
	public static void save(String id, TerroristAttack terroristAttack) {
		store.put(id, terroristAttack);
		maxAmountKill = getMax(maxAmountKill, terroristAttack.getAmountKill());
		maxAmountWound = getMax(maxAmountWound, terroristAttack.getAmountWound());
	}
	
	public TerroristAttack getById(String id) {
		return store.get(id);
	}
	
	public List<TerroristAttack> filter(TerroristAttack filter) {
		List<TerroristAttack> filterList = new ArrayList<TerroristAttack>();
		filterList.add(filter);
		return this.filter(filterList);
	}
	
	public List<TerroristAttack> filter(List<TerroristAttack> filter) {
		
		if (filter == null) 
			return new ArrayList<TerroristAttack>(store.values());
		
		List<TerroristAttack> terroristAttacksFiltered = new ArrayList<TerroristAttack>();
		
		for (TerroristAttack attack : store.values()) {
			for (TerroristAttack attackFilter : filter) {
				if (!terroristAttacksFiltered.contains(attack) && attack.match(attackFilter)) {
					terroristAttacksFiltered.add(attack);
				}
			}
		}
		return terroristAttacksFiltered;
	}
	
	public long getSize() {
		return store.size();
	}
	
	public List<TerroristAttack> getAll() {
		return new ArrayList<TerroristAttack>(store.values());
	}
	
	private static int getMax(int max, int current) {
		return (current > max ? current : max);
	}
	
}
