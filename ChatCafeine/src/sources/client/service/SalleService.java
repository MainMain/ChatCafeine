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
@RemoteServiceRelativePath("SalleService")
public interface SalleService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static SalleServiceAsync instance;
		
		public static SalleServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(SalleService.class);
			}
			return instance;
		}
	}
	void envoiEvenement(String event, String login);
	String getNewEvent(int cpt);
}