package es.sinjava.scheduler;

import java.util.TimerTask;
import java.util.logging.Logger;

public class ActivityTask extends TimerTask {

	Logger log = Lanzador.log;
	String name;
	public ActivityTask(String string) {
		name= string;
	}

	@Override
	public void run() {
		log.info("Registrada la llamada a "+name);
		this.cancel();
	}
}
