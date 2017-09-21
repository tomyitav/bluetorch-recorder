package iaf.bluetorch.injector;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class AppInjector {
	private static final AppInjector INSTANCE = new AppInjector();
	private Injector injector;
	
	private AppInjector() {
		injector = Guice.createInjector(new DBModule());
	}
	
	public static AppInjector instance() {
		return INSTANCE;
	}
	
	public Injector getInjector() {
		return injector;
	}
}
