package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.BitSet;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public abstract class CodeBinary {
	
	public static BitSet code(List<Integer> values) {
		BitSet bits = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
		codeYear(bits, values.get(0));
		codeByType(bits, values.get(1), 1);
		codeBoolean(bits, values.get(2), 2);
		codeBoolean(bits, values.get(3), 3);
		codeBoolean(bits, values.get(4), 4);
		codeByType(bits, values.get(5), 5);
		codeByType(bits, values.get(6), 6);
		codeByType(bits, values.get(7), 7);
		codeAmountKills(bits, values.get(8));
		codeAmountWound(bits, values.get(9));
		return bits;
	}
	
	public static void codeYear(BitSet bits, int year) {
		int numerador = year > Constants.YEAR_MIN ? year - Constants.YEAR_MIN : 0;
		int position = numerador / Constants.COUNT_DIVIDE_YEARS();
		bits.flip(position);
	}
	
	public static void codeAmountKills(BitSet bits,  int amount) {
		int numerador = (Constants.AMOUNT_KILLS_MAX > 0) ? Constants.AMOUNT_KILLS_MAX : 10000;
		int divisor = numerador / Constants.COUNT_BINARY_KILLS;
		int position = amount / divisor;
		flip(bits, position, 8);
	}
	
	public static void codeAmountWound(BitSet bits,  int amount) {
		int numerador = (Constants.AMOUNT_WOUND_MAX > 0) ? Constants.AMOUNT_WOUND_MAX : 10000;
		int divisor = numerador / Constants.COUNT_BINARY_WOUNDS;
		int position = amount / divisor;
		flip(bits, position, 9);
	}
	
	public static void codeByType(BitSet bits,  int value, int caso) {
		value--;
		flip(bits, value, caso);
	}
	
	public static void codeBoolean(BitSet bits,  int value, int caso){
		int position = Constants.FIRST_POSITION_BINARY()[caso];
		bits.set(position, value == 1);
	}
	
	private static void flip(BitSet bits, int value, int caso) {
		int initial = Constants.FIRST_POSITION_BINARY()[caso];
		int position = initial + value;
		bits.flip(position);
	}
}
