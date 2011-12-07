package sources.server;

import sources.client.model.User;
import sources.client.service.CompteService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class CompteServiceImpl extends RemoteServiceServlet implements CompteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3597453029675338488L;

	@Override
	public boolean inscription(String url, String login, String password,String loginUser, String mdp, int age, String sexe,
			String email) { // Tu me retourne true si ca fonctionne, false sinon. C'est pas lors de l'inscription que j'identifie l'user, mais quand il se connecte.

		ConBDD connexion=new ConBDD(url,login,password);
		String requete="INSERT INTO Utilisateur (ID_user, ID_salle, Login, Pass, Age, Sexe, Email, Aime, AimePas, Droit, Avatar, NbBannissements, DateInscription, DateLastConnexion, CompteurChat) " +
		"VALUES (null,null,'"+loginUser+"'"+", '"+mdp+"'"+", '"+age+"'"+", '"+sexe+"'"+", '"+email+"'"+", null, null,'utilisateur', null,0, null, null, null);";
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		if (resultat.equals("OK"))
			return true;
		else return false;
	}


	@Override
	public boolean desincription(String url, String login, String password,
			int idUser) {
		ConBDD connexion=new ConBDD(url,login,password);
		String requete="DELETE FROM Utilisateur WHERE ID_user LIKE '"+idUser+"'";
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		if (resultat.equals("OK")){
			Window.alert("Votre compte a �t� supprim�");
			return true;
		}
		else return false;
	}
}
