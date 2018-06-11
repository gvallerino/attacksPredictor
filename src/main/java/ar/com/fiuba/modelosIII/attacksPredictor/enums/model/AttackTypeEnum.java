package ar.com.fiuba.modelosIII.attacksPredictor.enums.model;

public enum AttackTypeEnum {
	
	OTHER(0, "Otros", 0),
	ASESINATO(1, "Asesinato", 7),
	ASALTO_ARMADO(2, "Asalto Armado", 2),
	BOMBA(3, "Bomba/Explosión", 8),
	HIJACKING(4, "Hijacking", 4),
	REHENES(5, "Toma de rehenes", 5),
	SECUESTRO(6, "Secuestro", 6),
	ATAQUE_INFRAESTRUCTURA(7, "Ataque Infraestructura", 3),
	ASALTO_NO_ARMADO(8, "Asalto no armado", 1);

	public int id;
	public String description;
	public int important;
	
	AttackTypeEnum(int id, String description, int important) {
		this.id = id;
		this.description = description;
		this.important = important;
	}
	
	public static AttackTypeEnum getById(int id) {
		if (id >= AttackTypeEnum.values().length) {
			return AttackTypeEnum.OTHER;
		}
		return AttackTypeEnum.values()[id];
	}
	
	public static int getImportantById(int id) {
		return getById(id).important;
	}
	
	public boolean isMoreImportant(AttackTypeEnum other) {
		return this.important > other.important;
	}
	
	public static int size() {
		return AttackTypeEnum.values().length;
	}
	
}
