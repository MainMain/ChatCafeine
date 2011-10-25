/**
 * 
 */
package sources.client.service;

import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ConnexionServiceAsync {
	public void authentifier(String id,String mdp, AsyncCallback<User> callback);

	public void getNewMessage(int cpt, AsyncCallback<String> asyncCallback);

	public void envoiMessage(String text, String login,
			AsyncCallback<Void> asyncCallback);
}
