package iaf.bluetorch.actors;

import iaf.bluetorch.actors.TrackStateActor.DBSaveAck;
import iaf.bluetorch.actors.utils.PropsGuiceFactory;
import iaf.bluetorch.db.entities.TrackEntity;
import iaf.bluetorch.db.service.IDBService;
import iaf.bluetorch.trackstore.ITrackStore;

import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.Logger;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.google.inject.Inject;

public class DBSaverActor extends AbstractActor {
	
	@Inject private Logger logger;
	@Inject private Configuration config;
	@Inject private IDBService dbService;
	
	//Protocol
	public static class SaveToDbMessage {
		private ITrackStore trackStore;

		public SaveToDbMessage(ITrackStore trackStore) {
			this.trackStore = trackStore;
		}

		public ITrackStore getTrackStore() {
			return trackStore;
		}
	}
	
	private void onSaveMessage(SaveToDbMessage trackMessage) {
		logger.info("***ACTOR-DB*** Got new Save message: ");
		String dbHost = config.getString("db.host");
		System.out.println("DB HOST - " + dbHost);
		dbService.persist(trackMessage.getTrackStore());
		getSender().tell(new DBSaveAck(), ActorRef.noSender());
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(SaveToDbMessage.class, this::onSaveMessage)
				.matchAny(somethingElse -> logger.warn("Recieved something else"))
				.build();
	}

	public static Props props() {
		return PropsGuiceFactory.create(DBSaverActor.class);
	}
}
