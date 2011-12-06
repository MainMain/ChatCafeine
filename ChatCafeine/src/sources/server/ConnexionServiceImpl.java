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
		
		String requete= "SELECT ID_user, ID_Salle, Login, Pass, Age, Sexe, Email, Aime, AimePas, Droit, Avatar " +
				"FROM Utilisateur WHERE Login = '"+login+"' AND pass = '"+mdp+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				User p=new User();
				p.setIdUser(resultat.getString("ID_user"));
				//p.setActivite(resultat.getString("ACTIVITE")); Pas de colone activité ! O_O !
				p.setAge(resultat.getString("Age"));
				p.setDroit(resultat.getString("Droit"));
				p.setEmail(resultat.getString("Email"));
				p.setGenre(resultat.getString("Sexe"));
				p.setLogin(resultat.getString("Login"));
				p.setMdp(resultat.getString("Pass"));
				return p;//String[]{"OK",resultat.getString("id")};
			}
			connexion.fermer();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		 
	/* if (login.equals("mainmain") && mdp.equals("azer")){
			User p = new User();
			p.setLogin("mainmain");
			return p;
		}
		else
			return null;
		*/
	}
}