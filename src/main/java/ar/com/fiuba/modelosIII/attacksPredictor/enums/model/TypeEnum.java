package ar.com.fiuba.modelosIII.attacksPredictor.enums.model;

public enum TypeEnum {
	
	OTHER(0, null),
	REGION(1, RegionEnum.class),
	ATTACK(2, AttackTypeEnum.class),
	TARGET(3, TargetTypeEnum.class),
	WEAPON(4, WeaponTypeEnum.class);

	public int id;
	public Object enumeration;
	
	TypeEnum (int id, Object enumeration) {
		this.id = id;
		this.enumeration = enumeration;
	}
	
	public static AttackTypeEnum getById(int id) {
		if (id >= AttackTypeEnum.values().length) {
			return AttackTypeEnum.OTHER;
		}
		return AttackTypeEnum.values()[id];
	}
	
}
