package ar.com.fiuba.modelosIII.attacksPredictor.others;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;

public class Constants {
	
	public static final int COUNT_CLUSTERS = 6;
	public static final int COUNT_GENERATIONS = 10;
	public static final int COUNT_DIGITS_PRINT_CLUSTERS = 6;
	
	public static final double POPULATION_RANDOM_MAX = 0.2D;
	public static final double POPULATION_RANDOM_MIN = 0.1D;
	public static final double POPULATION_EXACT = 34840D;
	public static final double PORCENTAJE_MUTATION = 1;

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
	
	public static boolean getRandom(double probability){
		if (probability == 0) {
			return false;
		}
		double random = 0;
		while (random == 0) {
			random = Constants.getRandom(0, 100);
		}
		return probability > random;
	}
	
	public static int choice(int value1, int value2, double probability) {
		boolean isValue1 = Constants.getRandom(probability);
		return isValue1 ? value1 : value2;
	}
	
	public static List<Double> toDouble(List<Integer> values) {
		List<Double> doubles = new ArrayList<Double>();
		if (values != null && !values.isEmpty()) {
			if (values.size() == Constants.COUNT_DATA_TYPE) {
				for (Integer value : values) {
					doubles.add(new Double(value));
				}
			} else {
				System.out.println("ERROR | Ha ocurrido un error de tamaño");
			}
		} else {
			System.out.println("ERROR | No hay datos en los valores");
		}
		return doubles;
	}
	
}
