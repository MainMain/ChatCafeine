/**
 * 
 */
package sources.server;

import sources.client.service.ChatService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class ChatServiceImpl extends RemoteServiceServlet implements ChatService{


	@Override
	public String getNewMessage(int cpt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void envoiMessage(String text, String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCptServeur() {
		// TODO Auto-generated method stub
		return 0;
	}
}
