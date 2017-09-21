package iaf.bluetorch.actors;

import iaf.bluetorch.actors.TrackStateActor.DBSaveAck;
import iaf.bluetorch.actors.utils.PropsGuiceFactory;
import iaf.bluetorch.db.entities.TrackEntity;
import iaf.bluetorch.db.service.IDBService;
import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.google.inject.Inject;

public class DBSaverActor extends AbstractLoggingActor {
	
	@Inject private IDBService dbService;
	
	//Protocol
	public static class SaveToDbMessage {
		private Integer id;
		
		public SaveToDbMessage(Integer id) {
			this.id = id;
		}
	}
	
	private void onSaveMessage(SaveToDbMessage trackMessage) {
		log().info("***ACTOR-DB*** Got new Save message. id: " + trackMessage.id);
		dbService.persist(new TrackEntity(trackMessage.id));
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
		return PropsGuiceFactory.create(DBSaverActor.class);
	}
}
