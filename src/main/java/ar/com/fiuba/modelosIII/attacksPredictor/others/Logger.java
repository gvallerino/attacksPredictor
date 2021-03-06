package ar.com.fiuba.modelosIII.attacksPredictor.others;

public abstract class Logger {
	
	private static int longMaxHeader = 50;

	public static void printHeader(String header) {
		try {
			int diff = longMaxHeader - header.length();
			int resto = diff % 2 == 0 ? 0 : 1;
			int diffTotal = diff / 2;
			StringBuilder headerSB = new StringBuilder();
			headerSB.append("\n -----  ");
			headerSB.append(getCharsWhite(diffTotal+resto));
			headerSB.append(header.toUpperCase());
			headerSB.append(getCharsWhite(diffTotal));
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
	
	public static void printError(String text) {
		text = "ERROR | " + text;
		print(text);
	}
}
