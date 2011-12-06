/**
 * 
 */
package sources.server;

import java.util.ArrayList;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 986767859747034195L;
	private int cpt = 0;
	private String mess;
	private HashMap<String, ArrayList<User>> listeUserParSalle = new HashMap<String, ArrayList<User>>();
	
	
	@Override
	public String getNewEvent(int num) {
		while (cpt != num)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return mess;
	}

	@Override
	public void envoiEvenement(String event, String login) {
		if (event.equals("entree")) mess = login+" vient d'entrer dans la salle !";
		else if (event.equals("cafe")) mess = login+" vient de prendre un café !";
		else if (event.equals("sortie")) mess = login+" vient de quitter la salle !";
		else mess = login+" vient de faire une action incomprise !";
		cpt++;		
	}
	
	@Override
	public ArrayList<User> entre1User(User u, Salle s) {
		ArrayList<User> tmp = listeUserParSalle.get(s.getNom());
		tmp.add(u);
		listeUserParSalle.remove(s.getNom());
		listeUserParSalle.put(s.getNom(), tmp);
		return tmp;
	}
	
	@Override
	public void ouvertureSalle(Salle s) {
		listeUserParSalle.put(s.getNom(), new ArrayList<User>());
	}
}
