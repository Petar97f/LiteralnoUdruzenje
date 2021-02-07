package com.example.kp.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


import org.springframework.stereotype.Service;

@Service
public class LoggingService {

	 private final Logger logger = Logger.getLogger(LoggingService.class
	            .getName());
	    private FileHandler fh = null;
	    private final Path root = Paths.get("uploads");

	    public void doLogging() {
	        logger.info("info msg");
	        logger.severe("error message");
	        logger.fine("fine message"); //won't show because to high level of logging
	    }
	    
	    public LoggingService() {
	        //just to make our log file nicer :)
	        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
	        try {
	            fh = new FileHandler("LogFile_"
	                + format.format(Calendar.getInstance().getTime()) + ".log", 0, 1, true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	       // fh.setFormatter(new SimpleFormatter());
	       // logger.addHandler(fh);
	    }

	    
		public synchronized void writeLog(String severity, String msg, String classname) {
//	        try {
////	          synchronized (this)	if (fh == null) {
////	        		 Date date = new Date();
////	                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
////	                 fh = new FileHandler((dateFormat.format(date) + ".log"), false);
////	        		synchronized (this){
////	        		fh =  new FileHandler("logger.log", 0, 1, true);
////	        }
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	        //fh.setLevel(Level.INFO);
	        fh.setFormatter(new SimpleFormatter());
	        logger.addHandler(fh);
	        Level level;
	        level = convert(severity);
	        Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
	        logger.setUseParentHandlers(false);
	        logger.setLevel(level);
	        logger.info("classname: " + classname +" level"+ level +" date: " + dateFormat.format(date) + " message: " + msg);
	        
	    }
	    
	    public Level convert(final String value) {
	    	if (value.equals("SEVERE"))
	    		return Level.SEVERE;
	    	else if (value.equals("WARNING"))
	    		return Level.WARNING;
	    	else if (value.equals("INFO"))
	    		return Level.INFO;
	    	else if (value.equals("CONFIG"))
	    		return Level.CONFIG;
	    	else if (value.equals("FINE"))
	    		return Level.FINE;
	    	else if (value.equals("FINER"))
	    		return Level.FINER;
	    	else if (value.equals("FINEST"))
	    		return Level.FINEST;
	    	else
	    		throw new RuntimeException("Incorrect Log Level.");
	    }
}
