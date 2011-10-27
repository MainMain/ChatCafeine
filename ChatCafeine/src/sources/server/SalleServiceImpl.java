/**
 * 
 */
package sources.server;

import sources.client.service.SalleService;

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

}
