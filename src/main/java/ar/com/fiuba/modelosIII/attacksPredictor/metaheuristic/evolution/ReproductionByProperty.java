package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class ReproductionByProperty extends Reproduction implements Borneable {

	@Override
	public TerroristAttack beBorn(TerroristAttack father, TerroristAttack mother) {
		
		List<Integer> gen = new ArrayList<Integer>();
		
		for (int i = 0; i < Constants.COUNT_DATA_TYPE; i++) {
			int fitnessFather = father.getFitnessByProperty(i);
			int fitnessMother = mother.getFitnessByProperty(i);
			Integer fitnessSon = 0;
			
			switch (i) {
				case 0: fitnessSon = this.getGenYear(fitnessFather, fitnessMother); break;
				case 1: fitnessSon = this.getGenRegion(father.getRegion(), mother.getRegion()); break;
				case 2: fitnessSon = this.getGenBoolean(fitnessFather, fitnessMother);break;
				case 3: fitnessSon = this.getGenBoolean(fitnessFather, fitnessMother);break;
				case 4: fitnessSon = this.getGenBoolean(fitnessFather, fitnessMother);break;
				case 5: fitnessSon = this.getGenAttack(father.getAttackType(), mother.getAttackType()); break;
				case 6: fitnessSon = this.getGenTarget(father.getTargetType(), mother.getTargetType()); break;
				case 7: fitnessSon = this.getGenWeapon(father.getWeaponType(), mother.getWeaponType()); break;
				case 8: fitnessSon = this.getGenByAmount(fitnessFather, fitnessMother); break;
				case 9: fitnessSon = this.getGenByAmount(fitnessFather, fitnessMother); break;
			}
			gen.add(fitnessSon);
		}
		return this.born(father, mother, gen);
	}
	
	private Integer getGenYear(int fitnessFather, int fitnessMother) {
		if (fitnessFather == fitnessMother) {
			return Constants.YEAR_MAX - fitnessFather;
		}
		return (fitnessFather > fitnessMother ? fitnessFather : fitnessMother);
	}

	private Integer getGenBoolean(int fitnessFather, int fitnessMother) {
		int fitnessSum = fitnessFather + fitnessMother;
		if (fitnessSum == 1) {
			return 1;
		}
		return 0;
	}
	
	private Integer getGenByAmount(int fatherAmount, int motherAmount) {
		return fatherAmount > motherAmount ? fatherAmount : motherAmount;
	}
	
	private Integer getGenAttack(AttackTypeEnum father, AttackTypeEnum mother) {
		AttackTypeEnum attack = (father.isMoreImportant(mother) ? father : mother);
		return attack.id;
	}
	
	private Integer getGenTarget(TargetTypeEnum father, TargetTypeEnum mother) {
		TargetTypeEnum target = (father.isMoreImportant(mother) ? father : mother);
		return target.id;
	}
	
	private Integer getGenWeapon(WeaponTypeEnum father, WeaponTypeEnum mother) {
		WeaponTypeEnum weapon = (father.isMoreImportant(mother) ? father : mother);
		return weapon.id;
	}
	
	private Integer getGenRegion(RegionEnum father, RegionEnum mother) {
		RegionEnum region = (father.isMoreImportant(mother) ? father : mother);
		return region.id;
	}
	
}
