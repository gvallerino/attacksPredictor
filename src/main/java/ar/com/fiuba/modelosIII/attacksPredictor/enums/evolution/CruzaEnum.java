package ar.com.fiuba.modelosIII.attacksPredictor.enums.evolution;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Cruzable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaByImportancia;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBySegmento;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBinaria;

public enum CruzaEnum {
	
	CRUZA_BINARIA (1, CruzaBinaria.getInstance()),
	CRUZA_IMPORTANCIA(2, CruzaByImportancia.getInstance()),
	CRUZA_SEGMENTO (3, CruzaBySegmento.getInstance());
	
	private int id;
	private Cruzable cruza;
	
	CruzaEnum(int id, Cruzable cruza) {
		this.id = id;
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
	
}
