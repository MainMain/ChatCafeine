package sources.client.service;

import java.util.ArrayList;

import sources.client.model.PaquetCom;
import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Johan
 *
 */
@RemoteServiceRelativePath("SalleService")
public interface SalleService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static SalleServiceAsync instance;
		
		public static SalleServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(SalleService.class);
			}
			return instance;
		}
	}
	
	/**
	 * Liés au tchat
	 */
	void envoiMessageFromClient(String text, String login);
	int getCptServeur();
	
	/*
	 * Liéé au paquet
	 */
	PaquetCom getNewMessage(int cptChat);
	
	/**
	 * liés à la salle
	 */
	boolean creerSalle(String nom, String theme, String description, int nbPlaceMax);
	ArrayList<Salle> getToutesSalles();
	boolean supprimerSalle(String nom);
	void envoiEvenement(String event, String login);
	String getNewEvent(int cpt);
	ArrayList<User> entre1User(User u, Salle s);
	boolean sinstaller(int x_case, int y_case, int x_last, int y_last, User userEnCours);
	boolean quitterPlace(int x_case, int y_case);
	PaquetCom getNewMatrice(int cptVueSalle);
	/**
	 * @return
	 */
	int getCptSalle();
	User[][] getMatriceUser(int cptVueSalle);
	boolean prendre1Cafe(int x_last, int y_last);
}
