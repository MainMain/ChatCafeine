/**
 * 
 */
package sources.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface ChatServiceAsync {
	void envoiMessage(String text, String login, AsyncCallback<Void> callback);
	void getCptServeur(AsyncCallback<Integer> callback);
	void getNewMessage(int cpt, AsyncCallback<String> callback);
}
