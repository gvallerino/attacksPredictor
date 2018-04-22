package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public interface Borneable {
	
	TerroristAttack beBorn (TerroristAttack father, TerroristAttack mother);

}
