package ar.com.fiuba.modelosIII.attacksPredictor.enums;

public enum RegionEnum {

	OTHER(0, "Otros"),
	NORTH_AMERICA(1, "Norte América"),
	CENTRAL_AMERICA(2, "América Central"),
	SOUTH_AMERICA(3, "Sudamérica"),
	EAST_ASIA(4, "Asia del Este"),
	SOUTHEAST_ASIA(5, "Sudeste de Asia"),
	SOUTH_ASIA(6, "Sur de Asia"),
	CENTRAL_ASIA(7, "Centro de Asia"),
	WESTERN_EUROPE(8, "Europa Occidental"),
	EASTERN_EUROPE(9, "Europa Oriental"),
	MIDDLE_EAST_NORTH_AFRICA(10, "Centro y Norte de Africa"),
	SUBSAHARA_AFRICA(11, "Sub-Sahara Africa"),
	OCEANIA(12, "Oceanía");
	
	private int id;
	private String description;
	
	RegionEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static RegionEnum getById(int id) {
		//System.out.println(id);
		return RegionEnum.values()[id];
	}
}
