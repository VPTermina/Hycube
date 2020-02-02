package de.gallas_it.hycube;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fuin.utils4j.PropertiesFilePreferencesFactory;

import de.gallas_it.sql.DatabaseConnection;
import de.gallas_it.http.*;

import java.nio.channels.FileChannel;
import java.nio.file.Files;



/**
 * Ein Programm um den HyCube zu kontaktieren und auszulesen.
 * Die Ergebnisse werden im SQL Server abgelegt
 * 
 * Anfrage via http request
 *
 * @author Rocco Gallas
 * @version 1.0
 */
public class HycubeConnect {


	private static final Logger logger = LogManager.getLogger(HycubeConnect.class);

	
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
		
		String myHttpServer = userPrefs.get("httpServer","noValue");
		
		logger.info( "The Http server:" + myHttpServer);		

       // httpConnectionTools myConnection = new HttpConnection(userPrefs.get("", userPrefs.get("httpPort",new String()), userPrefs.get("httpUsername",new String()), userPrefs.get("httpUserPasswort",new String()));
		 HttpConnection myConnection = new HttpConnection("192.168.3.166","80","hycube","hycube");
		
		
		//httpConnectionTools myConnection = new httpConnectionTools("http://192.168.3.166/info/.");
		
		//HttpConnection myConnection = new HttpConnection(myHttpServer);
        
        myConnection.auth();
        //myConnection.getValues();
       
        
        //myConnection.getValuesPrintScreen();
        
        myConnection.getValuesIntervall();
        
        // SQL connection
		
		//DatabaseConnection myDBConnect = new DatabaseConnection("localhost","rocco", "wwkasper01!","world", "MySQL");
		//DatabaseConnection myDBConnect = new DatabaseConnection("192.168.3.10","rocco", "IwiWW42!","consumptions", "MySQL");
		//myDBConnect.sendSQLQuery("select * from devices");
		//myDBConnect.sendInsert("","","","","","");
		//JSON tes
		 
		/*
		 * try {
		
			myConnection.interpretJSONgetValues();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		
		ValuesStruct myValues = new ValuesStruct();
		//myValues.init();
		
		
        		
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
