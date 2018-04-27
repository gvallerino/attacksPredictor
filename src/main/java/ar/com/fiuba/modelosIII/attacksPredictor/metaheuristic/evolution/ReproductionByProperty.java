package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class ReproductionByProperty extends Reproduction implements Borneable {

	@Override
	public TerroristAttack beBorn(TerroristAttack father, TerroristAttack mother) {
		
		List<Integer> gen = new ArrayList<Integer>();
		
		for (int i = 0; i < Constants.COUNT_DATA_TYPE; i++) {
			father.getValues().get(i);
		}
		return this.born(father, mother, gen);
	}

}
