/**
 * 
 */
package sources.client.service;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author Johan
 *
 */
public interface CompteService extends RemoteService {
	public void addCompte(String login, String mdp, String email); 
}
