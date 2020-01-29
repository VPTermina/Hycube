package de.gallas_it.sql;

import java.sql.*;  

public class DatabaseConnection {

	
	private Connection connection; 

    static { 
        try { 
            // veraltet Class<?> c = Class.forName("com.mysql.jdbc.Driver"); 
            
            Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
            if (c != null) { 
                System.out.println("JDBC-Treiber geladen"); 
            } 
        } catch (ClassNotFoundException e) { 
            System.err.println("Fehler beim Laden des JDBC-Treibers \n. Please download from https://dev.mysql.com/downloads/connector/j/"); 
            System.exit(1); 
        } 
	
	
}
    
    String url = "jdbc:mysql://localhost/?rewriteBatchedStatements=true"; 
    String user = "root"; 
    String pass = "wwkasper";
	
	
	
	
	public DatabaseConnection(String serverName, String dbType) {
		
		
		try{ 
		
		if (dbType == "MySQL") {
			Class.forName("com.mysql.jdbc.Driver");  
			Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
            if (c != null) { 
                System.out.println("JDBC-Treiber geladen"); 
			
			connection = DriverManager.getConnection("jdbc:mysql://"+ serverName + ":3306" + "/sonoo","root","root");  
            }
			
		}
		
		
		else {
			System.out.println("JDBC-Treiber nicht geladen"); 

		}
		}
		
		catch(Exception e){ 
			System.err.println("Fehler beim Laden des JDBC-Treibers \n. Please download from https://dev.mysql.com/downloads/connector/j/"); 
            System.exit(1); 
			 
		}
	
		// TODO Auto-generated constructor stub
	}	
	
	
public static void connectDB () {
	
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/sonoo","root","root");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from emp");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
		}  
	

public static void connectDBMySQL () {
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/sonoo","root","root");  
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from emp");  
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
con.close();  
}catch(Exception e){ System.out.println(e);}  
}





}
	

