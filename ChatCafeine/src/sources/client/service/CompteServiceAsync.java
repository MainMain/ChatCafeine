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
public interface CompteServiceAsync {

	public void desincription(int id, AsyncCallback<Boolean> callback);
	public void inscription(String login, String mdp, int age, String sexe,
			String email, AsyncCallback<Boolean> asyncCallback);
}
