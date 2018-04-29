package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.lang.annotation.Target;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public abstract class FitnessCalculator {

	//TODO: Verificar los que devuelven solo el value
	//TODO: Verificar los important de cada enum
	public static int calculate(int value, int position) {
		switch (position) {
			case 0: return calculateYearFitness(value);
			case 1: return calculateRegionFitness(value);
			case 2: return value;
			case 3: return value;
			case 4: return value;
			case 5: return calculateAttackFitness(value);
			case 6: return calculateTargetFitness(value);
			case 7: return calculateWeaponFitness(value);
			case 8: return value;
			case 9: return value;
		}
		return 0;
	}
	
	private static int calculateYearFitness(int year) {
		return (year - Constants.YEAR_MIN);
	}
	
	private static int calculateRegionFitness(int regionId) {
		return RegionEnum.getImportantById(regionId);
	}
	
	private static int calculateAttackFitness(int attackId) {
		return AttackTypeEnum.getImportantById(attackId);
	}
	
	private static int calculateTargetFitness(int targetId) {
		return TargetTypeEnum.getImportantById(targetId);
	}
	
	private static int calculateWeaponFitness(int weaponId) {
		return WeaponTypeEnum.getImportantById(weaponId);
	}
}
