package ar.com.fiuba.modelosIII.attacksPredictor.others;

public abstract class Logger {
	
	private static int longMaxHeader = 50;

	public static void printHeader(String header) {
		try {
			int resto = header.length() % 2 == 0 ? 0 : 1;
			int diff = ((longMaxHeader - header.length()) + resto) / 2;
			StringBuilder headerSB = new StringBuilder();
			headerSB.append("\n -----  ");
			headerSB.append(getCharsWhite(diff));
			headerSB.append(header.toUpperCase());
			headerSB.append(getCharsWhite(diff));
			headerSB.append("  -----\n");
			System.out.println(headerSB.toString());
		} catch (Exception e) {
			System.out.println(" -----  " + header.toUpperCase() + "  -----");
		}
	}
	
	private static String getCharsWhite(int size) {
		StringBuilder whites = new StringBuilder();
		for (int i = 0; i < size; i++) {
			whites.append(" ");
		}
		return whites.toString();
	}
	
	public static void print(String text) {
		System.out.println(text);
	}
	
	public static void printEnter() {
		System.out.print("\n");
	}
}
