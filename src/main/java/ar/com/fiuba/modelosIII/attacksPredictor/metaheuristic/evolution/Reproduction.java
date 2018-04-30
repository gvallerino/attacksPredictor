package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Borneable;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public abstract class Reproduction implements Borneable {
	
	protected static Reproduction INSTANCE = null;
	
	@Override
	public TerroristAttack beBorn(TerroristAttack father, TerroristAttack mother) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected TerroristAttack born(TerroristAttack father, TerroristAttack mother, List<Integer> gen) {
		String id = father.getId() + "-" + mother.getId();
		TerroristAttack son = new TerroristAttack(id, gen);
		return son;
		
	}

}
