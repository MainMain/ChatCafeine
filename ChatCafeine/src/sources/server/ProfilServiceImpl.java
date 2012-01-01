/**
 * 
 */
package sources.server;

import sources.client.service.ProfilService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class ProfilServiceImpl extends RemoteServiceServlet implements ProfilService{
	private static final long serialVersionUID = 71298666683531087L;
	
	@Override
	public boolean modifMdp(int idUser, String newMdp) {
		System.out.println("eeeeeeeeshjhfjdshodsoj");
		// LA METHODE SERA CODEE PAR AUDREY *********************
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean modifInfos(int idUser, String aime, String aimePas, int age,
			String activite) {
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeertoklefdkjshjhfjdshodsoj");
		// Controle que les infos ne soient pas vides et insére que ceux complétés. (et que age > 10 et < 99)
		// LA METHODE SERA CODEE PAR AUDREY *********************
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean modifDroit(int idUser, String newDroit) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		return false;
	}

}
