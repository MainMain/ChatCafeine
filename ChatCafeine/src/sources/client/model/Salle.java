/**
 * 
 */
package sources.client.model;

import java.io.Serializable;

/**
 * @author : Johan
 *
 */
public class Salle implements Comparable<Salle>, Serializable{
	private int idSalle;
	private String nom = "Aucun";
	private String theme = "Aucun";
	private String description;
	private int nbrPlaceMax = 40;
	private int nbrPlaceOqp = 0;

	public Salle(int idSalle, String nom, String theme, String description, int nbrPlaceMax) {
		super();
		this.idSalle = idSalle;
		this.nom = nom;
		this.theme = theme;
		this.description = description;
		this.nbrPlaceMax = nbrPlaceMax;
	}
	
	public Salle(){
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbrPlaceMax() {
		return nbrPlaceMax;
	}

	public void setNbrPlaceMax(int nbrPlaceMax) {
		this.nbrPlaceMax = nbrPlaceMax;
	}

	public int getNbrPlaceOqp() {
		return nbrPlaceOqp;
	}

	public void setNbrPlaceOqp(int nbrPlaceOqp) {
		this.nbrPlaceOqp = nbrPlaceOqp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Salle o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
	
	
	
}
