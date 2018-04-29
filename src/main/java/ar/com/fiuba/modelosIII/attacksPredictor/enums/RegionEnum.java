package ar.com.fiuba.modelosIII.attacksPredictor.enums;

public enum RegionEnum {

	OTHER(0, "Otros", 0),
	NORTH_AMERICA(1, "Norte América", 1),
	CENTRAL_AMERICA(2, "América Central", 2),
	SOUTH_AMERICA(3, "Sudamérica", 3),
	EAST_ASIA(4, "Asia del Este", 4),
	SOUTHEAST_ASIA(5, "Sudeste de Asia", 5),
	SOUTH_ASIA(6, "Sur de Asia", 6),
	CENTRAL_ASIA(7, "Centro de Asia", 7),
	WESTERN_EUROPE(8, "Europa Occidental", 8),
	EASTERN_EUROPE(9, "Europa Oriental", 9),
	MIDDLE_EAST_NORTH_AFRICA(10, "Centro y Norte de Africa", 10),
	SUBSAHARA_AFRICA(11, "Sub-Sahara Africa", 11),
	OCEANIA(12, "Oceanía", 12);
	
	private int id;
	private String description;
	private int important;
	
	RegionEnum(int id, String description, int important) {
		this.id = id;
		this.description = description;
		this.important = important;
	}
	
	public static RegionEnum getById(int id) {
		//System.out.println(id);
		return RegionEnum.values()[id];
	}
	
	public static int getImportantById(int id) {
		return getById(id).important;
	}
}
