package ar.com.fiuba.modelosIII.attacksPredictor.others;

public class Constants {

	public static int COUNT_DATA_TYPE = 10;
	public static double PROBABILITY_MAX = 0.2D;
	public static double PROBABILITY_MIN = 0.1D;
	public static int YEAR_MAX = 2016;
	public static int YEAR_MIN = 1970;
	public static int COUNT_BINARY_DATA_TYPE = 86;
	
	public static int getRandom(int inf, int sup) {
		double random = Math.random();
		int diff = sup - inf;
		int diffRandom = (int) (random * diff);
		int valueReturned = inf + diffRandom;
		return valueReturned;
	}
}
