package iaf.bluetorch.runnables;

import iaf.bluetorch.actors.TrackStateActor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import akka.actor.ActorRef;

public class DbRequestsTask implements Runnable {
	
	private ActorRef trackMessageActor;
	
	public DbRequestsTask(ActorRef trackMessageActor) {
		this.trackMessageActor = trackMessageActor;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Periodic request to write to DB..." + dateFormat.format(date));
				this.trackMessageActor.tell(new TrackStateActor.TrackSaveMessage(), ActorRef.noSender());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
