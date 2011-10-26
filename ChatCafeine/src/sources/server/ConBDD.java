package sources.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			connOjbect = DriverManager.getConnection("http://www5.subdomain.com/phpMyAdmin/index.php?lang=fr-utf-8&token=ec4ebda0534fba09bdd282869dfc620d", "user1691085", "yuI3zNVz");
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

