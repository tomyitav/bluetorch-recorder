package iaf.bluetorch.actors;

import iaf.bluetorch.actors.TrackStateActor.DBSaveAck;
import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class DBSaverActor extends AbstractLoggingActor {
	
	//Protocol
	public static class SaveToDbMessage {
		private Integer id;
		
		public SaveToDbMessage(Integer id) {
			this.id = id;
		}
	}
	
	private void onSaveMessage(SaveToDbMessage message) {
		log().info("***ACTOR-DB*** Got new Save message. id: " + message.id);
		getSender().tell(new DBSaveAck(), ActorRef.noSender());
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(SaveToDbMessage.class, this::onSaveMessage)
				.matchAny(somethingElse -> log().info("Recieved something else"))
				.build();
	}

	public static Props props() {
		return Props.create(DBSaverActor.class);
	}
}
