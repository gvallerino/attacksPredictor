package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Reproduction;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class CruzaBySegmento extends Reproduction implements Cruzable {
	
	private static CruzaBySegmento INSTANCE = null;
	
	private CruzaBySegmento() {}
	
	public static CruzaBySegmento getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CruzaBySegmento();
		}
		return INSTANCE;
	}

	public TerroristAttack cruzar (TerroristAttack father, TerroristAttack mother) {
		
		List<Integer> genSon = new ArrayList<Integer>();
		/*
		for (int i = 0; i < Constants.COUNT_DATA_TYPE; i++) {
			Integer genFather = father.getValues().get(i);
			Integer genMother = mother.getValues().get(i);
			boolean applyGenFather = Constants.getRandom(0, 1) == 1;
			Integer gen = applyGenFather ? genFather : genMother;
			genSon.add(gen);
		}*/
		for (int i = 0; i < 5; i++) {
			Integer genFather = father.getValues().get(i);
			genSon.add(genFather);
		}
		
		for (int i = 5; i < 10; i++) {
			Integer genMother = mother.getValues().get(i);
			genSon.add(genMother);
		}
		
		return this.cruzar(father, mother, genSon);
	}
}

//		int genMax = 0;
//		double genSup = 0;
//		if (father.getFitness() > mother.getFitness()) {
//			genSup = father.getFitness();
//		} else {
//			genSup = mother.getFitness();
//		}
//		
//		genMax = (int) ((genSup / (father.getFitness() + mother.getFitness())) * Constants.COUNT_DATA_TYPE);
//		List<Integer> genFather = father.getValues().subList(0, genMax);
//		List<Integer> genMother = mother.getValues().subList(genMax, Constants.COUNT_DATA_TYPE);
//		List<Integer> genSon = new ArrayList<Integer>();
//		genSon.addAll(genFather);
//		genSon.addAll(genMother);