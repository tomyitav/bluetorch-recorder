package iaf.bluetorch.core;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Before;
import org.junit.Test;

public class RecorderConfigTest {
	
	Configuration config;
	RecorderConfig recorderConfig;

	@Before
	public void setUp() throws Exception {
		Configurations configBuilder = new Configurations();
		try {
			config = configBuilder.properties(new File("src/test/java/testData" + File.separator + "config.properties"));
			recorderConfig = new RecorderConfig(config);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDbHost() {
		assertEquals(recorderConfig.dbHost(), "localhost");
	}

	@Test
	public void testDbPort() {
		assertEquals(recorderConfig.dbPort(), 27017);
	}

	@Test
	public void testDbName() {
		assertEquals(recorderConfig.dbName(), "track_history");
	}

	@Test
	public void testDbSocketTimeout() {
		assertEquals(recorderConfig.dbSocketTimeout(), 60000);
	}

	@Test
	public void testDbConnectTimeout() {
		assertEquals(recorderConfig.dbConnectTimeout(), 15000);
	}

	@Test
	public void testDbMaxConnectionIdleTime() {
		assertEquals(recorderConfig.dbMaxConnectionIdleTime(), 600000);
	}

}
