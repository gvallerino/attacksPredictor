package ar.com.fiuba.modelosIII.attacksPredictor.configurations;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class TypeFilter {
	
	private static TypeFilter INSTANCE = null;
	
	private static List<RegionEnum> regionFilters;
	private static List<AttackTypeEnum> attackTypeFilters;
	private static List<TargetTypeEnum> targetTypeFilters;
	private static List<WeaponTypeEnum> weaponTypeFilters;
	
	private TypeFilter() {}

	public static TypeFilter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TypeFilter();
			regionFilters = new ArrayList<RegionEnum>();
			weaponTypeFilters = new ArrayList<WeaponTypeEnum>();
			targetTypeFilters = new ArrayList<TargetTypeEnum>();
			attackTypeFilters = new ArrayList<AttackTypeEnum>();
		}
		return INSTANCE;
	}
	
//	public void addFilter(TypeEnum type, int i) {
//		if (RegionEnum.class.equals(type.getClass())) {
//			regionFilters.add(RegionEnum.getById(i));
//		} else if (AttackTypeEnum.class.equals(type.getClass())) {
//			attackTypeFilters.add(AttackTypeEnum.getById(i));
//		} else if (TargetTypeEnum.class.equals(type.getClass())) {
//			targetTypeFilters.add(TargetTypeEnum.getById(i));
//		} else if (WeaponTypeEnum.class.equals(type.getClass())) {
//			weaponTypeFilters.add(WeaponTypeEnum.getById(i));
//		}
//	}
	
	private void processFilters() {
		List<Integer> filters = ConfigurationsDataSet.getPositionsByKey("regionType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				regionFilters.add(RegionEnum.getById(position));
			}
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("attackType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				attackTypeFilters.add(AttackTypeEnum.getById(position));
			}
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("targetType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				targetTypeFilters.add(TargetTypeEnum.getById(position));
			}
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("weaponType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				weaponTypeFilters.add(WeaponTypeEnum.getById(position));
			}
		}
	}
	
	public List<TerroristAttack> applyFilters() {
		processFilters();
		List<TerroristAttack> filtersType = new ArrayList<TerroristAttack>();
		
		if (!regionFilters.isEmpty()) {
			for (RegionEnum type : regionFilters) {
				TerroristAttack attack = new TerroristAttack();
				attack.setRegion(type);
				filtersType.add(attack);
			}
		}
		
		if (!attackTypeFilters.isEmpty()) {
			for (AttackTypeEnum type: attackTypeFilters) {
				TerroristAttack attack = new TerroristAttack();
				attack.setAttackType(type);
				filtersType.add(attack);
			}
		}
		
		if (!targetTypeFilters.isEmpty()) {
			for (TargetTypeEnum type : targetTypeFilters) {
				TerroristAttack attack = new TerroristAttack();
				attack.setTargetType(type);
				filtersType.add(attack);
			}
		}
		
		if(!weaponTypeFilters.isEmpty()) {
			for (WeaponTypeEnum type : weaponTypeFilters) {
				TerroristAttack attack = new TerroristAttack();
				attack.setWeaponType(type);
				filtersType.add(attack);
			}
		}
		
		return filtersType;
	}
	
}
