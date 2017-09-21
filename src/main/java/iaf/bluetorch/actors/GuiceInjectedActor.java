package iaf.bluetorch.actors;

import com.google.inject.Injector;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class GuiceInjectedActor implements IndirectActorProducer {

	final Injector injector;
	final Class<? extends Actor> actorClass;

	public GuiceInjectedActor(Injector injector, Class<? extends Actor> actorClass) {
		this.injector = injector;
		this.actorClass = actorClass;
	}

	@Override
	public Class<? extends Actor> actorClass() {
		return actorClass;
	}

	@Override
	public Actor produce() {
		return injector.getInstance(actorClass);
	}
}