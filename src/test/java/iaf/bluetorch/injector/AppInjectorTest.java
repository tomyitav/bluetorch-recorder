package iaf.bluetorch.injector;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

public class AppInjectorTest {
	
	private static AppInjector appInj;

	@BeforeClass
	public static void setUpClass() throws Exception {
		appInj = AppInjector.instance();
	}

	@Test
	public void testGetInjector() {
		Injector inj = appInj.getInjector();
		assertNotNull(inj);
	}

}
