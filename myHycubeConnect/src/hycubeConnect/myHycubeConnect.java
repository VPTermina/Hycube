package hycubeConnect;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fuin.utils4j.PropertiesFilePreferencesFactory;

import httpConnections.httpConnectionTools;


/**
 * Ein Programm um den HyCube zu kontaktieren und auszulesen.
 * Anfrage via http request
 *
 * @author Rocco Gallas
 * @version 1.0
 */
public class myHycubeConnect {
	private static final Logger logger = LogManager.getLogger(myHycubeConnect.class);

	
	/**
     * Hauptprogramm.
     * Please check preference.properties in <userhome>/.myHycubeConnectSettings/preference.properties
     * @param args Kommandozeilenparameter
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		readConfigFile();
		// Write something to the preferences
		
		
		final Preferences userPrefs = Preferences.userRoot();
		userPrefs.putInt("a", 1);
		userPrefs.put("b", "test");
		
		String Rocco = userPrefs.get("httpServer",new String());
		

		logger.info( "Please check the user " + userPrefs.get("httpServer",new String()));		

        //httpConnectionTools myConnection = new httpConnectionTools(Rocco, userPrefs.get("httpPort",new String()), userPrefs.get("httpUsername",new String()), userPrefs.get("httpUserPasswort",new String()));
        
		httpConnectionTools myConnection = new httpConnectionTools("http://192.168.3.166/info/.");
        
        myConnection.auth();
        myConnection.getValues();
        myConnection.getValuesIntervall();
        
        
        		
		logger.info(  "Meine Info-Meldung aus MeineKlasse2."  );
		logger.warn(  "Meine Warn-Meldung aus MeineKlasse2."  );
	    logger.error( "Meine Error-Meldung aus MeineKlasse." );
		logger.debug("Meine drbug-Meldung aus MeineKlasse2.");
		logger.trace("Meine trace Meldung");
		
		
	}
	
	static void readConfigFile() {
		
		// Create an application wide preferences directory 
				// named "config" in the current startup directory
				final File systemPrefDir = new File("./config");
				if (!systemPrefDir.exists()) {
				    systemPrefDir.mkdir();
				}

				// Create a user preferences directory with your 
				// applications name in the user's home directory
				final File userHomeDir = new File(System.getProperty("user.home"));
				final File userPrefDir = new File(userHomeDir, ".myHycubeConnectSettings");
				if (!userPrefDir.exists()) {
				    userPrefDir.mkdir();
				    
				}

				// Set both directories as system properties
				System.setProperty(PropertiesFilePreferencesFactory.SYSTEM_PREF_DIR,systemPrefDir.toString());
				System.setProperty(PropertiesFilePreferencesFactory.USER_PREF_DIR, userPrefDir.toString());

				// Set the factory
				System.setProperty("java.util.prefs.PreferencesFactory",
				        PropertiesFilePreferencesFactory.class.getName());

				

		
				//
				
		
	}
	
	
	

}
