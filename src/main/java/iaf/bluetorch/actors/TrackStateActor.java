package iaf.bluetorch.actors;

import iaf.bluetorch.actors.utils.PropsGuiceFactory;
import iaf.bluetorch.db.entities.TrackEntity;
import iaf.bluetorch.trackstore.ITrackStore;

import java.util.HashMap;

import org.apache.logging.log4j.Logger;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.google.inject.Inject;

public class TrackStateActor extends AbstractActor {
	
	@Inject private Logger logger;
	@Inject private ITrackStore trackStore;
	
	HashMap<Integer, Integer> state = new HashMap<>();
	boolean canWriteToDB = true;
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
		trackStore.add(new TrackEntity(message.id, message.fakeVal));
	}
	
	private void onSaveMessage(TrackSaveMessage message) {
		logger.info("***ACTOR-STATE*** Got new save message");
		try {
			if(canWriteToDB) {
				this.child.tell(new DBSaverActor.SaveToDbMessage((ITrackStore) trackStore.clone()), getSelf());
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
