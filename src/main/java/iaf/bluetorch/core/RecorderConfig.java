package iaf.bluetorch.core;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RecorderConfig implements IRecorderConfig {
	
	private Configuration config;

	@Inject
	public RecorderConfig(Configuration config) {
		this.config = config;
	}

	@Override
	public String dbHost() {
		return config.getString("db.host");
	}

	@Override
	public int dbPort() {
		return config.getInt("db.port");
	}

	@Override
	public String dbName() {
		return config.getString("db.name");
	}

	@Override
	public int dbSocketTimeout() {
		return config.getInt("db.socketTimeout");
	}

	@Override
	public int dbConnectTimeout() {
		return config.getInt("db.connectTimeout");
	}

	@Override
	public int dbMaxConnectionIdleTime() {
		return config.getInt("db.maxConnectionIdleTime");
	}

}
