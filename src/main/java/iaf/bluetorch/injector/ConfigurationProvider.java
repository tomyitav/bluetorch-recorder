package iaf.bluetorch.injector;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.google.inject.Provider;

public class ConfigurationProvider implements Provider<Configuration> {

	@Override
	public Configuration get() {
		Configurations configBuilder = new Configurations();
		try {
			String localConfigDir = System.getenv("LOCAL_CONFIG_DIR");
			Configuration config = configBuilder.properties(new File(localConfigDir + File.separator + "config.properties"));
			return config;
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
