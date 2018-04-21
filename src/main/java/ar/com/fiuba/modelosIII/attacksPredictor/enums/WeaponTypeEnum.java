package ar.com.fiuba.modelosIII.attacksPredictor.enums;

public enum WeaponTypeEnum {

	NOTHING(0, "-"),
	BIOLOGICO(1, "Biologico"),
	QUIMICO(2, "Quimico"),
	RADIOACTIVO(3, "Radioactivo"),
	NUCLEAR(4, "Nuclear"),
	ARMAS_FUEGO(5, "Armas de fuego"),
	EXPLOSIVOS(6, "Explosivos/Bombas/Dinamita"),
	ARMAS_FALSAS(7, "Armas falsas"),
	INCENDIARIAS(8, "Incendiarias"),
	CUERPO_A_CUERPO(9, "Cuerpo a cuerpo"),
	VEHICULO(10, "Vehículo"),
	SABOTAJE(11, "Equipamento de sabotaje"),
	OTROS(12, "Otros");
	
	private int id;
	private String description;
	
	WeaponTypeEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static WeaponTypeEnum getById(int id) {
		return WeaponTypeEnum.values()[id];
	}
}
