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
	public boolean inscription(String login, String mdp, int age, String sexe,
			String email) { // Tu me retourne true si ca fonctionne, false sinon. C'est pas lors de l'inscription que j'identifie l'user, mais quand il se connecte.
		try {
			String requete="INSERT INTO `db1691085-main`.`UTILISATEUR` (`ID_UTILISATEUR`, `ID_SALLE`, `LOGIN`, `MDP`, `EMAIL`, `GENRE`, `AGE`, `ACTIVITE`, `AIME`, `AIME_PAS`, `DROIT`, `DATE_INSCRIPTION`, `DATE_LAST_CONNEXION`) VALUES ('1000', NULL, '"+login+"'"+", '"+mdp+"'"+", '"+email+"'"+", '"+sexe+"'"+", '"+age+"'"+", NULL, NULL, NULL, 'utilisateur', 'truc qui affiche la date', 'truc qui affiche la date');";
			ConBDD connexion=new ConBDD();
			String resultat=connexion.setData(requete);
			if (resultat == null || resultat.equals("Error") ) {
				//return null;
			}
			connexion.fermer();
		}
		catch (Exception e){
			//return null;
		}
		return false; 
	}

	@Override
	public boolean desincription(int id) {
		return false;
	}
}
