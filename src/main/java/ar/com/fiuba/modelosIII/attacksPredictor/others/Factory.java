package ar.com.fiuba.modelosIII.attacksPredictor.others;

import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class Factory {
	
	public static void makeEnviorement() {
		ConfigurationsDataSet.getInstance();
		ManagementFile.loadConfigurations();
		ClustersGrapher.loadConfiguration();
		TerroristAttacksDataSet.getInstance();
	}

}
