package ar.com.fiuba.modelosIII.attacksPredictor.enums;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Borneable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.ReproductionBySegmento;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.ReproductionByProperty;;

public enum BorneableEnum {
	
	REPRODUCTION_SEGMENTO (1, new ReproductionBySegmento()),
	REPRODUCTION_PROPERTY(2, new ReproductionByProperty());
	
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
