package sources.server;

import sources.client.service.CompteService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class CompteServiceImpl extends RemoteServiceServlet implements CompteService {
	String url = "//127.0.0.1:3306/chatcafeine";
	String login = "root";
	String password = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = -3597453029675338488L;

	@Override
	public boolean inscription(String loginUser, String mdp, int age, String sexe,
			String email) { // Tu me retourne true si ca fonctionne, false sinon. C'est pas lors de l'inscription que j'identifie l'user, mais quand il se connecte.

		ConBDD connexion=new ConBDD();
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
	/**
	 * 
	 */
	@Override
	public boolean desincription(int idUser) {
		ConBDD connexion=new ConBDD();
		String requete="DELETE FROM Utilisateur WHERE ID_user LIKE '"+idUser+"'";
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
	/**
	 * 
	 */
	@Override
	public boolean isBanniSalle(int idUser, String nomSalle) {
		// TRAVAIL D'AUDREY
		// RETOURNE SI LE LOGIN EST BANNI DE LA SALLE
		return false;
	}
	@Override
	public boolean bannirUser(int idUser, String nomSalle, int temps) {
		// TRAVAIL D'AUDREY
		// BANNI UN USER DE LA SALLE
		// LE TEMPS EST EN MINUTES
		// IL FAUT INCREMENTER LE NOMBRE DE BANNISSEMENT DE L'USER
		return false;
	}
	@Override
	public boolean majDateLastConnexion(int idUser) {
		// TRAVAIL D'AUDREY
		// RECUPERE LA DATE ACTUELLE ET INSERE LA DANS "DateLastConnexion"
		return false;
	}
}
