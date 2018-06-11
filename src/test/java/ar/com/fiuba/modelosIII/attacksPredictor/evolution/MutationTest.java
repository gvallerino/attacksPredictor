package ar.com.fiuba.modelosIII.attacksPredictor.evolution;

import java.util.BitSet;
import java.util.List;

import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.MutationEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.Mutation;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import junit.framework.Assert;

public class MutationTest {
	
	private TerroristAttacksDataSet data = TerroristAttacksDataSet.getInstance();
	private Mutation mutationValue = MutationEnum.MUTACION_POR_VALOR.getMutation();
	private Mutation mutationBinary = MutationEnum.MUTACION_BINARIA.getMutation();

	@Test
	public void testCountMutateValueShouldBeOne() {
		TerroristAttack attack = data.getById("1");
		List<Integer> beforeValues = attack.getValues();
		TerroristAttack attackMutated = mutationValue.mutate(attack);
		List<Integer> afterValues = attackMutated.getValues();
		
		int countBitsMutate = 0;
		
		Assert.assertEquals(beforeValues.size(), afterValues.size());
		
		for (int i = 0; i < afterValues.size(); i++) {
			if (beforeValues.get(i) != null && !beforeValues.get(i).equals(afterValues.get(i))) {
				countBitsMutate++;
			}
		}
		
		Assert.assertEquals(1, countBitsMutate);
	}
	
	@Test
	public void testCountMutateBinaryShouldBeOne() {
		TerroristAttack attack = data.getById("1");
		BitSet beforeBits = attack.getBits();
		TerroristAttack attackMutated = mutationBinary.mutate(attack);
		BitSet afterBits = attackMutated.getBits();
		
		int countBitsMutate = 0;
		
		Assert.assertEquals(beforeBits.size(), afterBits.size());
		
		for (int i = 0; i < afterBits.size(); i++) {
			if (beforeBits.get(i) != afterBits.get(i)) {
				countBitsMutate++;
			}
		}
		
		Assert.assertEquals(1, countBitsMutate);
	}
}
