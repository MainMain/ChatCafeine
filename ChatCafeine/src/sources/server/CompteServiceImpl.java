package sources.server;

import sources.client.model.User;
import sources.client.service.CompteService;

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
	public boolean inscription(String login, String mdp, int age, 
			String sexe, String email) { // Tu me retourne true si ca fonctionne, false sinon. C'est pas lors de l'inscription que j'identifie l'user, mais quand il se connecte.

		ConBDD connexion=new ConBDD();
		String requete="INSERT INTO 'Utilisateur' ('ID_salle', 'Login', 'Pass', 'Age', 'Sexe', 'Email','Aime', 'AimePas', 'Droit', 'Avatar', 'NbBannissements', 'DateInscription', 'DateLastConnexion') " +
									"VALUES ('', '"+login+"'"+", '"+mdp+"'"+", '"+age+"'"+", '"+sexe+"'"+", '"+email+"'"+", '', '','utilisateur','',0, '','');";
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		if (resultat.equals("OK"))
			return true;
		else return false;
	}


	@Override
	public boolean desincription(int id) {
		ConBDD connexion=new ConBDD();
		String requete="DELETE FROM Utilisateur WHERE ID_user LIKE '"+id+"'";
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		if (resultat.equals("OK"))
			return true;
		else return false;
	}
}
