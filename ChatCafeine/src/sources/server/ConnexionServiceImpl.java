/**
 * 
 */
package sources.server;

//import java.sql.ResultSet;
//import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.SQLException;

import sources.client.model.User;
import sources.client.service.ConnexionService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConnexionServiceImpl extends RemoteServiceServlet implements ConnexionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1942157650673416454L;
    private String mess;
    private int cpt = 0;
    
	@Override
	public User authentifier(String login, String mdp) {
		String requete="SELECT LOGIN,MDP FROM UTILISATEUR WHERE LOGIN LIKE '"+login+"' AND MDP LIKE '"+mdp+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null || resultat.equals("Error"))
			return null;
		else {
			try {
				while (resultat.next()){
					User p=new User();
					p.setIdUser(resultat.getString("ID_UTILISATEUR"));
					p.setLogin(resultat.getString("LOGIN"));
					p.setMdp(resultat.getString("MDP"));
					p.setDroit(resultat.getString("DROIT"));
					connexion.fermer();
					return p; //String[]{"OK",resultat.getString("id")};
				}
				return null;
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@Override
    public void envoiMessage(String message, String loginUser) {
        mess = loginUser+" : "+message;
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
	public User inscription(String login, String mdp, int age, String sexe,
			String email) {
		try {
		String requete="INSERT INTO `db1691085-main`.`UTILISATEUR` (`ID_UTILISATEUR`, `ID_SALLE`, `LOGIN`, `MDP`, `EMAIL`, `GENRE`, `AGE`, `ACTIVITE`, `AIME`, `AIME_PAS`, `DROIT`, `DATE_INSCRIPTION`, `DATE_LAST_CONNEXION`) VALUES ('1000', NULL, '"+login+"'"+", '"+mdp+"'"+", '"+email+"'"+", '"+sexe+"'"+", '"+age+"'"+", NULL, NULL, NULL, 'utilisateur', 'truc qui affiche la date', 'truc qui affiche la date');";
		ConBDD connexion=new ConBDD();
		String resultat=connexion.setData(requete);
		if (resultat == null || resultat.equals("Error") ) {
			return null;
		}
		connexion.fermer();
		}
		catch (Exception e){
			return null;
		}
		return null;
	}
}
