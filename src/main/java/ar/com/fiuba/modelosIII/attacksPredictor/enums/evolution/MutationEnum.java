package ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation.*;

public enum MutationEnum {
	
	MUTACION_BINARIA (0, "Mutacion Binaria", MutationBinary.getInstance()),
	MUTACION_POR_VALOR(1, "Mutacion por Valor", MutationValue.getInstance());
	
	private int id;
	private String description;
	private Mutation mutation;
	
	MutationEnum(int id, String description, Mutation mutation) {
		this.id = id;
		this.description = description;
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
	
	public static MutationEnum getById(int id) {
		return MutationEnum.values()[id];
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
