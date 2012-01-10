/**
 * 
 */
package sources.server;

import java.sql.ResultSet;
import java.util.ArrayList;

import sources.client.service.SalleService;
import sources.client.model.PaquetCom;
import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author : Johan
 *
 */
public class SalleServiceImpl extends RemoteServiceServlet implements SalleService{
	private static final long serialVersionUID = 986767859747034195L;


	/*
	 * METHODE LIEE AUX PAQUETS
	 */
	private PaquetCom paquetTmpMessage = null;
	private PaquetCom paquetTmpVue = null;

	@Override
	public PaquetCom getNewMessage(int idSalle, int cptChat) {
		//System.out.println("[Serveur] : Cpt salle : "+idSalle+" = " +cptMess.get(idSalle)+ " Cpt recu = "+cptChat);
		if (idSalle == -1) return null;					// Sécurité
		if (!(cptChat == cptMess.get(idSalle))){
			System.out.println("[Serveur] : Envoi du paquet n° "+paquetTmpMessage.getIdPaquet());
			return paquetTmpMessage;
		}
		else{								// Si il est a jour, one lui envoi un paquet null afin qu'il redemande une maj
			//PaquetCom pc = new PaquetCom();
			//pc.setIdSalleDestination(-1);
			//return pc;
			return null;
		}
		/*while (cptChat == cptMess.get(idSalle)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[Serveur] : Envoi du paquet n° "+paquetTmpMessage.getIdPaquet());
		return paquetTmpMessage;*/

	}

