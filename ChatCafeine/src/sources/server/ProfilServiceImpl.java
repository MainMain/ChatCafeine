/**
 * 
 */
package sources.server;

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
		String requete="UPDATE utilisateur SET Pass = "+newMdp+" WHERE ID_user = "+idUser;
		System.out.println("TEST : requete = "+requete);
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		else{
			Window.alert("Votre mot de passe à bien été changé");
			return true;
		}
		
	}
	/**
	 * 
	 */
	@Override
	public boolean modifInfos(int idUser, String aime, String aimePas, int age,
			String avatar) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		
		
		ConBDD connexion=new ConBDD();
		String requete="UPDATE utilisateur SET Aime = "+aime+" AND AimePas = "+aimePas+" AND Age = "+age+" AND Avatar = "+avatar+" WHERE ID_user = "+idUser;
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		else{
			Window.alert("Vos informations ont été changées");
			return true;
		}
	}
	/**
	 * 
	 */
	@Override
	public boolean modifDroit(int idUser, String newDroit) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		ConBDD connexion=new ConBDD();
		String requete="UPDATE utilisateur SET Droit = "+newDroit+" WHERE ID_user = "+idUser;
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null || resultat.equals("Error"))
			return false;
		else {
			Window.alert("Vos droits ont bien été changés");
			return true;
		}
	}

}
