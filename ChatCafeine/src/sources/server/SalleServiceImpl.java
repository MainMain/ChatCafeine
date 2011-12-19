/**
 * 
 */
package sources.server;

import java.util.ArrayList;

import sources.client.service.ChatService;
import sources.client.service.SalleService;
import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class SalleServiceImpl extends RemoteServiceServlet implements SalleService{
	private static final long serialVersionUID = 986767859747034195L;
	
	private String message;
	private int[][] vueSalle;
	private ArrayList<User> listeUtilisateurs;
	/*
	 * Idée :
	 * Le servlet contient le dernier messageage + liste utilisateur + matrice des positions.
	 * Le rpc fait passer un nouveau évnt
	 */
	//private HashMap<String, ArrayList<User>> listeUtilisateur = // String = nom de la salle
		//new HashMap<String, ArrayList<User>>();
	//private HashMap<String, int[][]> positionsUsers;
	
	/**
	 * Méthodes liées au tchat
	 */
	private String mess = "Message automatique: <font color=\"#4D9ACD\"><em> Historique vide !</em></font>";
	private int cpt = 0;
	@Override
	public void envoiMessage(String message, String loginUser) {
		if (loginUser.equals("Message automatique"))
			mess = loginUser+" : <font color=\"#FF0000\"><em>"+message+"</em></font>";
		else
			mess = loginUser+" : <font color=\"#4D9ACD\"><em>"+message+"</em></font>";
		cpt++;
	}
	@Override
	public String getNewMessage(int cptUser) { // Que se passe t-il si dans le lap des 100 ms deux messages sont envoyés?
		while (cptUser == cpt){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return mess;
	}
	@Override
	public int getCptServeur(){
		return this.cpt;
	}
	
	/**
	 * Méthodes liées à la salle
	 */
	@Override
	public ArrayList<User> entre1User(User u, Salle s) {
		//ArrayList<User> tmp = listeUserParSalle.get(s.getNom());
		//tmp.add(u);
		//listeUserParSalle.remove(s.getNom());
		//listeUserParSalle.put(s.getNom(), tmp);
		//return tmp;
		return null;
	}
	/**
	 * 
	 */
	public boolean majListeSalles(Salle s, boolean ajout) {
		//if (ajout)
		//	listeUserParSalle.put(s.getNom(), new ArrayList<User>());
		//else
		//	listeUserParSalle.remove(s.getNom());
		//return true;
		return false;
	}

	@Override
	public boolean creerSalle(String nom, String theme, String description,
			int nbPlaceMax) {
		// METHODE A CODER PAR AUDREY
		// CONTRAINTE : DEUX SALLES NE PEUVENT AVOIR LE MEME NOM
		return false;
	}
	
	@Override
	public ArrayList<Salle> getToutesSalles() {
		// METHODE A CODER PAR AUDREY
		// CETTE METHODE RENVERA UN ARRAY CONTENANT TOUTES LES SALLES
		return null;
	}
	
	@Override
	public boolean supprimerSalle(String nom) {
		// METHODE A CODER PAR AUDREY
		return false;
	}
	
	/**
	 * 	
	 * */
	@Override
	public String getNewEvent(int num) {
		while (cpt != num)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return message;
	}
	/**
	 * 
	 */
	@Override
	public void envoiEvenement(String event, String login) {
		if (event.equals("entree")) message = login+" vient d'entrer dans la salle !";
		else if (event.equals("cafe")) message = login+" vient de prendre un café !";
		else if (event.equals("sortie")) message = login+" vient de quitter la salle !";
		else message = login+" vient de faire une action incomprise !";
		cpt++;		
	}
	/**
	 * 
	 */
	/* (non-Javadoc)
	 * @see sources.client.service.SalleService#modifierPosition(int, int)
	 */
	@Override
	public boolean modifierPosition(int x_case, int y_case) {
		// TODO Auto-generated method stub
		return false;
	}

}
