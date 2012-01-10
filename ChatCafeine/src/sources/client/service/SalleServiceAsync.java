/**
 * 
 */
package sources.client.service;

import java.util.ArrayList;

import sources.client.model.PaquetCom;
import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author : Johan
 *
 */
public interface SalleServiceAsync {

	void envoiMessageFromClient(int idSalle, String text, String login,
			AsyncCallback<Void> callback);
	void getCptServeur(int idSalle, AsyncCallback<Integer> callback);
	
	void getNewMessage(int idSalle, int cptChat,
			AsyncCallback<PaquetCom> callback);
	void entre1User(int idSalle, User u,
			AsyncCallback<ArrayList<User>> asyncCallback);

	void creerSalle(String nom, String theme, String description,
			int nbPlaceMax, AsyncCallback<Boolean> callback);

	void getToutesSalles(AsyncCallback<ArrayList<Salle>> asyncCallback);

	void supprimerSalle(String nom, AsyncCallback<Boolean> callback);

	void sinstaller(int idSalle, int x_case, int y_case, int x_last,
			int y_last, boolean b, User userEnCours, AsyncCallback<Boolean> callback);
	void getNewMatrice(int idSalle, int cptVueSalle,
			AsyncCallback<PaquetCom> asyncCallback);
	void getCptSalle(int idSalle, AsyncCallback<Integer> callback);
	void getMatriceUser(int idSalle, int cptVueSalle,
			AsyncCallback<User[][]> asyncCallback);
	void sortie1User(int idSalle, User u, AsyncCallback<Void> callback);
	void prendre1Cafe(int idSalle, int x_last, int y_last,
			AsyncCallback<Boolean> callback);
	void ejecter(int idSalle, User u, AsyncCallback<Void> callback);


}
