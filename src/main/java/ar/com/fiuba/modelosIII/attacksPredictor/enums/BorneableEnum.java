package ar.com.fiuba.modelosIII.attacksPredictor.enums;

import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Borneable;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Reproduction;

public enum BorneableEnum {
	
	REPRODUCTION (1, new Reproduction());
	
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
