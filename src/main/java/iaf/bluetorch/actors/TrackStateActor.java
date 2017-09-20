package iaf.bluetorch.actors;

import java.util.HashMap;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class TrackStateActor extends AbstractLoggingActor {
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
				.matchAny(somethingElse -> log().info("Recieved something else"))
				.build();
	}

	private void onUpdateMessage(TrackUpdateMessage message) {
		log().info("***ACTOR-UPDATE*** Got new update message. id: " + message.id);
	}
	
	private void onSaveMessage(TrackSaveMessage message) {
		log().info("***ACTOR-STATE*** Got new save message");
		this.child.tell(new DBSaverActor.SaveToDbMessage(2), getSelf());
	}
	
	private void onDBSaveAck(DBSaveAck message) {
		log().info("***ACTOR-ACK*** Got new Ack from child!!!");
	}
	
	public static Props props() {
		return Props.create(TrackStateActor.class);
	}
}
