package iaf.bluetorch;

import iaf.bluetorch.actors.TrackStateActor;
import iaf.bluetorch.runnables.DbRequestsTask;
import iaf.bluetorch.runnables.MessageGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class RecorderMain {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("system");
		final ActorRef trackMessageActor = system.actorOf(TrackStateActor.props(), "trackUpdates");
		
		ExecutorService executorService  = Executors.newFixedThreadPool(2);
		executorService.execute(new MessageGenerator(trackMessageActor));
		executorService.execute(new DbRequestsTask(trackMessageActor));
	}
}
