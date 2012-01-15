package sources.server;

import java.sql.ResultSet;
import java.util.Date;

import sources.client.service.CompteService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

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
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String date1 = format.format(new Date());
		String requete="INSERT INTO Utilisateur (" +
				"ID_user, Login, 			Pass, 			Age, 		Sexe,			 Email, 		Aime, 			AimePas, 		Activite, 	Droit, 		Avatar, NbBannissements, 	NbEjections, 		DateInscription, DateLastConnexion) " +
		"VALUES (null,'"+loginUser+"'"+", '"+mdp+"'"+", '"+age+"'"+", '"+sexe+"'"+", '"+email+"'"+", 'Non Renseigné', 'Non Renseigné', 'Non Renseigné', 'utilisateur', null,		0, 					0,		'"+date1+"'"+", 	'"+date1+"'"+");";
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
		// RETOURNE FALSE SI LE LOGIN EST BANNI DE LA SALLE
		
		String requete = "Select * from Bannir, Salle WHERE Salle.ID_salle = bannir.ID_Salle AND Bannir.ID_user ='"+idUser+"' AND Salle.Nom LIKE '"+nomSalle+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		connexion.fermer();
		if (resultat==null){
			return false;
		}else{
			return true;
		}
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
