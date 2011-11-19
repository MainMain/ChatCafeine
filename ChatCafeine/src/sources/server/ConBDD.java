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
	public static boolean DEBUG = true;


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
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			if(DEBUG)System.out.println("begin connecttodb");
<<<<<<< HEAD
			connOjbect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/azer", "root", "");
=======
			connOjbect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ChatCafeine", "root", "");
>>>>>>> c1e5eb687876ae77af9fbe1705b3d329e32b070d
			if(DEBUG)System.out.println("end connecttodb");
			return true;
		} catch (SQLException e) {
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
			return false;
		}
		
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
