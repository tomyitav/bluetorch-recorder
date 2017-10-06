package iaf.bluetorch.actors;

import iaf.bluetorch.actors.TrackStateActor.DBSaveAck;
import iaf.bluetorch.db.service.IDBService;
import iaf.bluetorch.entitystore.IEntityStore;

import org.apache.logging.log4j.Logger;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.google.inject.Inject;

public class DBSaverActor extends AbstractActor {

	@Inject private Logger logger;
	@Inject private IDBService dbService;

	//Protocol
	public static class SaveToDbMessage {
		private IEntityStore trackStore;

		public SaveToDbMessage(IEntityStore trackStore) {
			this.trackStore = trackStore;
		}

		public IEntityStore getEntityStore() {
			return trackStore;
		}
	}

	private void onSaveMessage(SaveToDbMessage trackMessage) {
		logger.info("***ACTOR-DB*** Got new Save message: ");
		dbService.persist(trackMessage.getEntityStore());
		getSender().tell(new DBSaveAck(), ActorRef.noSender());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(SaveToDbMessage.class, this::onSaveMessage)
				.matchAny(somethingElse -> logger.warn("Recieved something else"))
				.build();
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		logger.info("DB Actor restart, sending ack to parent...");
		context().parent().tell(new DBSaveAck(), ActorRef.noSender());
	}

	public static Props props() {
		return PropsGuiceFactory.create(DBSaverActor.class);
	}
}
