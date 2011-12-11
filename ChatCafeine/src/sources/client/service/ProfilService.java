/**
 * 
 */
package sources.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author : Johan
 *
 */
@RemoteServiceRelativePath("ProfilService")
public interface ProfilService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ProfilServiceAsync instance;
		
		public static ProfilServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(ProfilService.class);
			}
			return instance;
		}
	}
	boolean modifMdp(String newMdp);
	boolean modifInfos(String aime, String aimePas, int age, String avatar);
	boolean modifDroit(String newDroit);
}
