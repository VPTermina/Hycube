package httpConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class httpConnectionTools {
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
     
	public httpConnectionTools(String serverDNSname, String serverPort, String serverUserName, String serverPassword) {
		super();
		this.serverDNSname = serverDNSname;
		this.serverPort = serverPort;
		this.serverUserName = serverUserName;
		this.serverPassword = serverPassword;
		
	     GET_URL = "http://"+ this.serverDNSname +":8080/experience/";
	}

	
	
	
	public httpConnectionTools(String http) {
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