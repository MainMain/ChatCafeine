package sources.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import sources.client.model.User;
import sources.client.service.ConnexionService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class ConnexionServiceImpl extends RemoteServiceServlet implements ConnexionService {


	public User authentifier(String login, String mdp) {
		
		User user = new User();
		String requete="SELECT ID_UTILISATEUR,LOGIN,MDP,EMAIL,GENRE, AGE,ACTIVITE,DROIT,DATE_INSCRIPTION,DATE_LAST_CONNEXION FROM UTILISATEUR WHERE LOGIN LIKE '"+login+"' AND MDP LIKE '"+mdp+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				User p=new User();
				p.setIdUser(resultat.getString("ID_UTILISATEUR"));
				p.setActivite(resultat.getString("ACTIVITE"));
				p.setAge(resultat.getString("AGE"));
				p.setDateInscription(resultat.getString("DATE_INSCRIPTION"));
				p.setDateLastConnexion(resultat.getString("DATE_LAST_CONNEXION"));
				p.setDroit(resultat.getString("DROIT"));
				p.setEmail(resultat.getString("EMAIL"));
				p.setGenre(resultat.getString("GENRE"));
				p.setLogin(resultat.getString("LOGIN"));
				p.setMdp(resultat.getString("MDP"));

				
				return p;//String[]{"OK",resultat.getString("id")};
			}
			connexion.fermer();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
