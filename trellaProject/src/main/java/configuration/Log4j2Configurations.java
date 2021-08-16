package configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Configurations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		Logger logg= LogManager.getLogger(Log4j2Configurations.class);
		System.out.println("hello world");
  logg.info("hello info");
	logg.fatal("fattaaal");	
		logg.error("errorororo");
	}

}
