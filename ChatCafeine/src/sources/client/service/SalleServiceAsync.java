/**
 * 
 */
package sources.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface SalleServiceAsync {

	void getNewEvent(int cpt, AsyncCallback<String> callback);

	void envoiEvenement(String event, String login, AsyncCallback<Void> callback);

}
