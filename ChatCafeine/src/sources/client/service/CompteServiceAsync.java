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

	public void desincription(String url, String login, String password,int idUser,
			AsyncCallback<Boolean> callback);
	public void inscription(String url, String login, String password,
			String loginUser, String mdp, int age, String sexe, String email,
			AsyncCallback<Boolean> asyncCallback);
	void isBanniSalle(String login, String nomSalle,
			AsyncCallback<Boolean> callback);
	void bannirUser(String login, String nomSalle, int temps,
			AsyncCallback<Boolean> callback);
	void majDateLastConnexion(String login, AsyncCallback<Boolean> callback);
}
