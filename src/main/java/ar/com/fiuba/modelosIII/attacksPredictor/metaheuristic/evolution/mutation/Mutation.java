package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class Mutation {

	public TerroristAttack mutate(TerroristAttack terroristAttack) {
		int valueToMutate = (int) (Math.random() * Constants.COUNT_DATA_TYPE);
		List<Integer> values = terroristAttack.getValues();
		Integer oldValue = values.remove(valueToMutate);
		
		Integer newValue = 0; //TODO: Hacer la mutacion
		values.add(valueToMutate, newValue);
		String id = terroristAttack.getId();
		return new TerroristAttack(id, values);
	}

	
}
