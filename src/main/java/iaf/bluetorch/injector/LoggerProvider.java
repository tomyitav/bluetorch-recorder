package iaf.bluetorch.injector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Provider;

public class LoggerProvider implements Provider<Logger> {

	@Override
	public Logger get() {
		Logger logger = LogManager.getRootLogger();
		return logger;
	}

}
