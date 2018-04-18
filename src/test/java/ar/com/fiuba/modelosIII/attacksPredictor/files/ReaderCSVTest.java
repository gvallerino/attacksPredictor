package ar.com.fiuba.modelosIII.attacksPredictor.files;

import org.junit.Test;

import ar.com.fiuba.modelosIII.attacksPredictor.reader.ReaderCSV;

public class ReaderCSVTest {

	@Test
	public void testReader() {
		ReaderCSV reader = new ReaderCSV();
		reader.loadFile();
	}
}
