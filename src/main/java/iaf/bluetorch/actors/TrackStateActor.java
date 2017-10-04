package iaf.bluetorch.actors;

import iaf.bluetorch.actors.utils.PropsGuiceFactory;
import iaf.bluetorch.db.entities.TrackEntity;
import iaf.bluetorch.entitystore.IEntityStore;

import java.util.HashMap;

import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.Logger;

import scala.concurrent.duration.Duration;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;

import com.google.inject.Inject;
import com.mongodb.MongoSocketReadException;

public class TrackStateActor extends AbstractActor {
	
	@Inject private Logger logger;
	@Inject private Configuration config;
	@Inject private IEntityStore trackStore;
	
	HashMap<Integer, Integer> state = new HashMap<>();
	boolean canWriteToDB = true;
	
	private final OneForOneStrategy STRATEGY = new OneForOneStrategy(
		    config.getInt("actor.numberOfTries"),
		    Duration.create("10 seconds"),
		    DeciderBuilder
		      .match(MongoSocketReadException.class, ex -> SupervisorStrategy.resume())
		      .build()
		  );
	
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
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return STRATEGY;
	}

	private void onUpdateMessage(TrackUpdateMessage message) {
		logger.info("***ACTOR-UPDATE*** Got new update message. id: " + message.id);
		trackStore.add(new TrackEntity(message.id, message.fakeVal));
	}
	
	private void onSaveMessage(TrackSaveMessage message) {
		logger.info("***ACTOR-STATE*** Got new save message");
		try {
			if(canWriteToDB) {
				this.child.tell(new DBSaverActor.SaveToDbMessage((IEntityStore) trackStore.clone()), getSelf());
				trackStore.clear();
				canWriteToDB= false;
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	private void onDBSaveAck(DBSaveAck message) {
		logger.info("***ACTOR-ACK*** Got new Ack from child!!!");
		canWriteToDB= true;
	}
	
	public static Props props() {
		return PropsGuiceFactory.create(TrackStateActor.class);
	}
}
