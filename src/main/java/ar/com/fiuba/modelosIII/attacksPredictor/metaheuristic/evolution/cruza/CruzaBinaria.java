package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza;

import java.util.BitSet;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Reproduction;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;

public class CruzaBinaria extends Reproduction implements Cruzable {
	
	private static CruzaBinaria INSTANCE = null;

	private CruzaBinaria() {}
	
	public static CruzaBinaria getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CruzaBinaria();
		}
		return INSTANCE;
	}
	
	@Override
	public TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother) {
		BitSet bitsFather = father.getBits();
		BitSet bitsMother = mother.getBits();
		BitSet bitsSon = (BitSet) bitsFather.clone();
		bitsSon.xor(bitsMother);
		TerroristAttack son = this.cruzar(father,mother,bitsSon);
		if (son.getYear().equals(0)) son.setYear(father.getYear());
		if (son.getRegion().equals(RegionEnum.OTHER)) son.setRegion(father.getRegion());
		if (son.getAttackType().equals(AttackTypeEnum.OTHER)) son.setAttackType(father.getAttackType());
		if (son.getTargetType().equals(TargetTypeEnum.NOTHING)) son.setTargetType(father.getTargetType());
		if (son.getWeaponType().equals(WeaponTypeEnum.NOTHING)) son.setWeaponType(father.getWeaponType());
		return son;
	}

}
