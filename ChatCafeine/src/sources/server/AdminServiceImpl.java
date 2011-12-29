/**
 * 
 */
package sources.server;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import sources.client.model.User;
import sources.client.service.AdminService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class AdminServiceImpl extends RemoteServiceServlet implements AdminService{

	/* (non-Javadoc)
	 * @see sources.client.service.AdminService#getAllUsers()
	 */
	@Override
	public ArrayList<User> getAllUsers() {	
			String url = "//127.0.0.1:3306/chatcafeine";		
			String login = "root";		
			String password = "";		
			ConBDD connexion=new ConBDD(url,login,password);	
			ArrayList<User> a = new ArrayList<User> ();		
			try{			
				String requete = "SELECT * FROM utilisateur";			
				ResultSet result = connexion.getData(requete);			
				while (result.next()){				
					a.add(new User(result.getString("Login"), result.getInt("NbEjections"), result.getInt("NbBannissements")));			
				}		
			}catch (Exception e){			
				System.out.println("Erreur lors de la récupération des salles !\n->"+e.getMessage());		
			}		
			return a;	
		}
	}
