package iaf.bluetorch.actors;

import iaf.bluetorch.injector.AppInjector;
import akka.actor.Props;

public class PropsGuiceFactory {
	public static Props create(Class<?> cls) {
		return Props.create(GuiceInjectedActor.class, AppInjector.instance().getInjector(), cls);
	}
}
