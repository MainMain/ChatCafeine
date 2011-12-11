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

	void modifMdp(String newMdp, AsyncCallback<Boolean> callback);

	void modifInfos(String aime, String aimePas, int age, String avatar,
			AsyncCallback<Boolean> callback);

	void modifDroit(String newDroit, AsyncCallback<Boolean> callback);

}
