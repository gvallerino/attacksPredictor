package ar.com.fiuba.modelosIII.attacksPredictor.files;

import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.BorneableEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Borneable;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;

public class EvolutionTest {
	
	private TerroristAttacksDataSet data = TerroristAttacksDataSet.getInstance();
	private Borneable reproduction = BorneableEnum.REPRODUCTION.getBorn();
	
	@Test
	public void sonTest() {
		TerroristAttack father = data.getById("1");
		TerroristAttack mother = data.getById("2");
		TerroristAttack son = reproduction.beBorn(father, mother);
		
		father.print();
		mother.print();
		son.print();
	}

}
