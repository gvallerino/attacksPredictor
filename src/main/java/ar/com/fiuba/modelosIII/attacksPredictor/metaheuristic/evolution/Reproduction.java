package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.BitSet;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public abstract class Reproduction implements Cruzable {
	
	@Override
	public TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother, List<Integer> gen) {
		String id = father.getId() + "-" + mother.getId();
		TerroristAttack son = null;
		if (gen != null) {
			son = new TerroristAttack(id, gen);
		} else {
			son = new TerroristAttack();
			son.setId(id);
		}
		return son;
	}
	
	protected TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother, BitSet gen) {
		String id = father.getId() + "-" + mother.getId();
		TerroristAttack son = null;
		if (gen != null) {
			son = new TerroristAttack(id, gen);
		} else {
			son = new TerroristAttack();
			son.setId(id);
		}
		return son;
	}

}
