package ar.com.fiuba.modelosIII.attacksPredictor.code;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import junit.framework.Assert;

public class CodeToBinaryTest {
	
	@Test
	public void testTerroristAttackBinaryYearWithRest() {
		Assert.assertTrue(this.getAssertByYearAndRest(1970, 0));
		Assert.assertTrue(this.getAssertByYearAndRest(1975, 1));
		Assert.assertTrue(this.getAssertByYearAndRest(1980, 2));
		Assert.assertTrue(this.getAssertByYearAndRest(1985, 3));
		Assert.assertTrue(this.getAssertByYearAndRest(1990, 4));
		Assert.assertTrue(this.getAssertByYearAndRest(1995, 5));
		Assert.assertTrue(this.getAssertByYearAndRest(2000, 6));
		Assert.assertTrue(this.getAssertByYearAndRest(2005, 7));
		Assert.assertTrue(this.getAssertByYearAndRest(2010, 8));
		Assert.assertTrue(this.getAssertByYearAndRest(2015, 9));
	}
	
	@Test
	public void testTerroristAttackBinaryRegionEnum() {
		for (int i = 1; i < RegionEnum.size(); i++) {
			Assert.assertTrue(this.getAssertRegionEnumByType(i));
		}
	}
	
	@Test
	public void testTerroristAttackBinaryAttackEnum() {
		for (int i = 1; i < AttackTypeEnum.size(); i++) {
			Assert.assertTrue(this.getAssertAttackEnumByType(i));
		}
	}
	
	@Test
	public void testTerroristAttacktBinaryTargetEnum() {
		for (int i = 1; i < TargetTypeEnum.size(); i++) {
			Assert.assertTrue(this.getAssertTargetEnumByType(i));
		}
	}
	
	@Test
	public void testTerroristAttackBinaryWeaponEnum() {
		for (int i = 1; i < WeaponTypeEnum.size(); i++) {
			Assert.assertTrue(this.getAssertWeaponEnumByType(i));
		}
	}
	
	@Test
	public void testTerroristAttackBinaryMultiple() {
		boolean assertBit = true;
		int positionBitTrue = Constants.FIRST_POSITION_BINARY()[2] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setMultiple(true);
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		Assert.assertTrue(assertBit);
	}
	
	@Test
	public void testTerroristAttackBinarySuccess() {
		boolean assertBit = true;
		int positionBitTrue = Constants.FIRST_POSITION_BINARY()[3] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setSuccess(true);
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		Assert.assertTrue(assertBit);
	}
	
	@Test
	public void testTerroristAttackBinarySuicide() {
		boolean assertBit = true;
		int positionBitTrue = Constants.FIRST_POSITION_BINARY()[4] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setSuicide(true);
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		Assert.assertTrue(assertBit);
	}
	
	@Test
	public void testTerroristAttackBinaryAmountKills() {
		boolean assertBit = true;
		int firstPosition = Constants.FIRST_POSITION_BINARY()[8] - 1;
		int maxKill = 10000;
		int divisor = 1000; //1000 / 10
		List<TerroristAttack> attacks = new ArrayList<TerroristAttack>();
		
		for (int i = 0; i < maxKill; i++) {
			TerroristAttack attack = new TerroristAttack();
			attack.setAmountKill(i);
			attacks.add(attack);
		}
		
		for (TerroristAttack attack : attacks) {
			BitSet bits = attack.getBits();
			for (int i = 0; i < bits.size(); i++) {
				int positionBitTrue = firstPosition + (attack.getAmountKill() / divisor);
				if (i == positionBitTrue) {
					assertBit  = assertBit && (bits.get(i) == true);
				} else {
					assertBit  = assertBit && (bits.get(i) == false);
				}
			}
		}
		Assert.assertTrue(assertBit);
	}
	
	@Test
	public void testTerroristAttackBinaryAmountWound() {
		boolean assertBit = true;
		int firstPosition = Constants.FIRST_POSITION_BINARY()[9] - 1;
		int maxKill = 10000;
		int divisor = 1000; //1000 / 10
		List<TerroristAttack> attacks = new ArrayList<TerroristAttack>();
		
		for (int i = 0; i < maxKill; i++) {
			TerroristAttack attack = new TerroristAttack();
			attack.setAmountWound(i);
			attacks.add(attack);
		}
		
		for (TerroristAttack attack : attacks) {
			BitSet bits = attack.getBits();
			for (int i = 0; i < bits.size(); i++) {
				int positionBitTrue = firstPosition + (attack.getAmountWound() / divisor);
				if (i == positionBitTrue) {
					assertBit  = assertBit && (bits.get(i) == true);
				} else {
					assertBit  = assertBit && (bits.get(i) == false);
				}
			}
		}
		Assert.assertTrue(assertBit);
	}
	
	private List<TerroristAttack>generateTerroristAttacksWithYear(int year) {
		int yearMultiple = year;
		List<TerroristAttack> attacks = new ArrayList<TerroristAttack>();
		for (int i = 0; i < Constants.COUNT_DIVIDE_YEARS(); i++) {
			TerroristAttack attack = new TerroristAttack();
			attack.setYear(yearMultiple);
			attacks.add(attack);
			yearMultiple ++;
		}
		return attacks;
	}
	
	private boolean getAssertByYearAndRest(int year, int rest) {
		boolean assertBit = true;
		for (TerroristAttack attack : this.generateTerroristAttacksWithYear(year)) {
			BitSet bits = attack.getBits();
			for (int i = 0; i < bits.size(); i++) {
				if (i == rest) {
					assertBit  = assertBit && (bits.get(i) == true);
				} else {
					assertBit  = assertBit && (bits.get(i) == false);
				}
			}
		}
		return assertBit;
	}
	
	private boolean getAssertRegionEnumByType(int type) {
		boolean assertBit = true;
		int positionBitTrue = type + Constants.FIRST_POSITION_BINARY()[1] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setRegion(RegionEnum.getById(type));
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		return assertBit;
	}
	
	private boolean getAssertAttackEnumByType(int type) {
		boolean assertBit = true;
		int positionBitTrue = type + Constants.FIRST_POSITION_BINARY()[5] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setAttackType(AttackTypeEnum.getById(type));
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		return assertBit;
	}
	
	private boolean getAssertTargetEnumByType(int type) {
		boolean assertBit = true;
		int positionBitTrue = type + Constants.FIRST_POSITION_BINARY()[6] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setTargetType(TargetTypeEnum.getById(type));
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		return assertBit;
	}
	
	private boolean getAssertWeaponEnumByType(int type) {
		boolean assertBit = true;
		int positionBitTrue = type + Constants.FIRST_POSITION_BINARY()[7] - 1;
		TerroristAttack attack = new TerroristAttack();
		attack.setWeaponType(WeaponTypeEnum.getById(type));
		BitSet bits = attack.getBits();
		for (int i = 0; i < bits.size(); i++) {
			if (i == positionBitTrue) {
				assertBit  = assertBit && (bits.get(i) == true);
			} else {
				assertBit  = assertBit && (bits.get(i) == false);
			}
		}
		return assertBit;
	}
	
}
