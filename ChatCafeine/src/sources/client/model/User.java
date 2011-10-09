package sources.client.model;

import java.io.Serializable;

import com.google.gwt.view.client.ProvidesKey;


public class User implements Comparable<User>, Serializable {

	private static final long serialVersionUID = -8860200820634715345L;
	/**
	 * The key provider that provides the unique ID of a user.
	 */
	private String id;
	private String prenom, nom, login;


	private boolean modo;
	private boolean admin;
	/*
	 * Constructeur 
	 */
	public User() {
		super();
	}

	/* 
	 * Méthodes de classe
	 */
	public static final ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>() {
		public Object getKey(User item) {
			return item == null ? null : item.getId();
		}
	};
	
	public int compareTo(User u) {
		return (u == null || u.id == null) ? -1	: -u.id.compareTo(id);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof User) 	
			return id == ((User) o).id;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(id.substring(id.length()-3));
	}
	
	/*
	 * Getters & Setters
	 */
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public boolean isModo() {
		return modo;
	}

	public void setModo(boolean a) {
		this.modo = a;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean a) {
		this.admin = a;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getId() {
		return id;
	}
}