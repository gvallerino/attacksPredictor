package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.cruza;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.Reproduction;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class CruzaByImportancia extends Reproduction implements Cruzable {
	
	private static CruzaByImportancia INSTANCE = null;
		
	private CruzaByImportancia() {}
	
	public static CruzaByImportancia getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CruzaByImportancia();
		}
		return INSTANCE;
	}

	//@Override
	public TerroristAttack cruzar2(TerroristAttack father, TerroristAttack mother) {
		List<Integer> gen = new ArrayList<Integer>();
		gen = (father.getFitness() > mother.getFitness()) ? father.getValues() : mother.getValues();
		return this.cruzar(father, mother, gen);
	}
	
	@Override
	public TerroristAttack cruzar(TerroristAttack father, TerroristAttack mother) {
		
		List<Integer> gen = new ArrayList<Integer>();
		
		for (int i = 0; i < Constants.COUNT_DATA_TYPE; i++) {
			int fitnessFather = father.getFitnessByProperty(i);
			int fitnessMother = mother.getFitnessByProperty(i);
			Integer genSon = 0;
			
			switch (i) {
				case 0: genSon = isMostImportant(father, mother) ? father.getYear() : mother.getYear(); break;
				case 1: genSon = this.getGenRegion(father.getRegion(), mother.getRegion()); break;
				case 2: genSon = this.getGenBoolean(fitnessFather, fitnessMother);break;
				case 3: genSon = this.getGenBoolean(fitnessFather, fitnessMother);break;
				case 4: genSon = this.getGenBoolean(fitnessFather, fitnessMother);break;
				case 5: genSon = this.getGenAttack(father.getAttackType(), mother.getAttackType()); break;
				case 6: genSon = this.getGenTarget(father.getTargetType(), mother.getTargetType()); break;
				case 7: genSon = this.getGenWeapon(father.getWeaponType(), mother.getWeaponType()); break;
				case 8: genSon = isMostImportant(father, mother) ? father.getAmountKill() : mother.getAmountKill(); break;
				case 9: genSon = isMostImportant(father, mother) ? father.getAmountWound() : mother.getAmountWound(); break;
			}
			gen.add(genSon);
		}
		return this.cruzar(father, mother, gen);
	}
	
	private boolean isMostImportant(TerroristAttack father, TerroristAttack mother) {
		return father.getFitness() > mother.getFitness();
	}
	
//	private Integer getGenYear(int fitnessFather, int fitnessMother) {
//		if (fitnessFather == fitnessMother) {
//			return Constants.YEAR_MAX - fitnessFather;
//		}
//		return (fitnessFather > fitnessMother ? fitnessFather : fitnessMother);
//	}

	private Integer getGenBoolean(int fitnessFather, int fitnessMother) {
//		int fitnessSum = fitnessFather + fitnessMother;
//		if (fitnessSum == 1) {
//			return 1;
//		}
//		return 0;
		if (fitnessFather + fitnessMother > 1) return 1;
		if (fitnessFather + fitnessMother > 0) return (Constants.getRandom(0, 100) > 50 ? 1 : 0);
		return 0;
	}
	
//	private Integer getGenByAmount(int fatherAmount, int motherAmount) {
//		return fatherAmount > motherAmount ? fatherAmount : motherAmount;
//	}
	
	private Integer getGenAttack(AttackTypeEnum father, AttackTypeEnum mother) {
		AttackTypeEnum attack = (father.isMoreImportant(mother) ? father : mother);
		return father.id;
	}
	
	private Integer getGenTarget(TargetTypeEnum father, TargetTypeEnum mother) {
		TargetTypeEnum target = (father.isMoreImportant(mother) ? father : mother);
		return father.id;
	}
	
	private Integer getGenWeapon(WeaponTypeEnum father, WeaponTypeEnum mother) {
		WeaponTypeEnum weapon = (father.isMoreImportant(mother) ? father : mother);
		return father.id;
	}
	
	private Integer getGenRegion(RegionEnum father, RegionEnum mother) {
		RegionEnum region = (father.isMoreImportant(mother) ? father : mother);
		return father.id;
	}
	
}
