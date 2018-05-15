package ar.com.fiuba.modelosIII.attacksPredictor.files;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;
import junit.framework.Assert;

public class DataSetTest {
	
	private TerroristAttacksDataSet data = TerroristAttacksDataSet.getInstance();
	private TerroristAttack filter;
	private TerroristAttack filter1;
	private TerroristAttack filter2;
	private TerroristAttack filter3;
	private List<TerroristAttack> filters;
	
	@Before
	public void setup() {
		filter = new TerroristAttack();
		filter1 = new TerroristAttack();
		filter2 = new TerroristAttack();
		filter3 = new TerroristAttack();
		filters = new ArrayList<TerroristAttack>();
	}
	
	@Test
	public void testReaderShould112338Items() {
		Assert.assertTrue(data.getAll().size() == 112338);
	}

	@Test
	public void testFilterByYear1970() {
		filter.setYear(1970);
		List<TerroristAttack> attacksFiltered = data.filter(filter);
		Assert.assertEquals(attacksFiltered.size(), 526);
	}
	
	@Test
	public void testFilterBySuccessAndRegion8() {
		filter.setSuccess(Boolean.TRUE);
		filter.setRegion(RegionEnum.WESTERN_EUROPE);
		List<TerroristAttack> attacksFiltered = data.filter(filter);
		Assert.assertEquals(attacksFiltered.size(), 9732);
	}
	
	@Test
	public void testFilterByKillAndWound() {
		filter.setAmountKill(4);
		filter.setAmountWound(4);
		List<TerroristAttack> attacksFiltered = data.filter(filter);
		Assert.assertEquals(attacksFiltered.size(), 115);
	}
	
	@Test
	public void testFilterByTypes() {
		filter.setAttackType(AttackTypeEnum.ASESINATO);
		filter.setTargetType(TargetTypeEnum.GOBIERNO_GENERAL);
		filter.setWeaponType(WeaponTypeEnum.ARMAS_FUEGO);
		List<TerroristAttack> attacksFiltered = data.filter(filter);
		Assert.assertEquals(attacksFiltered.size(), 3259);
	}
	
	@Test
	public void testFilterByVeryYears() {
		filter.setYear(1982);
		filter1.setYear(1988);
		filter2.setYear(1996);
		filter3.setYear(2016);
		filters.add(filter);
		filters.add(filter1);
		filters.add(filter2);
		filters.add(filter3);
		List<TerroristAttack> attacksFiltered = data.filter(filters);
		Assert.assertEquals(attacksFiltered.size(), 13181);
	}
	
	@Test
	public void testNumberMaxKillsAmount() {
		Assert.assertEquals(Constants.AMOUNT_KILLS_MAX, 1383);
	}
	
	@Test
	public void testNumberMaxWoundAmount() {
		Assert.assertEquals(Constants.AMOUNT_WOUND_MAX, 7366);
	}
}
