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
	private static final long serialVersionUID = 5691744844859451602L;
	/**
	 * 
	 */
	public User authentifier(String url, String login, String password,
			String id, String mdp) {
		String requete= "SELECT ID_user, ID_Salle, Login, Pass, Age, Sexe, Email, Aime, AimePas, Droit, Avatar " +
				"FROM Utilisateur WHERE Login = '"+id+"' AND pass = '"+mdp+"'";
		ConBDD connexion=new ConBDD(url,login,password);
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				User p=new User();
				p.setIdUser(resultat.getString("ID_user"));
				p.setId(resultat.getString("ID_user"));
				//p.setActivite(resultat.getString("ACTIVITE")); Pas de colone activité ! O_O !
				p.setAge(resultat.getString("Age"));
				p.setDroit(resultat.getString("Droit"));
				p.setEmail(resultat.getString("Email"));
				p.setGenre(resultat.getString("Sexe"));
				p.setLogin(resultat.getString("Login"));
				p.setMdp(resultat.getString("Pass"));
				p.setAime(resultat.getString("Aime"));
				p.setAimePas(resultat.getString("AimePas"));
				if (resultat.getString("Avatar") != null)
						p.setCheminAvatar(resultat.getString("Avatar"));
				else
					p.setCheminAvatar("anonyme.jpg");
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