package ar.com.fiuba.modelosIII.attacksPredictor.evolution;

import java.util.BitSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.CruzaEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.Population;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class CruzaTest {
	
	private TerroristAttacksDataSet data = TerroristAttacksDataSet.getInstance();
	private TerroristAttack filter;
	private Cruzable reproduction = null;
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
		reproduction = CruzaEnum.CRUZA_SEGMENTO.getCruzable();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.cruzar(father, mother);
		
		//TODO: Falta assert de la cruza
		Assert.assertEquals(son.getId(), this.getIdCruza(father.getId(), mother.getId()));
	}
	
	@Test
	public void sonTestByImportancia() {
		reproduction = CruzaEnum.CRUZA_IMPORTANCIA.getCruzable();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.cruzar(father, mother);
		
		//TODO: Falta assert de la cruza
		Assert.assertEquals(son.getId(), this.getIdCruza(father.getId(), mother.getId()));
	}
	
	@Test
	public void sonTestByBinario() {
		reproduction = CruzaEnum.CRUZA_BINARIA.getCruzable();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.cruzar(father, mother);
		father.getBits().xor(mother.getBits());

		Assert.assertArrayEquals(father.getBits().toByteArray(), son.getBits().toByteArray());
		Assert.assertEquals(son.getId(), this.getIdCruza(father.getId(), mother.getId()));
	}
	
	@Test
	public void testBinaryShouldFatherBeInmutable() {
		reproduction = CruzaEnum.CRUZA_BINARIA.getCruzable();
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		BitSet bitsCopyFather = (BitSet) father.getBits().clone();
		reproduction.cruzar(father, mother);
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