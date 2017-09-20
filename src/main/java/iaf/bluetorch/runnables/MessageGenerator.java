package iaf.bluetorch.runnables;

import iaf.bluetorch.actors.TrackStateActor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import akka.actor.ActorRef;

public class MessageGenerator implements Runnable {

	private ActorRef trackMessageActor;

	public MessageGenerator(ActorRef trackMessageActor) {
		this.trackMessageActor = trackMessageActor;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep((long)(Math.random() * 1000));
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Simulating send to actor..." + dateFormat.format(date));
				this.trackMessageActor.tell(new TrackStateActor.TrackUpdateMessage(1,1), ActorRef.noSender());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
