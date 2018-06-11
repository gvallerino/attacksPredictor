package ar.com.fiuba.modelosIII.attacksPredictor.configurations;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.CruzaEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution.MutationEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.Mutation;

public class TypeEvolution {

	public static Cruzable getCruza() {
		int id = getIdByProperty("cruza");
		CruzaEnum cruzaEnum = CruzaEnum.getById(id);
		System.out.println(cruzaEnum.getDescription() + " cargada con éxito.");
		return cruzaEnum.getCruzable();
	}
	
	public static Mutation getMutation() {
		int id = getIdByProperty("mutacion");
		MutationEnum mutationEnum = MutationEnum.getById(id);
		System.out.println(mutationEnum.getDescription() + " cargada con éxito.");
		return mutationEnum.getMutation();
	}
	
	private static int getIdByProperty(String property) {
		List<Integer> cruzas = ConfigurationsDataSet.getPositionsByKey(property);
		int id = 0;
		if (!cruzas.isEmpty()) {
			id = cruzas.get(0);
		}
		return id;
	}
}
