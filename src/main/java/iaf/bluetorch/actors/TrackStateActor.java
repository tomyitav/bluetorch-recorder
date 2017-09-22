package iaf.bluetorch.actors;

import iaf.bluetorch.actors.utils.PropsGuiceFactory;

import java.util.HashMap;

import org.apache.logging.log4j.Logger;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.google.inject.Inject;

public class TrackStateActor extends AbstractActor {
	
	@Inject private Logger logger;
	
	HashMap<Integer, Integer> state = new HashMap<>();
	boolean writeToDbAllowed = true;
	final ActorRef child = getContext().actorOf(DBSaverActor.props(), "child");
	
	//Protocol
	public static class TrackUpdateMessage {
		private Integer id;
		private Integer fakeVal;
		
		
		public TrackUpdateMessage(Integer id, Integer fakeVal) {
			this.id = id;
			this.fakeVal = fakeVal;
		}
	}
	
	public static class TrackSaveMessage {
		
	}
	
	public static class DBSaveAck {
		
	}
	
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TrackUpdateMessage.class, this::onUpdateMessage)
				.match(TrackSaveMessage.class, this::onSaveMessage)
				.match(DBSaveAck.class, this::onDBSaveAck)
				.matchAny(somethingElse -> logger.warn("Recieved something else"))
				.build();
	}

	private void onUpdateMessage(TrackUpdateMessage message) {
		logger.info("***ACTOR-UPDATE*** Got new update message. id: " + message.id);
	}
	
	private void onSaveMessage(TrackSaveMessage message) {
		logger.info("***ACTOR-STATE*** Got new save message");
		this.child.tell(new DBSaverActor.SaveToDbMessage(2), getSelf());
	}
	
	private void onDBSaveAck(DBSaveAck message) {
		logger.info("***ACTOR-ACK*** Got new Ack from child!!!");
	}
	
	public static Props props() {
		return PropsGuiceFactory.create(TrackStateActor.class);
	}
}
