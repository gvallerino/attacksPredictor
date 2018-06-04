package ar.com.fiuba.modelosIII.attacksPredictor.metaheuristic.evolution.mutation;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.model.WeaponTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttack;
import ar.com.fiuba.modelosIII.attacksPredictor.others.Constants;

public class MutationValue extends Mutation {
	
	private static MutationValue INSTANCE = null;
	
	private MutationValue() {}
	
	public static Mutation getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MutationValue();
		}
		return INSTANCE;
	}

	@Override
	public TerroristAttack mutate(TerroristAttack terroristAttack) {
		int positionToMutate = Constants.getRandom(0, Constants.COUNT_DATA_TYPE);
		Integer oldValue = terroristAttack.getValues().get(positionToMutate);
		Integer newValue = 0;
		switch (positionToMutate) {
			case 0: newValue = this.mutateYear(oldValue); break;
			case 1: newValue = this.mutateEnum(oldValue, RegionEnum.size()); break;
			case 2: newValue = this.mutateBoolean(oldValue); break;
			case 3: newValue = this.mutateBoolean(oldValue); break;
			case 4: newValue = this.mutateBoolean(oldValue); break;
			case 5: newValue = this.mutateEnum(oldValue, AttackTypeEnum.size()); break;
			case 6: newValue = this.mutateEnum(oldValue, TargetTypeEnum.size()); break;
			case 7: newValue = this.mutateEnum(oldValue, WeaponTypeEnum.size()); break;
			case 8: newValue = this.mutateAmounts(oldValue); break;
			case 9: newValue = this.mutateAmounts(oldValue); break;
		}
		
		terroristAttack.getValues().remove(positionToMutate);
		terroristAttack.getValues().add(positionToMutate, newValue);
		return terroristAttack;
	}
	
	private Integer mutateYear(Integer oldValue) {
		int yearRandom = Constants.getRandom(Constants.YEAR_MIN, Constants.YEAR_MAX);
		while (yearRandom == oldValue.intValue()) {
			yearRandom = Constants.getRandom(Constants.YEAR_MIN, Constants.YEAR_MAX);
		}
		return yearRandom;
	}

	private Integer mutateBoolean(Integer oldValue) {
		if (oldValue.intValue() == 0) {
			return 1;
		}
		return 0;
	}
	
	private Integer mutateEnum(Integer oldValue, int ordinal) {
		if (ordinal == 1) {
			return oldValue;
		}
		int enumRandom = Constants.getRandom(1, ordinal);
		while (enumRandom == oldValue.intValue()) {
			enumRandom = Constants.getRandom(1, ordinal);
		}
		return enumRandom;
	}
	
	private Integer mutateAmounts(Integer oldValue) {
		int amountDouble = oldValue * 2; //TODO: revisar esto
		if (amountDouble == 0) {
			return oldValue;
		}
		int amountRandom = Constants.getRandom(0, amountDouble);
		while (amountRandom == oldValue.intValue()) {
			amountRandom = Constants.getRandom(0, amountDouble);
		}
		return amountRandom;
	}

}
