package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public abstract class Mutation {
	
	public abstract TerroristAttack mutate (TerroristAttack terroristAttack);

}
