package hycubeConnect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class myHycubeConnect {
	private static final Logger logger = LogManager.getLogger(myHycubeConnect.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

logger.trace("Exiting application.");
		
		logger.info(  "Meine Info-Meldung aus MeineKlasse2."  );
		logger.warn(  "Meine Warn-Meldung aus MeineKlasse2."  );
	    logger.error( "Meine Error-Meldung aus MeineKlasse." );
		logger.debug("Meine drbug-Meldung aus MeineKlasse2.");
		logger.trace("Meine trace Meldung");
		
		
	}

}
