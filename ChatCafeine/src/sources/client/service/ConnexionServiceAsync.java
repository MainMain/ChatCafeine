/**
 * 
 */
package sources.client.service;

import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Johan
 *
 */
public interface ConnexionServiceAsync {

	/**
	 * @param login
	 * @param mdp
	 * @param asyncCallback
	 */
	public void authentifier(String login, String mdp,
			AsyncCallback<User> callback);
}
