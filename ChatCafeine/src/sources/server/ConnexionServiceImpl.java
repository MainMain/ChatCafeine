/**
 * 
 */
package sources.server;

//import java.sql.ResultSet;
//import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.SQLException;

import sources.client.model.User;
import sources.client.service.ConnexionService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConnexionServiceImpl extends RemoteServiceServlet implements ConnexionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1942157650673416454L;

	@Override
	public User authentifier(String login, String mdp) {
		String requete="SELECT LOGIN,MDP FROM UTILISATEUR WHERE LOGIN LIKE '"+login+"' AND MDP LIKE '"+mdp+"'"; // Non, tu doit me renvoyer toutes les infos de l'user !
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null || resultat.equals("Error"))
			return null;
		else {
			try {
				while (resultat.next()){
					User p=new User();
					p.setIdUser(resultat.getString("ID_UTILISATEUR"));
					p.setLogin(resultat.getString("LOGIN"));
					p.setMdp(resultat.getString("MDP"));
					p.setDroit(resultat.getString("DROIT")); // Oublie pas, tu me renvoi "utilisateur", "moderateur" ou "administrateur" (et attention à la casse)
					connexion.fermer();
					return p; //String[]{"OK",resultat.getString("id")};
				}
				return null;
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
