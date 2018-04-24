package ar.com.fiuba.modelosIII.attacksPredictor.enums;

public enum TargetTypeEnum {
	
	NOTHING(0, "-"),
	NEGOCIOS(1, "Negocios"),
	GOBIERNO_GENERAL(2, "Gobierno general"),
	POLICIA(3, "Politica"),
	MILITAR(4, "Militar"),
	ABORTO(5, "Relacionado al aborto"),
	AERONAVES_AEROPUERTOS(6, "Aeronaves y aeropuertos"),
	GOBIERNO_DIPLOMATICOS(7, "Gobierno diplomático"),
	INSTITUCIONES_EDUCACIONALES(8, "Instituciones educacionales"),
	COMIDA_AGUA(9, "Soporte a comida o agua"),
	PERIODISTAS(10, "Periodistas"),
	MARITIMO(11, "Marítimo"),
	NGO(12, "Organizaciones No Gubernamentales"),
	OTROS(13, "Otros"),
	CIVILES_Y_PROPIEDADES(14, "Civiles y propiedades"),
	INSTITUCIONES_RELIGIOSAS(15, "Instituciones religiosas"),
	TELECOMUNICACION(16, "Telecomunicación"),
	TERRORISTAS(17, "Terroristas"),
	TURISTAS(18, "Turistas"),
	TRANSPORTE(19, "Transporte"),
	EMPTY(20, ""),
	UTILIDADES(21, "Utilidades"),
	GRUPOS_POLITICOS(22, "Grupos politicos");

	private int id;
	private String description;
	
	TargetTypeEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static TargetTypeEnum getById(int id) {
		//System.out.println(id);
		return TargetTypeEnum.values()[id];
	}

}
