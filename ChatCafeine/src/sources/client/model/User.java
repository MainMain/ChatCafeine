package sources.client.model;

import java.io.Serializable;

import com.google.gwt.view.client.ProvidesKey;



public class User implements Comparable<User>, Serializable {

	private static final long serialVersionUID = -8860200820634715345L;
	/**
	 * The key provider that provides the unique ID ofa user.
	 */
	private String idUser;
	private int id;
	private String login, mdp, email, genre, activite, droit, dateInscription,
	dateLastConnexion, aime, aimePas, avatar;
	int age;
	private int compteurChat = 0, nbrCafe = 0;
	private int idSalleEnCours;


	private boolean installe = false; // Savoir s'il est assis dans une salle (pour recevoir messages).
	private boolean inSalle = false;


	private int pos_x = -1;
	private int pox_y = -1;
	private int nbEjections, nbBannissements;
	private boolean typeSiegeInstalle;
	/*
	 * Constructeur 
	 */
	public User(String login, int nbEjections, int nbBannissements, String droit, String dateLastConenxion){
		super();
		this.login = login;
		this.nbEjections = nbEjections;
		this.nbBannissements = nbBannissements;
		this.droit = droit;
		this.dateLastConnexion = dateLastConenxion;
	}

	public User(){
		super();
	}


	/* 
	 * Méthodes de classe
	 */


	/**
	 * The key provider that provides the unique ID of a user.
	 */
	public static final ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>() {
		public Object getKey(User item) {
			return item == null ? null : item.getIdUser();
		}
	};

	public int compareTo(User u) {
		return (u == null || u.idUser == null) ? -1	: -u.idUser.compareTo(idUser);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof User) 	
			return idUser == ((User) o).idUser;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

	/*
	 * Getters & Setters
	 */

	public int getIdInt(){
		return id;
	}
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	public void setId(String idUser) {
		this.id = Integer.parseInt(idUser);
		System.out.println("id "+id);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
		idUser = login;
	}
	
	
	public int getNbEjections() {
		return nbEjections;
	}

	public int getNbBannissements() {
		return nbBannissements;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age2) {
		this.age = age2;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getDroit() {
		return droit;
	}

	public void setDroit(String droit) {
		this.droit = droit;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getDateLastConnexion() {
		return dateLastConnexion;
	}

	public void setDateLastConnexion(String dateLastConnexion) {
		this.dateLastConnexion = dateLastConnexion;
	}
	public void sinstaller(){
		installe = true;
	}
	public void quitterLaPlace(){
		installe = false;
	}
	public boolean isInstalle(){
		return installe;
	}
	// audrey
	public void setAime(String aime) {
		this.aime = aime;
	}

	public String getAime() {
		return aime;
	}

	public void setAimePas(String aimePas) {
		this.aimePas = aimePas;
	}

	public String getAimePas() {
		return aimePas;
	}

	//fin audrey

	public int getCompteurChat(){
		return compteurChat;
	}
	public void unMsgEnvoye(){
		compteurChat-=1;
	}
	
	public boolean prendreCafe(){
			compteurChat = 25;
			nbrCafe++;
			return true;
	}
	public int getNbrCafePris(){
		return nbrCafe;
	}
	public String getCheminAvatar(){
		return avatar;
	}
	
	public void setCheminAvatar(String a){
		avatar = a;
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPox_y() {
		return pox_y;
	}

	public void setPox_y(int pox_y) {
		this.pox_y = pox_y;
	}
	
	public boolean isInSalle() {
		return inSalle;
	}
	public void entrerInSalle(){
		inSalle = true;
	}
	public void sortirFromSalle(){
		inSalle = false;
	}
	public int getIdSalleEnCours() {
		return idSalleEnCours;
	}

	public void setIdSalleEnCours(int idSalleEnCours) {
		this.idSalleEnCours = idSalleEnCours;
	}

	/**
	 * @return
	 */
	public boolean getTypeSiegeInstalle() {
		return typeSiegeInstalle;
	}

	/**
	 * @param b
	 */
	public void setTypeSiegeInstalle(boolean b) {
		typeSiegeInstalle = b;
	}
}