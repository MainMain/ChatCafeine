/**
 * 
 */
package sources.client.service;

import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ConnexionServiceAsync {
	public void authentifier(String id,String mdp, AsyncCallback<User> callback);
}
