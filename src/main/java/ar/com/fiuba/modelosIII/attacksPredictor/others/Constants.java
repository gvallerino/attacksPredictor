package ar.com.fiuba.modelosIII.attacksPredictor.others;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;

public class Constants {

	public static final int COUNT_DATA_TYPE = 10;
	public static final double PROBABILITY_MAX = 0.2D;
	public static final double PROBABILITY_MIN = 0.1D;
	public static final int YEAR_MAX = 2016;
	public static final int YEAR_MIN = 1970;
	public static int AMOUNT_KILLS_MAX = 0;
	public static int AMOUNT_WOUND_MAX = 0;
	
	public static final int COUNT_DIVIDE_YEARS = 9;
	public static final int COUNT_DIVIDE_REGION = RegionEnum.size() - 1;
	public static final int COUNT_DIVIDE_MULTIPLE = 1;
	public static final int COUNT_DIVIDE_SUCCESS = 1;
	public static final int COUNT_DIVIDE_SUICIDE = 1;
	public static final int COUNT_DIVIDE_ATTACK = AttackTypeEnum.size() - 1;
	public static final int COUNT_DIVIDE_TARGET = TargetTypeEnum.size() - 1;
	public static final int COUNT_DIVIDE_WEAPON = WeaponTypeEnum.size() - 1;
	public static final int COUNT_DIVIDE_KILLS = 10;
	public static final int COUNT_DIVIDE_WOUNDS = 10;
	
	public static final int[] COUNT_POSITION_BINARY = {COUNT_DIVIDE_YEARS, COUNT_DIVIDE_REGION, COUNT_DIVIDE_MULTIPLE, COUNT_DIVIDE_SUCCESS, COUNT_DIVIDE_SUICIDE, COUNT_DIVIDE_ATTACK, COUNT_DIVIDE_TARGET, COUNT_DIVIDE_WEAPON, COUNT_DIVIDE_KILLS, COUNT_DIVIDE_WOUNDS};
	
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
	
	public static int getRandom(int inf, int sup) {
		double random = Math.random();
		int diff = sup - inf;
		int diffRandom = (int) (random * diff);
		int valueReturned = inf + diffRandom;
		return valueReturned;
	}
}
