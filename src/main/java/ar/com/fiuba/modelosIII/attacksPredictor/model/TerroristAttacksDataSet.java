package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

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
			ManagementFile.read();
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
		return this.filter(1, filterList);
	}
	
	public List<TerroristAttack> filter(List<TerroristAttack> filter) {
		
		if (filter == null) 
			return new ArrayList<TerroristAttack>(store.values());
		
		Map<String, Boolean> typeFilters = new HashMap<String, Boolean>();
		for (TerroristAttack attackFilter : filter) {
			if(attackFilter.getId() != null) typeFilters.put("id", Boolean.TRUE);
			if(attackFilter.getYear() != null) typeFilters.put("year", Boolean.TRUE);
			if(attackFilter.getRegion() != null) typeFilters.put("region", Boolean.TRUE);
			if(attackFilter.isMultiple() != null) typeFilters.put("multiple", Boolean.TRUE);
			if(attackFilter.isSuccess() != null) typeFilters.put("success", Boolean.TRUE);
			if(attackFilter.isSuicide() != null) typeFilters.put("suicide", Boolean.TRUE);
			if(attackFilter.getAttackType() != null) typeFilters.put("attack", Boolean.TRUE);
			if(attackFilter.getTargetType() != null) typeFilters.put("target", Boolean.TRUE);
			if(attackFilter.getWeaponType() != null) typeFilters.put("weapon", Boolean.TRUE);
			if(attackFilter.getAmountKill() != null) typeFilters.put("kill", Boolean.TRUE);
			if(attackFilter.getAmountWound() != null) typeFilters.put("wound", Boolean.TRUE);
		}
		
		int countTypesFilters = 0;
		for (Boolean value : typeFilters.values()) {
			if (value) countTypesFilters++;
		}
		
		return this.filter(countTypesFilters, filter);
	}
	
	private List<TerroristAttack> filter(int countTypesFilters, List<TerroristAttack> filter) {
		List<TerroristAttack> terroristAttacksFiltered = new ArrayList<TerroristAttack>();
		
		for (TerroristAttack attack : store.values()) {
			int countOks = 0;
			for (TerroristAttack attackFilter : filter) {
				if (!terroristAttacksFiltered.contains(attack) && attack.match(attackFilter)) {
					countOks++;
				}
			}
			if(countOks == countTypesFilters) {
				terroristAttacksFiltered.add(attack);
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
