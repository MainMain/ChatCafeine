/**
 * 
 */
package sources.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import sources.client.model.User;
import sources.client.service.Connexion;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConnexionImpl extends RemoteServiceServlet implements Connexion {

	@Override
	public User isIdentifie(String id, String mdp) {
		User user = new User();
		String requete="SELECT id,mdp,nom,prenom,admin FROM User WHERE id LIKE '"+id+"' AND mdp LIKE '"+mdp+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				User p=new User();
				p.setId(resultat.getString("id"));
				p.setNom(resultat.getString("nom"));
				p.setPrenom(resultat.getString("prenom"));
				p.setAdmin(resultat.getBoolean("admin"));
				connexion.fermer();
				return p;//String[]{"OK",resultat.getString("id")};
			}
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
