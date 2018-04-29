package ar.com.fiuba.modelosIII.attacksPredictor.enums;

public enum WeaponTypeEnum {

	NOTHING(0, "-", 0),
	BIOLOGICO(1, "Biologico", 1),
	QUIMICO(2, "Quimico", 2),
	RADIOACTIVO(3, "Radioactivo", 3),
	NUCLEAR(4, "Nuclear", 4),
	ARMAS_FUEGO(5, "Armas de fuego", 5),
	EXPLOSIVOS(6, "Explosivos/Bombas/Dinamita", 6),
	ARMAS_FALSAS(7, "Armas falsas", 7),
	INCENDIARIAS(8, "Incendiarias", 8),
	CUERPO_A_CUERPO(9, "Cuerpo a cuerpo", 9),
	VEHICULO(10, "Vehículo", 10),
	SABOTAJE(11, "Equipamento de sabotaje", 11),
	OTROS(12, "Otros", 12);
	
	public int id;
	public String description;
	public int important;
	
	WeaponTypeEnum(int id, String description, int important) {
		this.id = id;
		this.description = description;
		this.important = important;
	}
	
	public static WeaponTypeEnum getById(int id) {
		return WeaponTypeEnum.values()[id];
	}
	
	public static int getImportantById(int id) {
		return getById(id).important;
	}
	
	public boolean isMoreImportant(WeaponTypeEnum other) {
		return this.important > other.important;
	}
}
