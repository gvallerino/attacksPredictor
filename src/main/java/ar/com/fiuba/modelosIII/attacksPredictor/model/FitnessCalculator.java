package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public abstract class FitnessCalculator {

	//TODO: Faltaria agregar importancia de los enums (o no)
	//TODO: Verificar nulls o numeros negativos
	
	public static List<Integer> calculate(List<Integer> values) {
		List<Integer> fitness = new ArrayList<Integer>();
		
		int fitnessYear = calculateYearFitness(values.get(0));
		int fitnessRegion = 0;
		int fitnessMultiple = values.get(2) * 5;
		int fitnessSuccess = values.get(3) * 10;
		int fitnessSuicide = values.get(4) * 3;
		int fitnessAttack = 0;
		int fitnessTarget = 0;
		int fitnessWeapon = 0;
		int fitnessKill = calculateMounts(values.get(8), fitnessMultiple, fitnessSuccess, fitnessSuicide);
		int fitnessWound = calculateMounts(values.get(9), fitnessMultiple, fitnessSuccess, fitnessSuicide);
		
		fitness.add(fitnessYear);
		fitness.add(fitnessRegion);
		fitness.add(fitnessMultiple);
		fitness.add(fitnessSuccess);
		fitness.add(fitnessSuicide);
		fitness.add(fitnessAttack);
		fitness.add(fitnessTarget);
		fitness.add(fitnessWeapon);
		fitness.add(fitnessKill);
		fitness.add(fitnessWound);
		
		return fitness;
	}
	
	private static int calculateYearFitness(int year) {
		return (year - Constants.YEAR_MIN);
	}
	
	private static int calculateMounts(int mount, int fitnessMultiple, int fitnessSuccess, int fitnessSuicide) {
		return (mount * fitnessMultiple) + (mount * fitnessSuccess) + (mount * fitnessSuicide);
	}
	
//	private static int calculateRegionFitness(int regionId) {
//		return RegionEnum.getImportantById(regionId);
//	}
//	
//	private static int calculateAttackFitness(int attackId) {
//		return AttackTypeEnum.getImportantById(attackId);
//	}
//	
//	private static int calculateTargetFitness(int targetId) {
//		return TargetTypeEnum.getImportantById(targetId);
//	}
//	
//	private static int calculateWeaponFitness(int weaponId) {
//		return WeaponTypeEnum.getImportantById(weaponId);
//	}
}
