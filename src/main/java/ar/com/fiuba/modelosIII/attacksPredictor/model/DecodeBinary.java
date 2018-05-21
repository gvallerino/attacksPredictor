package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public abstract class DecodeBinary {

	public static List<Integer> decode(BitSet bits) {
		List<Integer> values = new ArrayList<Integer>();
		
		values.add(decodeYear(bits.get(0, Constants.COUNT_BINARY_YEARS)));
		values.add(decodeType(bits.get(Constants.FIRST_POSITION_BINARY()[1], Constants.FIRST_POSITION_BINARY()[1] + Constants.COUNT_BINARY_REGION)));
		values.add(decodeBoolean(bits.get(Constants.FIRST_POSITION_BINARY()[2], Constants.FIRST_POSITION_BINARY()[2] + Constants.COUNT_BINARY_MULTIPLE)));
		values.add(decodeBoolean(bits.get(Constants.FIRST_POSITION_BINARY()[3], Constants.FIRST_POSITION_BINARY()[3] + Constants.COUNT_BINARY_SUCCESS)));
		values.add(decodeBoolean(bits.get(Constants.FIRST_POSITION_BINARY()[4], Constants.FIRST_POSITION_BINARY()[4] +  Constants.COUNT_BINARY_SUICIDE)));
		values.add(decodeType(bits.get(Constants.FIRST_POSITION_BINARY()[5], Constants.FIRST_POSITION_BINARY()[5] + Constants.COUNT_BINARY_ATTACK)));
		values.add(decodeType(bits.get(Constants.FIRST_POSITION_BINARY()[6], Constants.FIRST_POSITION_BINARY()[6] + Constants.COUNT_BINARY_TARGET)));
		values.add(decodeType(bits.get(Constants.FIRST_POSITION_BINARY()[7], Constants.FIRST_POSITION_BINARY()[7] + Constants.COUNT_BINARY_WEAPON)));
		values.add(decodeKill(bits.get(Constants.FIRST_POSITION_BINARY()[8], Constants.FIRST_POSITION_BINARY()[8] + Constants.COUNT_BINARY_KILLS)));
		values.add(decodeWound(bits.get(Constants.FIRST_POSITION_BINARY()[9], Constants.FIRST_POSITION_BINARY()[9] + Constants.COUNT_BINARY_WOUNDS)));
		return values;
	}
	
	private static Integer decodeYear(BitSet bits) {
		int positionTrue = getPositionTrue(bits);
		if (positionTrue == -1) positionTrue++;
		int randomYear = Constants.getRandom(0,Constants.COUNT_DIVIDE_YEARS());
		Integer year = Constants.YEAR_MIN + randomYear + (positionTrue * Constants.COUNT_DIVIDE_YEARS());
		return year;
	}

	
	private static Integer decodeType(BitSet bits) {
		Integer enumId = getPositionTrue(bits) + 1;
		return enumId;
	}
	
	private static Integer decodeBoolean(BitSet bits) {
		boolean bit = bits.get(0);
		if (bit) return 1;
		return 0;
	}
	
	private static Integer decodeKill(BitSet bits) {
		int positionTrue = getPositionTrue(bits);
		if (positionTrue < 0) return 0;
		int amountMax = (Constants.AMOUNT_KILLS_MAX > 0) ? Constants.AMOUNT_KILLS_MAX : 10000;
		int range = amountMax / Constants.COUNT_POSITION_BINARY[8];
		int inf = positionTrue * range;
		int sup = (positionTrue + 1) * range;
		int random = Constants.getRandom(inf, sup);
		return random;
	}
	
	private static Integer decodeWound(BitSet bits) {
		int positionTrue = getPositionTrue(bits);
		if (positionTrue < 0) return 0;
		int amountMax = (Constants.AMOUNT_WOUND_MAX > 0) ? Constants.AMOUNT_WOUND_MAX : 10000;
		int range = amountMax / Constants.COUNT_POSITION_BINARY[9];
		int inf = positionTrue * range;
		int sup = (positionTrue + 1) * range;
		int random = Constants.getRandom(inf, sup);
		return random;
	}
	
	
	private static int getPositionTrue(BitSet bits) {
		int positionTrue = -1;
		for (int i = 0; i < bits.size(); i++) {
			if (bits.get(i)) {
				positionTrue = i;
				break;
			}
		}
		return positionTrue;
	}
}
