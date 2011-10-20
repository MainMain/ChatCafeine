package sources.server;

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
	public void addCompte(String login, String mdp, String email) {
		
	}
	/* (non-Javadoc)
	 * @see sources.client.service.CompteService#delCompte(int)
	 */
	
	@Override
	public void delCompte(int id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see sources.client.service.CompteService#recupInfoCompte(java.lang.String)
	 */
	@Override
	public String recupInfoCompte(String info) {
		if (info.equals("nom"))
				return "nom";
		return null;
	}
}
