package de.gallas_it.sql;

import java.sql.*;



public class DatabaseConnection {

	
	Connection connection; 
	String database_url;
    String database_user; 
    String database_pass;
    String database_name;
    String database_settings;
	  
	
	/**
	 * Constructor um Java Klasse für MySQL Datenbank Verbindung zu initialisieren 	
	 * @param serverName
	 * @param dbType
	 */
	
	public DatabaseConnection(String serverName, String user, String pass, String database, String dbType) {
		
		
		try{ 
		
		this.database_user = user;
		this.database_pass= pass;
		this.database_settings = "/" + database +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		this .database_url = "jdbc:mysql://" + serverName + "/?rewriteBatchedStatements=true\"";
		this.database_name = database;
				
			
			
		if (dbType == "MySQL") {
		 
			Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
            if (c != null) { 
                System.out.println("JDBC-Treiber geladen"); 
                
			  
              this.connection = DriverManager.getConnection("jdbc:mysql://"+ serverName + ":3306"+ this.database_settings,this.database_user,this.database_pass);  
              
              //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
            }
			
		}
		
		
		else {
			System.out.println("Kein Datenbanktreiber angegeben. Aktuell nur JDBC Treiber für MySQL unterstuetzt"); 

		}
		}
		
		
		
		catch  (java.sql.SQLException e) {
			System.out.println(e);
			System.err.println("Fehler bei der Datenbankanmeldung!"); 
            System.exit(1); 
		}
		
		
		catch  (ClassNotFoundException e) {
			System.out.println(e);
			System.err.println("Fehler beim Laden des JDBC-Treibers. Please download from https://dev.mysql.com/downloads/connector/j/"); 
            System.exit(1); 
		}
		

		
		catch(Exception e){ 
			System.out.println(e);
			System.err.println("Datenbank Initialisierung nicht erfolgreich!"); 
            System.exit(1); 
			 
		}
	
		// TODO Auto-generated constructor stub
	}	
	
/**
 * 
 * 
 * 	
 * @param query
 */

public void sendSQLQuery (String query) {
try{  
Statement stmt=this.connection.createStatement();  
ResultSet rs=stmt.executeQuery(query);  
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
this.connection.close();  
}catch(Exception e){ System.out.println(e);}  
}





}
	

