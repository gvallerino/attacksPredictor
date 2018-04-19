package ar.com.fiuba.modelosIII.attacksPredictor.model;

import java.util.List;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.AttackTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.RegionEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.TargetTypeEnum;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.WeaponTypeEnum;

public class TerroristAttack {

	private String id;
	private int year;
	private RegionEnum region;
	private boolean multiple;
	private boolean success;
	private boolean suicide;
	private AttackTypeEnum attackType;
	private TargetTypeEnum targetType;
	private WeaponTypeEnum weaponType;
	private int amountKill;
	private int amountWound;
	
	public TerroristAttack (String id, List<Integer> values) {
		this.id = id;
		for (int i = 0; i < values.size(); i++) {
			int value = values.get(i).intValue();	
			switch (i) {
				case 0: this.year = value; break;
				case 1: this.region = RegionEnum.getById(value); break;
				case 2: this.multiple = value == 1;break;
				case 3: this.success = value == 1;break;
				case 4: this.suicide = value == 1;break;
				case 5: this.attackType = AttackTypeEnum.getById(value); break;
				case 6: this.targetType = TargetTypeEnum.getById(value); break;
				case 7: this.weaponType = WeaponTypeEnum.getById(value); break;
				case 8: this.amountKill = value; break;
				case 9: this.amountWound = value; break;
			}
		}
	}
}
