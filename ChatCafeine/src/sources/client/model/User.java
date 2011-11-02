package sources.client.model;

import java.io.Serializable;

import com.google.gwt.view.client.ProvidesKey;



public class User implements Comparable<User>, Serializable {

	private static final long serialVersionUID = -8860200820634715345L;
	/**
	 * The key provider that provides the unique ID of a user.
	 */
	private String idUser;
	private String login, mdp, email, genre, age, activite, droit, dateInscription, dateLastConnexion, aime, aimePas;

	/*
	 * Constructeur 
	 */
	public User() {
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
		return Integer.parseInt(idUser.substring(idUser.length()-3));
	}

	/*
	 * Getters & Setters
	 */

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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



}