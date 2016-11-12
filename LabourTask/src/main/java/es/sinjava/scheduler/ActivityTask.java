package es.sinjava.scheduler;

import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
