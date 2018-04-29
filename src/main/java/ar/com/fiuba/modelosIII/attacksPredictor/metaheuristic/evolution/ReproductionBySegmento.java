package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class ReproductionBySegmento extends Reproduction implements Borneable {

	public TerroristAttack beBorn (TerroristAttack father, TerroristAttack mother) {
		
		int genMax = 0;
		double genSup = 0;
		if (father.getFitness() > mother.getFitness()) {
			genSup = father.getFitness();
		} else {
			genSup = mother.getFitness();
		}
		
		genMax = (int) ((genSup / (father.getFitness() + mother.getFitness())) * Constants.COUNT_DATA_TYPE);
		List<Integer> genFather = father.getValues().subList(0, genMax);
		List<Integer> genMother = mother.getValues().subList(genMax, Constants.COUNT_DATA_TYPE);
		List<Integer> genSon = new ArrayList<Integer>();
		genSon.addAll(genFather);
		genSon.addAll(genMother);
		
		return this.born(father, mother, genSon);
	}
}
