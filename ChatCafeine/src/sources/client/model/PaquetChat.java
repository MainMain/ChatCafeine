/**
 * 
 */
package sources.client.model;

import java.util.ArrayList;

/**
 * @author : Johan
 *
 */
public class PaquetChat {
	private String nomSalleDestination;
	private String message;
	private int[][] vueSalle;
	private ArrayList<User>  listeUtilisateurs;
	
	public PaquetChat(){
		
	}
	
	public boolean existNewMessage(){
		return (message != null) ? true : false;
	}
	public boolean existNewVueSalle(){
		return (vueSalle != null) ? true : false;
	}
	public boolean existeNewListeUtil(){
		return (listeUtilisateurs != null) ? true : false;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int[][] getVueSalle() {
		return vueSalle;
	}

	public void setVueSalle(int[][] vueSalle) {
		this.vueSalle = vueSalle;
	}

	public ArrayList<User>  getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(ArrayList<User>  listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}
	
	
}
