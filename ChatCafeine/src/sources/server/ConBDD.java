package sources.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.google.gwt.user.client.Window;

public class ConBDD {

	private Connection connOjbect = null;

	private static String URL      = "";
	private static String userName = "";
	private static String userPWD  = "";



	public ConBDD() {
		super();
		connectToDB();
	}

	private void setConnexionData(String URLparam, String userNameparam, String userPWDparam){
		URL = URLparam;
		userName = userNameparam; 
		userPWD = userPWDparam; 
	}

	private boolean connectToDB(){
		/*Properties prop = new Properties();
		// fichier de config 'config.myproperties'
		FileInputStream in;
		try {
			//in = new FileInputStream("config/config.myproperties");
			//prop.load(in);
			prop.load(ConBDD.class.getResourceAsStream("config.myproperties"));
			//in.close();
		} catch (FileNotFoundException e1) {
			Window.alert("Fichier de configuration JDBC non trouve cote serveur");
			e1.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			Window.alert("Probleme lecture du fichier de configuration JDBC cote serveur");
			e.printStackTrace();
			System.exit(1);
		}
		//System.out.println(ConBDD.class.);

		// Extraction des propriétés
		String url = prop.getProperty("jdbcurl");
		String user = prop.getProperty("jdbcuser");
		String password = prop.getProperty("jdbcpassword"); 
		if (password==null)
			password="";*/
		//setConnexionData("jdbc:mysql://localhost:3306/Enigme2", "root", "");
		//setConnexionData(url, user, password);
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connOjbect = DriverManager.getConnection("http://www11.subdomain.com/phpMyAdmin/index.php?lang=fr-utf-8&convcharset=iso-8859-1&collation_connection=utf8_unicode_ci&token=18b2cf9712220b4c5b3a1580790445b8", "user1687730", "yuI3zNVz");
			return true;
		} catch (SQLException e) {
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
		}
		return false;	
	}

	public Connection getConnectionObject(){
		return connOjbect;
	}


	public String setData(String SQL) {

		try {
			if (connOjbect == null){
				if (! connectToDB()){
					return "Error";
				}	
			}

			String message;

			Statement st = connOjbect.createStatement();

			if (st.execute(SQL)){
				message = "OK";
			}
			else{
				message = "Error";
			}
			st.close();
			return message;	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}


	}

	public ResultSet getData(String SQL) {
		ResultSet results = null;

		if (connOjbect==null)
			connectToDB();
		try {
			results = connOjbect.createStatement().executeQuery(SQL);

		} catch (SQLException e) {
			System.err.println("Impossible d'executer la requete");
			e.printStackTrace();
		}

		return results;


	}

	public void fermer() {
		if (connOjbect!=null)
			try {
				connOjbect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}



}

