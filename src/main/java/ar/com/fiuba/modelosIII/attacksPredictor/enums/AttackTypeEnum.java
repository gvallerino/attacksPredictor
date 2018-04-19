package ar.com.fiuba.modelosIII.attacksPredictor.enums;

public enum AttackTypeEnum {
	
	OTHER(0, "Otros"),
	ASESINATO(1, "Asesinato"),
	ASALTO_ARMADO(2, "Asalto Armado"),
	BOMBA(3, "Bomba/Explosión"),
	HIJACKING(4, "Hijacking"),
	REHENES(5, "Toma de rehenes"),
	SECUESTRO(6, "Secuestro"),
	ATAQUE_INFRAESTRUCTURA(7, "Ataque Infraestructura"),
	ASALTO_NO_ARMADO(8, "Asalto no armado");

	private int id;
	private String description;
	
	AttackTypeEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static AttackTypeEnum getById(int id) {
		return AttackTypeEnum.values()[id-1];
	}
}
