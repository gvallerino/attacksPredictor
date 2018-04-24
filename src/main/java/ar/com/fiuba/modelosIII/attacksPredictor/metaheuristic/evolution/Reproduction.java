package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class Reproduction implements Borneable {

	public TerroristAttack beBorn (TerroristAttack father, TerroristAttack mother) {
		
		int genMax = (father.getFitness() > mother.getFitness()) ? father.getFitness() : mother.getFitness();
		List<Integer> genFather = father.getValues().subList(0, genMax);
		List<Integer> genMother = mother.getValues().subList(genMax, Constants.COUNT_DATA_TYPE);
		List<Integer> genSon = new ArrayList<Integer>();
		genSon.addAll(genFather);
		genSon.addAll(genMother);
		String id = father.getId() + "-" + mother.getId();
		TerroristAttack son = new TerroristAttack(id, genSon);
		return son;
	}
}
