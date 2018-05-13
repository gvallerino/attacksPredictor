package ar.com.fiuba.modelosIII.attacksPredictor.geneticsAlgorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import junit.framework.Assert;

public class TerroristAttackTest {
	
	@Test
	public void testTerroristAttackBinaryYearWithRest0() {
		Assert.assertTrue(this.getAssertByYearAndRest(1971, 0));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest1() {
		Assert.assertTrue(this.getAssertByYearAndRest(1972, 1));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest2() {
		Assert.assertTrue(this.getAssertByYearAndRest(1973, 2));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest3() {
		Assert.assertTrue(this.getAssertByYearAndRest(1974, 3));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest4() {
		Assert.assertTrue(this.getAssertByYearAndRest(1975, 4));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest5() {
		Assert.assertTrue(this.getAssertByYearAndRest(1976, 5));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest6() {
		Assert.assertTrue(this.getAssertByYearAndRest(1977, 6));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest7() {
		Assert.assertTrue(this.getAssertByYearAndRest(1978, 7));
	}
	
	@Test
	public void testTerroristAttackBinaryYearWithRest8() {
		Assert.assertTrue(this.getAssertByYearAndRest(1970, 8));
	}
	
	private List<TerroristAttack>generateTerroristAttacksWithYear(int year) {
		int yearMultiple = year;
		List<TerroristAttack> attacks = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			TerroristAttack attack = new TerroristAttack();
			attack.setYear(yearMultiple);
			attacks.add(attack);
			yearMultiple += Constants.COUNT_DIVIDE_YEARS;
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
}
