package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation;

import java.util.BitSet;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class MutationBinary extends Mutation {
	
	private static MutationBinary INSTANCE = null;
	
	private MutationBinary() {}
	
	public static Mutation getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MutationBinary();
		}
		return INSTANCE;
	}

	@Override
	public TerroristAttack mutate(TerroristAttack terroristAttack) {
		int positionToMutate = (int) (Math.random() * Constants.COUNT_DATA_TYPE_BINARY());
		BitSet bits = (BitSet) terroristAttack.getBits().clone(); //TODO: deberia hacer un clone del terrorist attack, porque pasa de generacion. no es el mismo attack
		bits.flip(positionToMutate);
		terroristAttack.setBits(bits);
		return terroristAttack;
	}

}
