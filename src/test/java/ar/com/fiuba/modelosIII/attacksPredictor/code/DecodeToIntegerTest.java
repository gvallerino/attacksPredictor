package ar.com.fiuba.modelosIII.attacksPredictor.code;

import java.util.BitSet;

import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import junit.framework.Assert;

public class DecodeToIntegerTest {

	@Test
	public void testCodeBinaryToYearShouldCorrectly() {
		
		int limitInf = Constants.YEAR_MIN;
		int limitSup = Constants.YEAR_MIN + Constants.COUNT_DIVIDE_YEARS();
		
		boolean between = true;
		for (int i = 1; i <= Constants.COUNT_BINARY_YEARS; i++) {
			int position = i-1;
			for (int j = 1; j <= 100; j++) {
				BitSet bits = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
				bits.flip(position);
				TerroristAttack attack = new TerroristAttack(String.valueOf(i*j), bits);
				int year = attack.getYear();
				between = limitInf <= year && year <= limitSup;
				if (!between) break;
			}
			if (!between) break;
			limitInf += Constants.COUNT_DIVIDE_YEARS();
			limitSup += Constants.COUNT_DIVIDE_YEARS();
		}
		
		Assert.assertTrue(between);
	}
	
	@Test
	public void testCodeBinaryMultipleShouldCorrectly() {
		Assert.assertTrue(generateTerroristAttack(2).isMultiple());
	}
	
	@Test
	public void testCodeBinarySuccessShouldCorrectly() {
		Assert.assertTrue(generateTerroristAttack(3).isSuccess());
	}
	
	@Test
	public void testCodeBinarySuicideShouldCorrectly() {
		Assert.assertTrue(generateTerroristAttack(4).isSuicide());
	}
	
	@Test
	public void testCodeBinaryKillsShouldCorrectly() {
		boolean inRange = true;
		int max = Constants.AMOUNT_KILLS_MAX != 0 ? Constants.AMOUNT_KILLS_MAX : 10000;
		int range = max / Constants.COUNT_BINARY_KILLS;
		for (int i = 0; i < Constants.COUNT_BINARY_KILLS; i++) {
			TerroristAttack attack = generateTerroristAttack(8, i);
			inRange = (range * i) <= attack.getAmountKill() && attack.getAmountKill() < (range * (i+1));
			if(!inRange) break;
		}
		Assert.assertTrue(inRange);
	}
	
	@Test
	public void testCodeBinaryWoundsShouldCorrectly() {
		boolean inRange = true;
		int max = Constants.AMOUNT_WOUND_MAX != 0 ? Constants.AMOUNT_WOUND_MAX : 10000;
		int range = max / Constants.COUNT_BINARY_WOUNDS;
		for (int i = 0; i < Constants.COUNT_BINARY_WOUNDS; i++) {
			TerroristAttack attack = generateTerroristAttack(9, i);
			inRange = (range * i) <= attack.getAmountWound() && attack.getAmountWound() < (range * (i+1));
			if(!inRange) break;
		}
		Assert.assertTrue(inRange);
	}
		
	
	private TerroristAttack generateTerroristAttack(int i) {
		int position = Constants.FIRST_POSITION_BINARY()[i];
		BitSet bits = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
		bits.flip(position);
		TerroristAttack attack = new TerroristAttack(String.valueOf(position), bits);
		return attack;
	}
	
	private TerroristAttack generateTerroristAttack(int i, int length) {
		int position = Constants.FIRST_POSITION_BINARY()[i];
		BitSet bits = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
		bits.flip(position + length);
		TerroristAttack attack = new TerroristAttack(String.valueOf(position), bits);
		return attack;
	}
	
	@Test
	public void testCodeBinaryToRegionShouldCorrectly() {
		
		boolean isRegion = true;
		for (int i = 1; i < RegionEnum.size(); i++) {
			int position = Constants.FIRST_POSITION_BINARY()[1] + i - 1;
			BitSet bits = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
			bits.flip(position);
			TerroristAttack attack = new TerroristAttack(String.valueOf(i), bits);
			RegionEnum region = attack.getRegion();
			isRegion = RegionEnum.getById(i).equals(region);
			if (!isRegion) break;
		}
		Assert.assertTrue(isRegion);
	}
	
	@Test
	public void testCodeBinaryToAttackShouldCorrectly() {
		
		boolean isAttack = true;
		for (int i = 1; i < AttackTypeEnum.size(); i++) {
			int position = Constants.FIRST_POSITION_BINARY()[5] + i - 1;
			BitSet bits = new BitSet(Constants.COUNT_DATA_TYPE_BINARY());
			bits.flip(position);
			TerroristAttack attack = new TerroristAttack(String.valueOf(i), bits);
			AttackTypeEnum region = attack.getAttackType();
			isAttack = AttackTypeEnum.getById(i).equals(region);
			if (!isAttack) break;
		}
		Assert.assertTrue(isAttack);
	}
}
