/**
 * 
 */
package sources.client.service;

import java.util.ArrayList;

import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface SalleServiceAsync {

	void getNewEvent(int cpt, AsyncCallback<String> callback);

	void envoiEvenement(String event, String login, AsyncCallback<Void> callback);

	void entre1User(User u, Salle s,
			AsyncCallback<ArrayList<User>> asyncCallback);

	void creerSalle(String nom, String theme, String description,
			int nbPlaceMax, AsyncCallback<Boolean> callback);

	void getToutesSalles(AsyncCallback<ArrayList<Salle>> callback);

	void supprimerSalle(String nom, AsyncCallback<Boolean> callback);

}
