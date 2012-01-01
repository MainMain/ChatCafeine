package sources.server;

import java.sql.*;

public class ConBDD {

	private Connection connOjbect = null;

	private static String URL      = "//127.0.0.1:3306/ChatCafeine";
	private static String userName = "root";
	private static String userPWD  = "";
	
	//private static String URL      = "//lerelou.dyndns.org:3306/ChatCafeine";
	//private static String userName = "MainMain";
	//private static String userPWD  = "azerty";
	
	public static boolean DEBUG = true;

	public ConBDD() {
		super();
		connectToDB();
	}

	/*private void setConnexionData(String URLparam, String userNameparam, String userPWDparam){
		URL = URLparam;
		userName = userNameparam; 
		userPWD = userPWDparam; 
	}*/

	private boolean connectToDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			connOjbect = DriverManager.getConnection("jdbc:mysql:"+URL, userName, userPWD);
			//connOjbect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ChatCafeine", "root", "");
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
