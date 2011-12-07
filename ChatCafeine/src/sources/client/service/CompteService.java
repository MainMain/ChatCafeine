/**
 * 
 */
package sources.client.service;

import sources.client.model.User;

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
	boolean desincription(String url, String login, String password, int idUser);
	boolean inscription(String url, String login, String password,
			String loginUser, String mdp, int age, String sexe, String email);
}
