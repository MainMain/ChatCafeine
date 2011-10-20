/**
 * 
 */
package sources.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Johan
 *
 */
public interface CompteServiceAsync {

	void addCompte(String login, String mdp, String email, AsyncCallback<Void> callback);

	void delCompte(int id, AsyncCallback<Void> callback);

	void recupInfoCompte(String info, AsyncCallback<String> callback);

}
