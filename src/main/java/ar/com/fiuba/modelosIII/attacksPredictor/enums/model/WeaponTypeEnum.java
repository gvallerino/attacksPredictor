package ar.com.fiuba.modelosIII.attacksPredictor.enums.model;

public enum WeaponTypeEnum {

	NOTHING(0, "-", 0),
	BIOLOGICO(1, "Biologico", 10),
	QUIMICO(2, "Quimico", 9),
	RADIOACTIVO(3, "Radioactivo", 11),
	NUCLEAR(4, "Nuclear", 12),
	ARMAS_FUEGO(5, "Armas de fuego", 7),
	EXPLOSIVOS(6, "Explosivos/Bombas/Dinamita", 8),
	ARMAS_FALSAS(7, "Armas falsas", 2),
	INCENDIARIAS(8, "Incendiarias", 6),
	CUERPO_A_CUERPO(9, "Cuerpo a cuerpo", 5),
	VEHICULO(10, "Vehículo", 4),
	SABOTAJE(11, "Equipamento de sabotaje", 3),
	OTROS(12, "Otros", 1);
	
	public int id;
	public String description;
	public int important;
	
	WeaponTypeEnum(int id, String description, int important) {
		this.id = id;
		this.description = description;
		this.important = important;
	}
	
	public static WeaponTypeEnum getById(int id) {
		if (id >= WeaponTypeEnum.values().length) {
			return WeaponTypeEnum.NOTHING;
		}
		return WeaponTypeEnum.values()[id];
	}
	
	public static int getImportantById(int id) {
		return getById(id).important;
	}
	
	public boolean isMoreImportant(WeaponTypeEnum other) {
		return this.important > other.important;
	}
	
	public static int size() {
		return WeaponTypeEnum.values().length;
	}
}
