package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public interface Cruzable {
	
	TerroristAttack cruzar (TerroristAttack father, TerroristAttack mother);

}
