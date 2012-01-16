/**
 * 
 */
package sources.server;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import sources.client.model.User;
import sources.client.service.AdminService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class AdminServiceImpl extends RemoteServiceServlet implements AdminService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9003233947034934261L;

	/* (non-Javadoc)
	 * @see sources.client.service.AdminService#getAllUsers()
	 */
	@Override
	public ArrayList<User> getAllUsers() {	
		ConBDD connexion=new ConBDD();	
		ArrayList<User> a = new ArrayList<User> ();		
		try{			
			String requete = "SELECT * FROM Utilisateur";			
			ResultSet result = connexion.getData(requete);			
			while (result.next()){				
				a.add(new User(result.getString("Login"), result.getInt("NbEjections"), result.getInt("NbBannissements"), result.getString("Droit"), result.getString("DateLastConnexion")));			
			}		
		}catch (Exception e){			
			System.out.println("Erreur lors de la récupération des utilisateurs !\n->"+e.getMessage());		
		}		
		return a;	
	}

	public boolean deleteUser(String login) {
		ConBDD connexion=new ConBDD();
		String requete="DELETE FROM Utilisateur WHERE Login LIKE '"+login+"'";
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		if (resultat.equals("OK")){
			Window.alert("Votre compte a été supprimé");
			return true;
		}
		else return false;
	}

	/* (non-Javadoc)
	 * @see sources.client.service.AdminService#majUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean majUser(String login, String droit) {
		ConBDD connexion=new ConBDD();
		String requete="UPDATE Utilisateur SET Droit = '"+droit+"' WHERE Login LIKE '"+login+"'";

		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		if (resultat.equals("OK")){
			Window.alert("Votre compte a été supprimé");
			return true;
		}
		else return false;
	}

}