	@Override
	public PaquetCom getNewMatrice(int idSalle, int cpt) {
		System.out.println("[Serveur] : Demande de maj de la vue, salle "+idSalle);
		if (idSalle == -1) return null;					// Sécurité
		while (cpt == cptVueSalle.get(idSalle)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		paquetTmpVue.setListeUtilisateurs(listeUtilisateurs.get(idSalle));
		return paquetTmpVue;
	}









	/*
	 * METHODES & ATTRIBUTS LIEES AU TCHAT
	 */
	private HashMap<Integer, String> message= new HashMap<Integer, String>();
	private HashMap<Integer, Integer> cptMess = new HashMap<Integer, Integer>();
	private String emetteur = "Message automatique";

	// RECEPTION D'UN NOUVEAU MESSAGE
	@Override
	public void envoiMessageFromClient(int idSalle, String mess, String loginUser) {
		emetteur = loginUser;
		System.out.println("[Serveur] : Mesage recu ! emetteur : "+emetteur);
		if (loginUser.equals("Message automatique"))
			message.put(idSalle, loginUser+" : <font color=\"#FF0000\"><em>"+mess+"</em></font>");
		else
			message.put(idSalle, loginUser+" : <font color=\"#4D9ACD\"><em>"+mess+"</em></font>");
		PaquetCom pc = new PaquetCom();					// Création du paquet
		pc.setIdSalleDestination(idSalle);				// ... Insertion id salle dest
		pc.setNomEmetteur(loginUser);					// ... Insertion nom emmetteur
		pc.setMessage(message.get(idSalle));			// ... Insertion message
		System.out.println("[Serveur] : Message inséré dans le paquet : "+(message.get(idSalle)));
		paquetTmpMessage = pc;									// Mise dans paquet Tmp (donc prêt à envoyer)
		System.out.println("[Serveur] : Nouveau paquet prêt à être expédié ! id du paquet : "+pc.getIdPaquet()+"("+paquetTmpMessage.getIdPaquet()+")");
		System.out.println("[Serveur ] : cptMess = "+cptMess.get(idSalle));
		cptMess.put(idSalle, cptMess.get(idSalle)+1);	// Incrémente le compteur pour envoi
	}

	//RECUPERATION DU COMPTEUR DU SERVEUR
	@Override
	public int getCptServeur(int idSalle){
		System.out.println("[Serveur] : Gestion de message pour la salle "+idSalle+" définie ? "+cptMess.containsKey(idSalle));
		if (cptMess.containsKey(idSalle))				// Si pas de clé : compteur existe pas, donc il faut le créer (else)
			return cptMess.get(idSalle);
		else{
			cptMess.put(idSalle, 0);
			System.out.println("[Serveur] : Nouvelle entrée de gestion de message pour la salle "+idSalle);
			return 0;
		}
	}










	/*
	 * METHODES & ATTRIBUTS LIEES A LA SALLE
	 */
	//********************************************************* METTRE DES HASHMAP !!!
	private HashMap<Integer,User[][]> matriceUser = new HashMap<Integer, User[][]>();

	private HashMap<Integer, ArrayList<User>> listeUtilisateurs = 
			new HashMap<Integer,  ArrayList<User>>();
	private HashMap<Integer, Integer> cptVueSalle = new HashMap<Integer, Integer>();

	@Override
	public ArrayList<User> entre1User(int idSalle, User u) {
		System.out.println("[Serveur] Entrée de "+u.getLogin()+" dans la salle "+idSalle);
		listeUtilisateurs.get(idSalle).add(u);
		System.out.println("[Serveur]Index de "+u.getLogin()+" dans la liste : "+listeUtilisateurs.get(idSalle).indexOf(u));
		PaquetCom pc = new PaquetCom(); 							// On crée un nouveau paquet...
		pc.setListeUtilisateurs(listeUtilisateurs.get(idSalle));
		cptVueSalle.put(idSalle, cptVueSalle.get(idSalle)+1);
		paquetTmpVue = pc;
		return listeUtilisateurs.get(idSalle);
	}

	@Override
	public void sortie1User(int idSalle, User u) {
		System.out.println("[Serveur] : "+u.getLogin()+" est sorti de la salle "+idSalle);
		PaquetCom pc = new PaquetCom();
		int a = -1;
		for (int i = 0; i < listeUtilisateurs.size(); i++){ 		
			if (listeUtilisateurs.get(idSalle).get(i).getLogin().equals(u.getLogin())){
				System.out.println("[Serveur] : "+u.getLogin()+" sorti de la liste des users !");
				a = i;
				break;
			}
		}
		if (a < -1) listeUtilisateurs.remove(a);					// Suppression de la liste des utilisateurs			
		if (u.getPos_x() > -1 && u.getPox_y() > -1){				// Si utilisateur était installé
			matriceUser.get(idSalle)[u.getPos_x()][u.getPox_y()] = null;
			pc.setX_last(u.getPos_x());
			pc.setY_last(u.getPox_y());
		}
		// On crée un nouveau paquet...
		pc.setListeUtilisateurs(listeUtilisateurs.get(idSalle));
		pc.setIdSalleDestination(idSalle);
		paquetTmpVue = pc;
		cptVueSalle.put(idSalle, cptVueSalle.get(idSalle)+1);
	}
	@Override
	public boolean sinstaller(int idSalle, int x_case, int y_case, int x_last, int y_last, User u) {
		PaquetCom pc = new PaquetCom(); 							// On crée un nouveau paquet...
		pc.setX_case(x_case);										// ... avec la position x
		pc.setY_case(y_case);										// ... la y...
		pc.setImgUser(u.getCheminAvatar());							// ... et l'image de l'user
		matriceUser.get(idSalle)[x_case][y_case] = u;

		if (x_last != -1 && y_last != -1){
			matriceUser.get(idSalle)[x_last][y_last] = null;
			pc.setX_last(x_last);
			pc.setY_last(y_last);
		}
		cptVueSalle.put(idSalle, cptVueSalle.get(idSalle)+1);
		paquetTmpVue = pc;
		paquetTmpVue.setIdSalleDestination(idSalle);
		return true;
	}

	@Override
	public boolean prendre1Cafe(int idSalle, int x_last, int y_last) {
		PaquetCom pc = new PaquetCom(); 							// On crée un nouveau paquet...
		pc.setX_last(x_last);
		pc.setY_last(y_last);
		matriceUser.get(idSalle)[x_last][y_last] = null;

		cptVueSalle.put(idSalle, cptVueSalle.get(idSalle)+1);
		paquetTmpVue = pc;
		paquetTmpVue.setIdSalleDestination(idSalle);
		return true;
	}

	@Override
	public User[][] getMatriceUser(int idSalle, int cpt) {
		return matriceUser.get(idSalle);
	}

	@Override
	public int getCptSalle(int idSalle) {
		System.out.println("[Serveur] : Gestion de vue pour la salle "+idSalle+" définie ? "+cptMess.containsKey(idSalle));
		if (cptVueSalle.containsKey(idSalle))				// Si pas de clé : compteur existe pas, donc il faut le créer (else)
			return cptVueSalle.get(idSalle);
		else{												// On initialise donc les structures de données pour la salle
			System.out.println("[Serveur] : Nouvelle entrée de gestion de vue pour la salle "+idSalle);
			cptVueSalle.put(idSalle, 0);
			listeUtilisateurs.put(idSalle, new ArrayList<User>());
			matriceUser.put(idSalle, new User[10][12]);
			return 0;
		}
	}















	@Override
	public boolean creerSalle(String nom, String theme, String description,
			int nbPlaceMax) {
		// METHODE A CODER PAR AUDREY
		// CONTRAINTE : DEUX SALLES NE PEUVENT AVOIR LE MEME NOM
		String requete= "SELECT * FROM Salle Where Nom Like '"+nom+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null){
			connexion.fermer();
			return false;
		}else{
			String requete2="INSERT INTO Salle (ID_salle,Nom,Theme,Description,NbPlaceMax) VALUES (null,'"+nom+"','"+theme+"','"+description+"','"+nbPlaceMax+"');";
			String resultat2=connexion.setData(requete2);
			connexion.fermer();
			if (resultat2==null || resultat2.equals("Error"))
				return false;
			if (resultat2.equals("OK"))
				return true;
			else return false;
		}
		//return false;
	}

	@Override
	public ArrayList<Salle> getToutesSalles() {
		// par audrey
		ConBDD connexion=new ConBDD();
		ArrayList<Salle> a = new ArrayList<Salle> ();
		try{
			String requete = "SELECT * FROM Salle";
			ResultSet result = connexion.getData(requete);
			while (result.next()){
				a.add(new Salle(result.getInt("ID_salle"), result.getString("Nom"), result.getString("Theme"),result.getString("Description"), result.getInt("NbPlaceMax")));
			}
		}catch (Exception e){
			System.out.println("Erreur lors de la récupération des salles !\n->"+e.getMessage());
		}
		return a;
	}

	@Override
	public boolean supprimerSalle(String nom) {
		//par audrey.
		ConBDD connexion=new ConBDD();
		String requete="DELETE FROM Salle WHERE Nom LIKE '"+nom+"'";
		String resultat=connexion.setData(requete);
		System.out.println(resultat);
		
		connexion.fermer();
		if (resultat.equals("Error"))
			return true;
		else {
			System.out.println(resultat);
			return false;
		}

	}





















	//private HashMap<String, ArrayList<User>> listeUtilisateur = // String = nom de la salle
	//new HashMap<String, ArrayList<User>>();
	//private HashMap<String, int[][]> positionsUsers;



}
