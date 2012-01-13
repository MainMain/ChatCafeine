/**
 * 
 */
package sources.client.service;

import java.util.ArrayList;

import sources.client.model.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author : Johan
 *
 */
@RemoteServiceRelativePath("AdminService")
public interface AdminService extends RemoteService{
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static AdminServiceAsync instance;
		
		public static AdminServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(AdminService.class);
			}
			return instance;
		}
	}

	ArrayList<User> getAllUsers();
	boolean deleteUser(String login);
	boolean majUser(String login, String droit);
	
}
