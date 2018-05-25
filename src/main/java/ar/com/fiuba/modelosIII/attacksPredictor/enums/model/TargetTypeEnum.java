package ar.com.fiuba.modelosIII.attacksPredictor.enums.model;

public enum TargetTypeEnum {
	
	NOTHING(0, "-", 0),
	NEGOCIOS(1, "Negocios", 1),
	GOBIERNO_GENERAL(2, "Gobierno general", 2),
	POLICIA(3, "Politica", 3),
	MILITAR(4, "Militar", 4),
	ABORTO(5, "Relacionado al aborto", 5),
	AERONAVES_AEROPUERTOS(6, "Aeronaves y aeropuertos", 6),
	GOBIERNO_DIPLOMATICOS(7, "Gobierno diplomático", 7),
	INSTITUCIONES_EDUCACIONALES(8, "Instituciones educacionales", 8),
	COMIDA_AGUA(9, "Soporte a comida o agua", 9),
	PERIODISTAS(10, "Periodistas", 10),
	MARITIMO(11, "Marítimo", 11),
	NGO(12, "Organizaciones No Gubernamentales", 12),
	OTROS(13, "Otros", 13),
	CIVILES_Y_PROPIEDADES(14, "Civiles y propiedades", 14),
	INSTITUCIONES_RELIGIOSAS(15, "Instituciones religiosas", 15),
	TELECOMUNICACION(16, "Telecomunicación", 16),
	TERRORISTAS(17, "Terroristas", 17),
	TURISTAS(18, "Turistas", 18),
	TRANSPORTE(19, "Transporte", 19),
	EMPTY(20, "", 0),
	UTILIDADES(21, "Utilidades", 21),
	GRUPOS_POLITICOS(22, "Grupos politicos", 22);

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
