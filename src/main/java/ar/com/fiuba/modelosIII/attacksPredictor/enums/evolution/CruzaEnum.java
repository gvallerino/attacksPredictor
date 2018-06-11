package ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaByImportancia;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBySegmento;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBinaria;

public enum CruzaEnum {
	
	CRUZA_BINARIA (0, "Cruza Binaria", CruzaBinaria.getInstance()),
	CRUZA_IMPORTANCIA (1, "Cruza por Importancia", CruzaByImportancia.getInstance()),
	CRUZA_SEGMENTO (2, "Cruza por Segmento", CruzaBySegmento.getInstance());
	
	private int id;
	private String description;

	private Cruzable cruza;
	
	CruzaEnum(int id, String description, Cruzable cruza) {
		this.id = id;
		this.description = description;
		this.cruza = cruza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cruzable getCruzable() {
		return cruza;
	}

	public void setCruzable(Cruzable cruza) {
		this.cruza = cruza;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static CruzaEnum getById(int id) {
		return CruzaEnum.values()[id];
	}
	
}
