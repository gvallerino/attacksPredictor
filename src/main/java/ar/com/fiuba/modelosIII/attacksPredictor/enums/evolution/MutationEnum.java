package ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.*;

public enum MutationEnum {
	
	MUTACION_BINARIA (1, MutationBinary.getInstance()),
	MUTACION_POR_VALOR(2, MutationValue.getInstance());
	
	private int id;
	private Mutation mutation;
	
	MutationEnum(int id, Mutation mutation) {
		this.id = id;
		this.mutation = mutation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mutation getMutation() {
		return mutation;
	}

	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}
	
}
