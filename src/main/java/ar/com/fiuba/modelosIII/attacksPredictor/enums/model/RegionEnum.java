package ar.com.fiuba.modelosIII.attacksPredictor.enums.model;

public enum RegionEnum {

	OTHER(0, "Otros", 0),
	NORTH_AMERICA(1, "Norte América", 1), //10 binary
	CENTRAL_AMERICA(2, "América Central", 2), //11 binary
	SOUTH_AMERICA(3, "Sudamérica", 3), //12 binary
	EAST_ASIA(4, "Asia del Este", 4), //13 binary
	SOUTHEAST_ASIA(5, "Sudeste de Asia", 5), //14 binary
	SOUTH_ASIA(6, "Sur de Asia", 6), //15 binary
	CENTRAL_ASIA(7, "Centro de Asia", 7), //16 binary
	WESTERN_EUROPE(8, "Europa Occidental", 8), //17 binary
	EASTERN_EUROPE(9, "Europa Oriental", 9), //18 binary
	MIDDLE_EAST_NORTH_AFRICA(10, "Centro y Norte de Africa", 10), //19 binary
	SUBSAHARA_AFRICA(11, "Sub-Sahara Africa", 11), //20 binary
	OCEANIA(12, "Oceanía", 12); //21 binary
	
	public int id;
	public String description;
	public int important;
	
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
	
	public boolean isMoreImportant(RegionEnum other) {
		return this.important > other.important;
	}
	
	public static int size() {
		return RegionEnum.values().length;
	}
}
