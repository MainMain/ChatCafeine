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

	void authentifier(String url, String login, String password, String id,
			String mdp, AsyncCallback<User> callback);
}
