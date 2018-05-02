package ar.com.fiuba.modelosIII.attacksPredictor.evolution;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.BorneableEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.Population;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Borneable;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class EvolutionTest {
	
	private TerroristAttacksDataSet data = TerroristAttacksDataSet.getInstance();
	private TerroristAttack filter;
	private Borneable reproduction = BorneableEnum.CRUZA_SEGMENTO.getBorn();
	private Population population = new Population();
	
	@Before
	public void setup() {
		filter = new TerroristAttack();
		filter.setSuccess(Boolean.TRUE);
		filter.setRegion(RegionEnum.WESTERN_EUROPE);
	}
	
	@Test
	public void sonTest() {
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.beBorn(father, mother);
		
		father.print();
		mother.print();
		son.print();
	}
	
	@Test
	public void populationTest() {
		List<TerroristAttack> populate = population.populate(null);
		Assert.assertNotNull(populate);
	}
	
	@Test
	public void populationSize() {
		List<TerroristAttack> populate = population.populate(null);
		double max = data.getSize() * Constants.PROBABILITY_MAX;
		Assert.assertTrue(populate.size() < max);
	}
	
	@Test
	public void populationTestWithFilters() {
		List<TerroristAttack> populate = population.populate(filter);
		Assert.assertNotNull(populate);
	}
	
	@Test
	public void populationSizeWithFilters() {
		List<TerroristAttack> populate = population.populate(filter);
		double max = data.filter(filter).size() * Constants.PROBABILITY_MAX;
		Assert.assertTrue(populate.size() < max);
	}

}