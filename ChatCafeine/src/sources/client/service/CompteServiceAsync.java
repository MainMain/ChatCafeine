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

	void addCompte(String login, String mdp, int age, boolean sexe,
			String email, AsyncCallback<Boolean> callback);
	
	void delCompte(int id, AsyncCallback<Boolean> callback);

	void recupInfoCompte(String info, AsyncCallback<User> callback);


}
