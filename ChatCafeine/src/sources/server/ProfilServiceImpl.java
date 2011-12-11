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
	public boolean modifMdp(String newMdp) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean modifInfos(String aime, String aimePas, int age,
			String avatar) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean modifDroit(String newDroit) {
		// LA METHODE SERA CODEE PAR AUDREY *********************
		return false;
	}

}
