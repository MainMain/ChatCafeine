/**
 * 
 */
package sources.server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import sources.client.service.ProfilService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class ProfilServiceImpl extends RemoteServiceServlet implements ProfilService{
	private static final long serialVersionUID = 71298666683531087L;

	@Override
	public boolean modifMdp(int idUser, String newMdp) {
		// LA METHODE SERA CODEE PAR AUDREY *********************


		ConBDD connexion=new ConBDD();
		String requete="UPDATE Utilisateur SET Pass="+"\'"+newMdp+"\'"+" WHERE ID_user = \'"+idUser+"\' ";
		System.out.println("TESTTEST : requete = "+requete);
		connexion.setData(requete);
		connexion.fermer();
		System.out.println("Votre mot de passe à bien été changé");
		return true;

	}
	/**
	 * 
	 */
	@Override
	public boolean modifInfos(int idUser, String aime, String aimePas, int age,
			String activite) {
		// Controle que les infos ne soient pas vides et insére que ceux complétés. (et que age > 10 et < 99)
		// LA METHODE SERA CODEE PAR AUDREY *********************

		//pas de champ activité dans la BDD pour le moment donc activite non traitee.
		ConBDD connexion=new ConBDD();
		String requete="UPDATE Utilisateur SET Aime = '"+aime+"', AimePas = '"+aimePas+"', Activite = '"+activite+"', Age = '"+age+"' WHERE ID_user = '"+idUser+"'";
		System.out.println("TEST : requete = "+requete);
		connexion.setData(requete);
		connexion.fermer();
		System.out.println("Vos informations ont été changées");
		return true;
		
	}
	/**
	 * 
	 */
	@Override
	public boolean modifDroit(int idUser, String newDroit) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		ConBDD connexion=new ConBDD();
		String requete="UPDATE Utilisateur SET Droit = '"+newDroit+"' WHERE ID_user = '"+idUser+"'";
		connexion.setData(requete);
		connexion.fermer();
		System.out.println("Vos droits ont bien été changés");
		return true;
	}

}
