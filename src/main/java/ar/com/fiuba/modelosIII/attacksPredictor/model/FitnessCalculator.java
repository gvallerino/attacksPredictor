package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public abstract class FitnessCalculator {

	public static List<Double> calculate(List<Integer> values) {
		List<Double> fitness = new ArrayList<Double>();
		Map<String, String> configurations = FitnessConfiguration.getConfigurations();
		
		double fitnessYear = calculateYearFitness(values.get(0)) * Double.valueOf(configurations.get("year"));
		double fitnessRegion = 1 *  Double.valueOf(configurations.get("region"));
		double fitnessMultiple = values.get(2) * Double.valueOf(configurations.get("multiple"));
		double fitnessSuccess = values.get(3) * Double.valueOf(configurations.get("success"));
		double fitnessSuicide = values.get(4) * Double.valueOf(configurations.get("suicide"));
		double fitnessAttack = 1 * Double.valueOf(configurations.get("attack"));
		double fitnessTarget = 1 * Double.valueOf(configurations.get("target"));
		double fitnessWeapon = 1 * Double.valueOf(configurations.get("weapon"));
		double fitnessKill = values.get(8) * Double.valueOf(configurations.get("kills"));
		double fitnessWound = values.get(9) * Double.valueOf(configurations.get("wound"));
		
		int potencia = Integer.valueOf(configurations.get("potencia"));

		//double fitnessKill = calculateMounts(values.get(8), fitnessMultiple, fitnessSuccess, fitnessSuicide);
		//double fitnessWound = calculateMounts(values.get(9), fitnessMultiple, fitnessSuccess, fitnessSuicide);
		
		fitness.add(Math.pow(fitnessYear,potencia));
		fitness.add(Math.pow(fitnessRegion,potencia));
		fitness.add(Math.pow(fitnessMultiple,potencia));
		fitness.add(Math.pow(fitnessSuccess,potencia));
		fitness.add(Math.pow(fitnessSuicide,potencia));
		fitness.add(Math.pow(fitnessAttack,potencia));
		fitness.add(Math.pow(fitnessTarget,potencia));
		fitness.add(Math.pow(fitnessWeapon,potencia));
		fitness.add(Math.pow(fitnessKill,potencia));
		fitness.add(Math.pow(fitnessWound,potencia));
		
		return fitness;
	}
	
	private static int calculateYearFitness(int year) {
		return (year - Constants.YEAR_MIN);
	}
	
//	private static int calculateMounts(int mount, int fitnessMultiple, int fitnessSuccess, int fitnessSuicide) {
//		return (mount * fitnessMultiple) + (mount * fitnessSuccess) + (mount * fitnessSuicide);
//	}
//	
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
