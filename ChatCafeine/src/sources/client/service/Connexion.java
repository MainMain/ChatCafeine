/**
 * 
 */
package sources.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import sources.client.model.User;

@RemoteServiceRelativePath("Connexion")
public interface Connexion extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ConnexionAsync instance;
		public static ConnexionAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(Connexion.class);
			}
			return instance;
		}
	}

	User isIdentifie(String id, String mdp);
}
