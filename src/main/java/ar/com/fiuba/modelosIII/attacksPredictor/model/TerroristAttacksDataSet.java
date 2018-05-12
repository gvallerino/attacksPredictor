package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.reader.ReaderCSV;

public class TerroristAttacksDataSet {

	private static TerroristAttacksDataSet INSTANCE;
	
	private Map<String, TerroristAttack> store = new HashMap<String, TerroristAttack>();
	private List<TerroristAttack> storeList = new ArrayList<TerroristAttack>();
	private long size = 0L;
	
	//private Map<Integer, Integer> storeAmountKill = new HashMap<Integer, Integer>();
	private int maxAmountKill = 0;
	private int maxAmountWound = 0;
	
	private TerroristAttacksDataSet () {}
	
	public static TerroristAttacksDataSet getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TerroristAttacksDataSet();
			ReaderCSV reader = new ReaderCSV();
			reader.loadFile();
		}
		return INSTANCE;
	}
	
	public void save(String id, TerroristAttack terroristAttack) {
		store.put(id, terroristAttack);
		storeList.add(terroristAttack);
		size++;
		
		int amountKill = terroristAttack.getAmountKill();
		int amountWound = terroristAttack.getAmountWound();
		
		maxAmountKill = this.getMax(maxAmountKill, amountKill);
		maxAmountWound = this.getMax(maxAmountWound, amountWound);
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
			return storeList;
		
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
	
	public List<TerroristAttack> getAll() {
		return this.storeList;
	}
	
	public long getSize() {
		return this.size;
	}
	
	private int getMax(int max, int current) {
		return (current > max ? current : max);
	}
	
}
