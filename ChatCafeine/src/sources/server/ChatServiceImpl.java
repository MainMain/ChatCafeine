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
public class ChatServiceImpl  extends RemoteServiceServlet implements ChatService{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3724431790214379509L;
	private String mess;
    private int cpt = 0;

	@Override
    public void envoiMessage(String message, String loginUser) {
        mess = loginUser+" : <font color=\"#4D9ACD\"><em>"+message+"</em></font>";
        cpt++;
    }

    @Override
    public String getNewMessage(int num) { // Que se passe t-il si dans le lap des 100 ms deux messages sont envoyés?
        while (cpt != num)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return mess;
    }

    @Override
    public int getCptServeur(){
		return this.cpt;
	}
}
