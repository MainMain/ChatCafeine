/**
 * 
 */
package sources.client.service;

import java.util.ArrayList;

import sources.client.model.PaquetCom;
import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface SalleServiceAsync {

	/**
	 * Liés au tchat
	 */
	void envoiMessageFromClient(String text, String login, AsyncCallback<Void> callback);
	void getCptServeur(AsyncCallback<Integer> callback);
	
	/**
	 * 
	 */
	void getNewMessage(int cptChat, AsyncCallback<PaquetCom> callback);
	/**
	 * 
	 */
	void getNewEvent(int cpt, AsyncCallback<String> callback);

	void envoiEvenement(String event, String login, AsyncCallback<Void> callback);

	void entre1User(User u, Salle s,
			AsyncCallback<ArrayList<User>> asyncCallback);

	void creerSalle(String nom, String theme, String description,
			int nbPlaceMax, AsyncCallback<Boolean> callback);

	void getToutesSalles(AsyncCallback<ArrayList<Salle>> callback);

	void supprimerSalle(String nom, AsyncCallback<Boolean> callback);

	void sinstaller(int x_case, int y_case, int x_last, int y_last,
			User userEnCours, AsyncCallback<Boolean> callback);
	/**
	 * @param cptVueSalle
	 * @param asyncCallback
	 */
	void getNewMatrice(int cptVueSalle, AsyncCallback<PaquetCom> asyncCallback);
	void getCptSalle(AsyncCallback<Integer> callback);
	/**
	 * @param cptVueSalle
	 * @param asyncCallback
	 */
	void getMatriceUser(int cptVueSalle, AsyncCallback<User[][]> asyncCallback);
	void quitterPlace(int x_case, int y_case, AsyncCallback<Boolean> callback);
	/**
	 * @param x_last
	 * @param y_last
	 * @param asyncCallback
	 */
	void prendre1Cafe(int x_last, int y_last,
			AsyncCallback<Boolean> asyncCallback);
	void sortie1User(User u, Salle s, AsyncCallback<Void> callback);


}
