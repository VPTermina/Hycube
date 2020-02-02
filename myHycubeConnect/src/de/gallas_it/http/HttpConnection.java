package de.gallas_it.http;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import de.gallas_it.sql.DatabaseConnection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import javax.json.spi.JsonProvider;


public class HttpConnection {
     String serverDNSname;
     String serverPort;
     String serverUserName;
     String serverPassword;
     String httpCommand;
     String authCommand;
     String authToken;
	
/**
 * 
 * @param serverDNSname
 * @param serverPort
 * @param serverUserName
 * @param serverPassword
 */
     
	public HttpConnection(String serverDNSname, String serverPort, String serverUserName, String serverPassword) {
		super();
		this.serverDNSname = serverDNSname;
		this.serverPort = serverPort;
		this.serverUserName = serverUserName;
		this.serverPassword = serverPassword;
		
	     GET_URL = "http://"+ this.serverDNSname +":8080/experience/";
	}

	 
	private static final String USER_AGENT = "Mozilla/5.0";
	//private static final String GET_URL = "http://localhost:9090/SpringMVCExample";
	//private String GET_URL = "http://easy-test.nucleus.atom.ads:8080/experience/";
	private  String GET_URL = "";
	
	private static final String POST_URL = "http://easy-test.nucleus.atom.ads:8080/experience/";
	private static final String POST_PARAMS = "userName=Pankaj";
	
	
	
	/**
	 * 
	 * 
	 * @return
	 */
	
	public boolean auth() {

        try {
            URL url = new URL ("http://"+ this.serverDNSname + "/auth/");

            String encoding = Base64.getEncoder().encodeToString(("Basic "+this.serverUserName +":" + this.serverPassword).getBytes("UTF-8"));
            
            //String encoding = "hycube:hycube";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", encoding);
            
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                this.authToken=line;
            	System.out.println(line); 
            }
        } catch(Exception e) {
            e.printStackTrace();
          
        }
        return true;
    }
	
	
	/**
	 *
	 *Returns the follwoing JSON object
	{
	" Battery_C ": 55,
	" Battery_I ": -3.03,
	" Battery_P ": -149,	
	" Battery_V ": 48.93,
	" Grid_f ": 49.99,
	" Grid_P ": 64,
	" Grid_V ": 228.4,
	" Home_P ": 144,
	" Inv 1_I": 0.8,
	" Inv 1_P": 80,
	" Inv 1_V": 228.4,
	" Solar 1_P": 0,
	" Solar 1_I": 0,
	" Solar 1_V": 0,
	" solar 2_P": 0,
	" solar 2_I": 0,
	" solar 2_V": 0,
	" solar_total_P ": 0
	}
	 *  Battery_C
 Battery_I
 Battery_P
 Battery_V
 Grid_f
 Grid_P
 Grid_V
 Home_P
 Inv 1_I
 Inv 1_P
 Inv 1_V
 Solar 1_P
 Solar 1_I
 Solar 1_V
 solar 2_P
 solar 2_I
 solar 2_V
 solar_total_P

	 * 
	 * 
	 * @return
	 */
	
	public boolean getValues() {

        try {
            URL url = new URL ("http://"+ this.serverDNSname + "/get_values/");

            
            //String encoding = "hycube:hycube";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", this.authToken);
            
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            
            String line;
            
            String line1="";
            while ((line = in.readLine()) != null) {
             line1=line1+line;
           
            
            }

            // neue lib
            
            try {
    	        JSONObject jsonObject = new JSONObject(line1);
    	        
    	        System.out.println(jsonObject.isEmpty());
    	       
    	        
    	        if (!jsonObject.isEmpty()) {
    	        
    	        	DatabaseConnection myDBConnect = new DatabaseConnection("192.168.3.10","rocco", "IwiWW42!","consumptions", "MySQL");
    	        	helpJson2Database("Battery_C",jsonObject ,"i",myDBConnect);

    	         	        helpJson2Database("Battery_I",jsonObject ,"d",myDBConnect);
 
    	        
    	        helpJson2Database("Battery_P",jsonObject ,"i",myDBConnect);

                
                helpJson2Database("Battery_V",jsonObject ,"d",myDBConnect);
     
                
                helpJson2Database("Grid_P",jsonObject ,"d",myDBConnect);
     
                
                helpJson2Database("Grid_V",jsonObject ,"d",myDBConnect);
  
                
                helpJson2Database("Home_P",jsonObject ,"d",myDBConnect);

                
                helpJson2Database("Inv1_I",jsonObject ,"d",myDBConnect);
   
                
                helpJson2Database("Inv1_P",jsonObject ,"i",myDBConnect);
    
                
                helpJson2Database("Inv1_V",jsonObject ,"d",myDBConnect);
       
                
                
                helpJson2Database("Meter2_P",jsonObject ,"d",myDBConnect);

                
                helpJson2Database("Solar1_I",jsonObject ,"d",myDBConnect);
     
                
                helpJson2Database("Solar1_P",jsonObject ,"i",myDBConnect);
      
                
                helpJson2Database("Solar1_V",jsonObject ,"d",myDBConnect);
           
                
                helpJson2Database("solar2_I",jsonObject ,"d",myDBConnect);
            
                
                helpJson2Database("solar2_P",jsonObject ,"i",myDBConnect);
          
                
                helpJson2Database("solar2_V",jsonObject ,"d",myDBConnect);
           
                
                helpJson2Database("solar_total_P",jsonObject ,"i",myDBConnect);
             
    	        }
                
                
    	        
    	     	        
          
    	    } catch (JSONException e) {
    	        e.printStackTrace();
    	      return false;
    	}
	        

            
            
        } catch(Exception e) {
            e.printStackTrace();
          
        }
        return true;
    }
	
    private void helpJson2Screen(String name, JSONObject jo, String type) {
    	
    	switch(type){
        case "s":
        	System.out.println(name + ": " + jo.getString(name));
            break;
        case "i":
        	System.out.println(name +"  : " + jo.getInt(name));
            break;
        case "d":
        	System.out.println(name +  "  : " + jo.getDouble(name));
            break;

        default:
            System.out.println("i liegt nicht zwischen null und drei");
        }
    }
    	
    	
