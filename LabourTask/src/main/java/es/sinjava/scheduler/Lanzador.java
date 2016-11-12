package es.sinjava.scheduler;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Lanzador {

	public static Logger log = inicializeLogger();

	public static void main(String... args) throws InterruptedException {
		log.fine("Mensaje");
		// crear un timer
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.MINUTE, true);

		int seed = 0;
		TimerTask initialTask = new ActivityTask("Instancia " + seed++);
		timer.schedule(initialTask, 10L);
		long timeToSleep =0;
		
		Calendar calendarEnd= Calendar.getInstance();
		calendarEnd.roll(Calendar.MINUTE, false);
		calendarEnd.add(Calendar.MINUTE, 10);
		
		log.info("Acaba a las " +calendarEnd.getTime());
		do {
			calendar= Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 1);
			initialTask = new ActivityTask("Instancia " + seed++);
			timer.schedule(initialTask, calendar.getTime());
			timeToSleep= initialTask.scheduledExecutionTime() - new Date().getTime();
			if (timeToSleep>0){
				System.out.println("Tiempo "+ timeToSleep );
			}
			timeToSleep= (timeToSleep<0L)?0:timeToSleep;
			
			Thread.sleep(timeToSleep);
			
		} while (calendarEnd.after(calendar));

		timer.cancel();
	}

	private static Logger inicializeLogger() {
		Logger log = Logger.getAnonymousLogger();
		Handler fh;
		try {
			fh = new FileHandler("%t/labour.log", false);
			fh.setEncoding("UTF-8");
			Formatter xmlFormatter = new SimpleFormatter();
			fh.setFormatter(xmlFormatter);
			log.setLevel(Level.ALL);
			log.addHandler(fh);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		return log;
	}
}
