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

	public void desincription(int idUser, AsyncCallback<Boolean> callback);
	public void inscription(String loginUser, String mdp, int age, String sexe, String email,
			AsyncCallback<Boolean> asyncCallback);
	void isBanniSalle(int idUser, String nomSalle,
			AsyncCallback<Boolean> callback);
	void bannirUser(int idUser, String nomSalle, int temps,
			AsyncCallback<Boolean> callback);
	void majDateLastConnexion(int idUser, AsyncCallback<Boolean> callback);
}
