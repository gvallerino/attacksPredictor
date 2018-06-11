package ar.com.fiuba.modelosIII.attacksPredictor.enums.model;

public enum TargetTypeEnum {
	
	NOTHING(0, "-", 0),
	NEGOCIOS(1, "Negocios", 19),
	GOBIERNO_GENERAL(2, "Gobierno general", 15),
	POLICIA(3, "Politica", 14),
	MILITAR(4, "Militar", 20),
	ABORTO(5, "Relacionado al aborto", 2),
	AERONAVES_AEROPUERTOS(6, "Aeronaves y aeropuertos", 17),
	GOBIERNO_DIPLOMATICOS(7, "Gobierno diplomático", 16),
	INSTITUCIONES_EDUCACIONALES(8, "Instituciones educacionales", 18),
	COMIDA_AGUA(9, "Soporte a comida o agua", 5),
	PERIODISTAS(10, "Periodistas", 4),
	MARITIMO(11, "Marítimo", 13),
	NGO(12, "Organizaciones No Gubernamentales", 3),
	OTROS(13, "Otros", 2),
	CIVILES_Y_PROPIEDADES(14, "Civiles y propiedades", 9),
	INSTITUCIONES_RELIGIOSAS(15, "Instituciones religiosas", 8),
	TELECOMUNICACION(16, "Telecomunicación", 7),
	TERRORISTAS(17, "Terroristas", 21),
	TURISTAS(18, "Turistas", 6),
	TRANSPORTE(19, "Transporte", 12),
	EMPTY(20, "", 1),
	UTILIDADES(21, "Utilidades", 10),
	GRUPOS_POLITICOS(22, "Grupos politicos", 11);

	public int id;
	public String description;
	public int important;
	
	TargetTypeEnum(int id, String description, int important) {
		this.id = id;
		this.description = description;
		this.important = important;
	}
	
	public static TargetTypeEnum getById(int id) {
		if (id >= TargetTypeEnum.values().length) {
			return TargetTypeEnum.NOTHING;
		}
		return TargetTypeEnum.values()[id];
	}
	
	public static int getImportantById(int id) {
		return getById(id).important;
	}
	
	public boolean isMoreImportant(TargetTypeEnum other) {
		return this.important > other.important;
	}
	
	public static int size() {
		return TargetTypeEnum.values().length;
	}

}
