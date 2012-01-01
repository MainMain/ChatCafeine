/**
 * 
 */
package sources.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Johan
 *
 */
@RemoteServiceRelativePath("CompteService")
public interface CompteService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static CompteServiceAsync instance;
		
		public static CompteServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(CompteService.class);
			}
			return instance;
		}
	}
	boolean desincription(int idUser);
	boolean inscription(String loginUser, String mdp, int age, String sexe, String email);
	boolean majDateLastConnexion(int idUser);
	boolean isBanniSalle(int idUser, String nomSalle);
	boolean bannirUser(int idUser, String nomSalle, int temps);
}
