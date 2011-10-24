package sources.server;

import sources.client.model.User;
import sources.client.service.CompteService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class CompteServiceImpl extends RemoteServiceServlet implements CompteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3597453029675338488L;

	@Override
	public boolean addCompte(String login, String mdp, int age, boolean sexe,
			String email) {
// A CODER ! 		
/*
 *  Note : si sexe = 1, alors sexe = homme
 *  10 =< age =< 99 (controle déja fait, tu n'as plus qu'a traiter les données
 */
		return true;
	}
	
	@Override
	public boolean delCompte(int id) {
		return false;
	}
	
	@Override
	public User recupInfoCompte(String info) {
		return null;
	}

}
