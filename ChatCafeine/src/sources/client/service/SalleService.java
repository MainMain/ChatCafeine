package sources.client.service;

import java.util.ArrayList;

import sources.client.model.PaquetCom;
import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
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
	void envoiMessageFromClient(int idSalle, String text, String login);
	int getCptServeur(int idSalle);
	
	/*
	 * Liéé au paquet
	 */
	PaquetCom getNewMessage(int idSalle, int cptChat);
	
	/**
	 * liés à la salle
	 */
	boolean creerSalle(String nom, String theme, String description, int nbPlaceMax);
	ArrayList<Salle> getToutesSalles();
	boolean supprimerSalle(String nom);
	
	boolean sinstaller(int idSalle, int x_case, int y_case, int x_last,
			int y_last, boolean b, User userEnCours);
	PaquetCom getNewMatrice(int idSalle, int cptVueSalle);
	/**
	 * @return
	 */
	int getCptSalle(int idSalle);
	User[][] getMatriceUser(int idSalle, int cptVueSalle);
	boolean prendre1Cafe(int idSalle, int x_last, int y_last);
	/**
	 * @param u
	 * @param s
	 * @return
	 */
	ArrayList<User> entre1User(int idSalle, User u);
	/**
	 * @param idSalle
	 * @param u
	 */
	void sortie1User(int idSalle, User u);
	/**
	 * @param idSalle
	 * @param u
	 */
	void ejecter(int idSalle, User u);
}
