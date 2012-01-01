/**
 * 
 */
package sources.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface ProfilServiceAsync {

	void modifMdp(int idUser, String newMdp, AsyncCallback<Boolean> callback);

	void modifInfos(int idUser, String aime, String aimePas, int age,
			String activite, AsyncCallback<Boolean> callback);

	void modifDroit(int idUser, String newDroit, AsyncCallback<Boolean> callback);

}
