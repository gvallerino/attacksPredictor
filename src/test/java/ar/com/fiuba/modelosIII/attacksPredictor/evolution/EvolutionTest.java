package ar.com.fiuba.modelosIII.attacksPredictor.evolution;

import java.util.BitSet;
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
	private Borneable reproduction = null;
	private Population population = new Population();
	
//	father.print();
//	mother.print();
//	son.print();
	
	@Before
	public void setup() {
		filter = new TerroristAttack();
		filter.setSuccess(Boolean.TRUE);
		filter.setRegion(RegionEnum.WESTERN_EUROPE);
	}
	
	@Test
	public void sonTestBySegmento() {
		reproduction = BorneableEnum.CRUZA_SEGMENTO.getBorn();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.beBorn(father, mother);
		
		//TODO: Falta assert de la cruza
		Assert.assertEquals(son.getId(), this.getIdCruza(father.getId(), mother.getId()));
	}
	
	@Test
	public void sonTestByImportancia() {
		reproduction = BorneableEnum.CRUZA_IMPORTANCIA.getBorn();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.beBorn(father, mother);
		
		//TODO: Falta assert de la cruza
		Assert.assertEquals(son.getId(), this.getIdCruza(father.getId(), mother.getId()));
	}
	
	@Test
	public void sonTestByBinario() {
		reproduction = BorneableEnum.CRUZA_BINARIA.getBorn();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.beBorn(father, mother);
		father.getBits().xor(mother.getBits());

		Assert.assertArrayEquals(father.getBits().toByteArray(), son.getBits().toByteArray());
		Assert.assertEquals(son.getId(), this.getIdCruza(father.getId(), mother.getId()));
	}
	
	@Test
	public void testBinaryShouldFatherBeInmutable() {
		reproduction = BorneableEnum.CRUZA_BINARIA.getBorn();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		BitSet bitsCopyFather = (BitSet) father.getBits().clone();
		reproduction.beBorn(father, mother);
		Assert.assertArrayEquals(father.getBits().toByteArray(), bitsCopyFather.toByteArray());
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
	
	private String getIdCruza(String id1, String id2) {
		return id1 + "-" + id2;
	}

}