package ar.com.fiuba.modelosIII.attacksPredictor.configurations;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

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
		List<Integer> filtersType = ConfigurationsDataSet.getPositionsByKey("filtersType");
		for (Integer position : filtersType) {
			if (position.intValue() == 0) {
				processCommonFilters();
			}
			if (position.intValue() == 1) {
				processIndividualFilters();
			}
		}
		
	}
	
	private void processCommonFilters() {
		List<Integer> filters = ConfigurationsDataSet.getPositionsByKey("common.regionType");
		if (!filters.isEmpty()) {
			addCommonRegionTypeFilters(filters);
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("common.weaponType");
		if (!filters.isEmpty()) {
			addCommonWeaponTypeFilters(filters);
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("common.targetType");
		if (!filters.isEmpty()) {
			addCommonTargetTypeFilters(filters);
		}
		
	}
	
	private void processIndividualFilters() {
		List<Integer> filters = ConfigurationsDataSet.getPositionsByKey("filters.regionType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				regionFilters.add(RegionEnum.getById(position));
			}
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("filters.attackType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				attackTypeFilters.add(AttackTypeEnum.getById(position));
			}
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("filters.targetType");
		if (!filters.isEmpty()) {
			for (Integer position : filters) {
				targetTypeFilters.add(TargetTypeEnum.getById(position));
			}
		}
		
		filters = ConfigurationsDataSet.getPositionsByKey("filters.weaponType");
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
	
	private void addCommonRegionTypeFilters(List<Integer> filters) {
		for (Integer position : filters) {
			switch(position.intValue()) {
				case 0: regionFilters.add(RegionEnum.NORTH_AMERICA);
						break;
				
				case 1: regionFilters.add(RegionEnum.NORTH_AMERICA);
						regionFilters.add(RegionEnum.CENTRAL_AMERICA);
						regionFilters.add(RegionEnum.SOUTH_AMERICA);
						break;
						
				case 2: regionFilters.add(RegionEnum.CENTRAL_ASIA);
						regionFilters.add(RegionEnum.EAST_ASIA);
						regionFilters.add(RegionEnum.SOUTH_ASIA);
						regionFilters.add(RegionEnum.SOUTHEAST_ASIA);
						break;
						
				case 3: regionFilters.add(RegionEnum.EASTERN_EUROPE);
						regionFilters.add(RegionEnum.WESTERN_EUROPE);
						break;
			}
		}
	}
	
	
	private void addCommonWeaponTypeFilters(List<Integer> filters) {
		for (Integer position : filters) {
			switch(position) {
				case 0: weaponTypeFilters.add(WeaponTypeEnum.ARMAS_FUEGO);
						weaponTypeFilters.add(WeaponTypeEnum.EXPLOSIVOS);
						weaponTypeFilters.add(WeaponTypeEnum.INCENDIARIAS);
						break;
				
				case 1: weaponTypeFilters.add(WeaponTypeEnum.ARMAS_FALSAS);
						weaponTypeFilters.add(WeaponTypeEnum.CUERPO_A_CUERPO);
						weaponTypeFilters.add(WeaponTypeEnum.VEHICULO);
						weaponTypeFilters.add(WeaponTypeEnum.SABOTAJE);
						weaponTypeFilters.add(WeaponTypeEnum.OTROS);
						break;
						
				case 2: weaponTypeFilters.add(WeaponTypeEnum.BIOLOGICO);
						weaponTypeFilters.add(WeaponTypeEnum.QUIMICO);
						weaponTypeFilters.add(WeaponTypeEnum.RADIOACTIVO);
						weaponTypeFilters.add(WeaponTypeEnum.NUCLEAR);
						break;
			}
		}
	}
	
	private void addCommonTargetTypeFilters(List<Integer> filters) {
		for (Integer position : filters) {
			switch(position) {
				case 0: targetTypeFilters.add(TargetTypeEnum.GOBIERNO_GENERAL);
						targetTypeFilters.add(TargetTypeEnum.POLICIA);
						targetTypeFilters.add(TargetTypeEnum.MILITAR);
						targetTypeFilters.add(TargetTypeEnum.GOBIERNO_DIPLOMATICOS);
						targetTypeFilters.add(TargetTypeEnum.TERRORISTAS);
						targetTypeFilters.add(TargetTypeEnum.GRUPOS_POLITICOS);
						break;
				
				case 1: targetTypeFilters.add(TargetTypeEnum.NEGOCIOS);
						targetTypeFilters.add(TargetTypeEnum.ABORTO);
						targetTypeFilters.add(TargetTypeEnum.AERONAVES_AEROPUERTOS); 
						targetTypeFilters.add(TargetTypeEnum.INSTITUCIONES_EDUCACIONALES); 
						targetTypeFilters.add(TargetTypeEnum.COMIDA_AGUA);
						targetTypeFilters.add(TargetTypeEnum.PERIODISTAS);
						targetTypeFilters.add(TargetTypeEnum.MARITIMO);
						targetTypeFilters.add(TargetTypeEnum.NGO);
						targetTypeFilters.add(TargetTypeEnum.OTROS);
						targetTypeFilters.add(TargetTypeEnum.CIVILES_Y_PROPIEDADES);
						targetTypeFilters.add(TargetTypeEnum.INSTITUCIONES_RELIGIOSAS);
						targetTypeFilters.add(TargetTypeEnum.TELECOMUNICACION);
						targetTypeFilters.add(TargetTypeEnum.TURISTAS);
						targetTypeFilters.add(TargetTypeEnum.TRANSPORTE);
						targetTypeFilters.add(TargetTypeEnum.UTILIDADES);
						targetTypeFilters.add(TargetTypeEnum.EMPTY);
						targetTypeFilters.add(TargetTypeEnum.NOTHING);
						break;
			}
		}
	}
}
