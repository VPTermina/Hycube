package de.gallas_it.http;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

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

	
	
	
	public HttpConnection(String http) {
		super();
		this.httpCommand = http;
		this.authCommand = "http://192.168.3.166/auth/.";
		this.authToken = null;
	}
	
	 
	private static final String USER_AGENT = "Mozilla/5.0";
	//private static final String GET_URL = "http://localhost:9090/SpringMVCExample";
	//private String GET_URL = "http://easy-test.nucleus.atom.ads:8080/experience/";
	private  String GET_URL = "";
	
	private static final String POST_URL = "http://easy-test.nucleus.atom.ads:8080/experience/";
	private static final String POST_PARAMS = "userName=Pankaj";
	
	
	
	
	
	public boolean auth() {

        try {
            URL url = new URL ("http://192.168.3.166/auth/");

            String encoding = Base64.getEncoder().encodeToString(("Basic "+"hycube:hycube").getBytes("UTF-8"));
            
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
	 * 
	 * 
	 * 
	 * @return
	 */
	
	
	public boolean getValues() {

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