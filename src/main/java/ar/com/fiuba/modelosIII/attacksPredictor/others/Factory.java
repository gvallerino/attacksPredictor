package ar.com.fiuba.modelosIII.attacksPredictor.others;

import ar.com.fiuba.modelosIII.attacksPredictor.configurations.ConstantsConfigurations;
import ar.com.fiuba.modelosIII.attacksPredictor.enums.data.ConfigurationsDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.model.TerroristAttacksDataSet;
import ar.com.fiuba.modelosIII.attacksPredictor.reader.ManagementFile;

public class Factory {
	
	public static void makeEnviorement() {
		ConfigurationsDataSet.getInstance();
		ManagementFile.loadConfigurations();
		ConstantsConfigurations.loadConstants();
		ClustersGrapher.loadConfiguration();
		TerroristAttacksDataSet.getInstance();
	}

}
