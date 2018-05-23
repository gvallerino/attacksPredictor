package ar.com.fiuba.modelosIII.attacksPredictor.others;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;

public class Constants {
	
	public static final int COUNT_CLUSTERS = 6;
	
	public static final double POPULATION_RANDOM_MAX = 1.0D;
	public static final double POPULATION_RANDOM_MIN = 0.9D;
	public static final double PORCENTAJE_MUTATION = 3;

	public static final int COUNT_DATA_TYPE = 10;
	public static final int YEAR_MAX = 2016;
	public static final int YEAR_MIN = 1970;
	public static int AMOUNT_KILLS_MAX = 1383; //1383
	public static int AMOUNT_WOUND_MAX = 7366; //7366
	
	public static final int COUNT_BINARY_YEARS = 10;
	public static final int COUNT_BINARY_REGION = RegionEnum.size() - 1;
	public static final int COUNT_BINARY_MULTIPLE = 1;
	public static final int COUNT_BINARY_SUCCESS = 1;
	public static final int COUNT_BINARY_SUICIDE = 1;
	public static final int COUNT_BINARY_ATTACK = AttackTypeEnum.size() - 1;
	public static final int COUNT_BINARY_TARGET = TargetTypeEnum.size() - 1;
	public static final int COUNT_BINARY_WEAPON = WeaponTypeEnum.size() - 1;
	public static final int COUNT_BINARY_KILLS = 10;
	public static final int COUNT_BINARY_WOUNDS = 10;
	
	public static final int[] COUNT_POSITION_BINARY = {COUNT_BINARY_YEARS, COUNT_BINARY_REGION, COUNT_BINARY_MULTIPLE, COUNT_BINARY_SUCCESS, COUNT_BINARY_SUICIDE, COUNT_BINARY_ATTACK, COUNT_BINARY_TARGET, COUNT_BINARY_WEAPON, COUNT_BINARY_KILLS, COUNT_BINARY_WOUNDS};
	
	public static int[] FIRST_POSITION_BINARY () {
		int[] firstPositionsBinary = new int [COUNT_DATA_TYPE]; 
		int acum = 0;
		for (int i = 0; i < COUNT_DATA_TYPE; i++) {
			firstPositionsBinary[i] = acum;
			acum += COUNT_POSITION_BINARY[i];
		}
		return firstPositionsBinary;
	}
	
	public static int COUNT_DATA_TYPE_BINARY () {
		int acum = 0;
		for (int i = 0; i < COUNT_DATA_TYPE; i++) {
			acum += COUNT_POSITION_BINARY[i];
		}
		return acum;
	}
	
	public static int COUNT_DIVIDE_YEARS() {
		int diff = YEAR_MAX - YEAR_MIN;
		return (diff / COUNT_BINARY_YEARS) + 1;
	}
	
	public static int getRandom(int inf, int sup) {
		double random = Math.random();
		int diff = sup - inf;
		int diffRandom = (int) (random * diff);
		int valueReturned = inf + diffRandom;
		return valueReturned;
	}
}
