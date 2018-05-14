package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza;

import java.util.BitSet;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Reproduction;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public class CruzaBinaria extends Reproduction implements Borneable {
	
	private static CruzaBinaria INSTANCE = null;

	private CruzaBinaria() {}
	
	public static CruzaBinaria getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CruzaBinaria();
		}
		return INSTANCE;
	}
	
	@Override
	public TerroristAttack beBorn(TerroristAttack father, TerroristAttack mother) {
		BitSet bitsFather = father.getBits();
		BitSet bitsMother = mother.getBits();
		BitSet bitsSon = (BitSet) bitsFather.clone();
		bitsSon.xor(bitsMother);
		TerroristAttack son = this.born(father,mother,bitsSon);
		return son;
	}

}