private void helpJson2Database(String name, JSONObject jo, String type, DatabaseConnection myDBConnect) {
    	
	


	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    
	String aktDate = now.format(formatter);
	
	
	
    	switch(type){
        case "s":
        	System.out.println(name + ": " + jo.getString(name));
            break;
        case "i":
        	myDBConnect.sendInsertI(aktDate,jo.getInt(name),"","",name);        	
        	System.out.println(name +"  : " + jo.getInt(name));
            break;
        case "d":
        	myDBConnect.sendInsertD(aktDate,jo.getDouble(name),"","",name);
        	System.out.println(name +  "  : " + jo.getDouble(name));
            break;

        default:
            System.out.println("i liegt nicht zwischen null und drei");
        }
    } 	
    	
  
	
	
	
	/**
	 * 
	 * Ausgabe des http Objectes am Screen
	 * 
	 * @return
	 */
	public boolean getValuesPrintScreen() {

        try {
URL url = new URL ("http://"+ this.serverDNSname + "/get_values/");

            
            //String encoding = "hycube:hycube";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", this.authToken);
            
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));

        

           String line;
           while ((line = in.readLine()) != null) {
            
           	System.out.println(line); 
           }
            
            
        } catch(Exception e) {
            e.printStackTrace();
          
        }
        return true;
    }
	
	

	
	public boolean getValuesFromfile() {

        try {
            URL url = new URL ("http://192.168.3.166/get_values/");

            
            //String encoding = "hycube:hycube";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", this.authToken);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            


            JsonReader reader = Json.createReader(in);
            

            JsonObject personObject = reader.readObject();

            reader.close();

            System.out.println("Name   : " + personObject.getInt("Battery_C"));
            System.out.println("Name   : " + personObject.getInt("Grid_P"));
       
            
            String line;
            while ((line = in.readLine()) != null) {
             
            	System.out.println(line); 
            }
        } catch(Exception e) {
            e.printStackTrace();
          
        }
        return true;
    }
	
	
    public void interpretJSONgetValues() throws FileNotFoundException {
    	
    	InputStream fis = new FileInputStream("config/values.json");

        JsonReader reader = Json.createReader(fis);

        JsonObject personObject = reader.readObject();

        reader.close();

        System.out.println("Name   : " + personObject.getInt(" Battery_C "));
        System.out.println("Name   : " + personObject.getInt(" Grid_P "));
        

    
    }
	
	public void testInterpretJSON() throws FileNotFoundException {
		
		InputStream fis = new FileInputStream("config/person.json");

        JsonReader reader = Json.createReader(fis);

        JsonObject personObject = reader.readObject();

        reader.close();

        System.out.println("Name   : " + personObject.getString("name"));
        System.out.println("Age    : " + personObject.getInt("age"));
        System.out.println("Married: " + personObject.getBoolean("isMarried"));

        JsonObject addressObject = personObject.getJsonObject("address");
        System.out.println("Address: ");
        System.out.println(addressObject.getString("street"));
        System.out.println(addressObject.getString("zipCode"));

        System.out.println("Phone  : ");
        JsonArray phoneNumbersArray = personObject.getJsonArray("phoneNumbers");

        for (JsonValue jsonValue : phoneNumbersArray) {
            System.out.println(jsonValue.toString());
        }
    }
	
	
	
	
	

	
	
	
	/**
	 * 
	 * @return
	 */
	
	
	public boolean getValuesIntervall() {

        try {
            URL url = new URL ("http://192.168.3.166/day_val/?from=11:00:00&to=11:02:00");
            // Achtung: Problem mit dem Zeitformat nach 12

            
            //String encoding = "hycube:hycube";
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", this.authToken);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
            
            	System.out.println(line); 
            }
        } catch(Exception e) {
            e.printStackTrace();
          
        }
        return true;
    }
	
	
	
	public boolean runMe() throws IOException {

		sendGET();
		System.out.println("GET DONE");
		sendPOST();
		System.out.println("POST DONE");
		return true;
	}

	public boolean runInfo() throws IOException {
		GET_URL=this.httpCommand;
		sendGET();
		
		System.out.println("POST DONE");
		return true;
	}
	
	public boolean doAuthentification() throws IOException {
		GET_URL=this.authCommand;
		sendGET();
		
		System.out.println("POST DONE");
		return true;
	}
	
	public boolean getValues1() throws IOException {
		GET_URL=this.httpCommand;
		sendGET();
		
		System.out.println("POST DONE");
		return true;
	}
	
	
	
	
	private  void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}

	private void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}

}