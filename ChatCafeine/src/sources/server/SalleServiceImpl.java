/**
 * 
 */
package sources.server;

import java.util.ArrayList;

import sources.client.service.SalleService;
import sources.client.model.PaquetCom;
import sources.client.model.Salle;
import sources.client.model.User;

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
	private PaquetCom paquetTmp = null;
	
	@Override
	public PaquetCom getNewMessage(int cptChat) {
		while (cptChat == cptMess){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		PaquetCom pc = new PaquetCom();
		pc.setMessage(message);
		pc.setNomEmetteur(emetteur);
		System.out.println("avant envoi : "+emetteur);
		return pc;
	}
	
	@Override
	public PaquetCom getNewMatrice(int cpt) {
		System.out.println(cpt+" "+cptVueSalle);
		while (cpt == cptVueSalle){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		paquetTmp.setListeUtilisateurs(listeUtilisateurs);
		return paquetTmp;
	}
	
	
	
	
	
	
	
	
	
	/*
	 * METHODES & ATTRIBUTS LIEES AU TCHAT
	 */
	private String message= "Message automatique: <font color=\"#4D9ACD\"><em> Historique vide !</em></font>";
	private int cptMess = 0;
	private String emetteur = "Message automatique";
	
	// RECEPTION D'UN NOUVEAU MESSAGE
	@Override
	public void envoiMessageFromClient(String mess, String loginUser) {
		emetteur = loginUser;
		System.out.println(emetteur);
		if (loginUser.equals("Message automatique"))
			message= loginUser+" : <font color=\"#FF0000\"><em>"+mess+"</em></font>";
		else
			message= loginUser+" : <font color=\"#4D9ACD\"><em>"+mess+"</em></font>";
		cptMess++;
	}
	
	//RECUPERATION DU COMPTEUR DU SERVEUR
	@Override
	public int getCptServeur(){
		return cptMess;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * METHODES & ATTRIBUTS LIEES A LA SALLE
	 */
	private User[][] matriceUser = new User[10][12];
	private ArrayList<User> listeUtilisateurs = new ArrayList<User>();
	private int cptVueSalle = 0;
	
	@Override
	public ArrayList<User> entre1User(User u, Salle s) {
		System.out.println("Entrée d'un user !");
		listeUtilisateurs.add(u);
		System.out.println("Entrée User index : "+listeUtilisateurs.indexOf(u));
		PaquetCom pc = new PaquetCom(); 							// On crée un nouveau paquet...
		pc.setListeUtilisateurs(listeUtilisateurs);
		cptVueSalle++;
		paquetTmp = pc;
		return listeUtilisateurs;
	}
	
	@Override
	public void sortie1User(User u, Salle s) {
		PaquetCom pc = new PaquetCom();
		int a = -1;
		for (int i = 0; i < listeUtilisateurs.size(); i++){
			if (listeUtilisateurs.get(i).getLogin().equals(u.getLogin())){
					a = i;
					break;
			}
		}
		listeUtilisateurs.remove(a);								// Suppression de la liste des utilisateurs
		if (u.getPos_x() > -1 && u.getPox_y() > -1){				// Si utilisateur était installé
			matriceUser[u.getPos_x()][u.getPox_y()] = null;
			pc.setX_last(u.getPos_x());
			pc.setY_last(u.getPox_y());
		}
							// On crée un nouveau paquet...
		pc.setListeUtilisateurs(listeUtilisateurs);
		cptVueSalle++;
		paquetTmp = pc;
	}
	@Override
	public boolean sinstaller(int x_case, int y_case, int x_last, int y_last, User u) {
		PaquetCom pc = new PaquetCom(); 							// On crée un nouveau paquet...
		pc.setX_case(x_case);										// ... avec la position x
		pc.setY_case(y_case);										// ... la y...
		pc.setImgUser(u.getCheminAvatar());							// ... et l'image de l'user
		matriceUser[x_case][y_case] = u;

		if (x_last != -1 && y_last != -1){
			matriceUser[x_last][y_last] = null;
			pc.setX_last(x_last);
			pc.setY_last(y_last);
		}
		cptVueSalle++;
		paquetTmp = pc;
		return true;
	}
	
	@Override
	public boolean prendre1Cafe(int x_last, int y_last) {
		PaquetCom pc = new PaquetCom(); 							// On crée un nouveau paquet...
		pc.setX_last(x_last);
		pc.setY_last(y_last);
		matriceUser[x_last][y_last] = null;

		cptVueSalle++;
		paquetTmp = pc;
		return true;
	}
	
	@Override
	public User[][] getMatriceUser(int cpt) {
		return matriceUser;
	}
	
	@Override
	public int getCptSalle() {
		return cptVueSalle;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean creerSalle(String nom, String theme, String description,
			int nbPlaceMax) {
		// METHODE A CODER PAR AUDREY
		// CONTRAINTE : DEUX SALLES NE PEUVENT AVOIR LE MEME NOM
		return false;
	}
	
	@Override
	public ArrayList<Salle> getToutesSalles() {
		// METHODE A CODER PAR AUDREY
		// CETTE METHODE RENVERA UN ARRAY CONTENANT TOUTES LES SALLES
		return null;
	}
	
	@Override
	public boolean supprimerSalle(String nom) {
		// METHODE A CODER PAR AUDREY
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 	
	 * */
	@Override
	public String getNewEvent(int num) {
		while (cptMess != num)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return message;
	}
	/**
	 * 
	 */
	@Override
	public void envoiEvenement(String event, String login) {
		if (event.equals("entree")) message = login+" vient d'entrer dans la salle !";
		else if (event.equals("cafe")) message = login+" vient de prendre un café !";
		else if (event.equals("sortie")) message = login+" vient de quitter la salle !";
		else message = login+" vient de faire une action incomprise !";
		cptMess++;		
	}
	/**
	 * 
	 */

	/* (non-Javadoc)
	 * @see sources.client.service.SalleService#quitterPlace(int, int)
	 */
	@Override
	public boolean quitterPlace(int x_case, int y_case) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see sources.client.service.SalleService#prendre1Cafe(int, int)
	 */





	//private HashMap<String, ArrayList<User>> listeUtilisateur = // String = nom de la salle
	//new HashMap<String, ArrayList<User>>();
//private HashMap<String, int[][]> positionsUsers;



}
