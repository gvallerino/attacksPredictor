package ar.com.fiuba.modelosIII.attacksPredictor.enums;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.Borneable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaByImportancia;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza.CruzaBySegmento;

public enum BorneableEnum {
	
	CRUZA_IMPORTANCIA(1, CruzaByImportancia.getInstance()),
	CRUZA_SEGMENTO (2, CruzaBySegmento.getInstance());
	
	private int id;
	private Borneable born;
	
	BorneableEnum(int id, Borneable born) {
		this.id = id;
		this.born = born;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Borneable getBorn() {
		return born;
	}

	public void setBorn(Borneable born) {
		this.born = born;
	}
	
}
