/**
 * 
 */
package sources.client.service;

import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ConnexionAsync {
	public void isIdentifie(String id,String mdp, AsyncCallback<User> callback);
}
