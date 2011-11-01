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
@RemoteServiceRelativePath("ConnexionService")
public interface ConnexionService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ConnexionServiceAsync instance;
		
		public static ConnexionServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(ConnexionService.class);
			}
			return instance;
		}
	}
	
	public User authentifier(String text, String text2);
}
