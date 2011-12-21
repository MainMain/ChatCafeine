/**
 * 
 */
package sources.client.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.ui.Image;

/**
 * @author : Johan
 *
 */
public class PaquetCom implements Comparable<PaquetCom>, Serializable{
	private String nomSalleDestination;
	
	private String message;
	private int cptMessage;
	private String nomEmetteur;

	private int[][] vueSalle;

	private int x_case = -1;
	private int y_case = -1;
	private int x_last = -1;
	private int y_last = -1;
	private String imgUser = null;
	
	private ArrayList<User> listeUtilisateurs = null;
	
	public PaquetCom(){
		
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
	
	public String getNomSalleDestination() {
		return nomSalleDestination;
	}

	public void setNomSalleDestination(String nomSalleDestination) {
		this.nomSalleDestination = nomSalleDestination;
	}

	public int getCptMessage() {
		return cptMessage;
	}

	public void setCptMessage(int cptMessage) {
		this.cptMessage = cptMessage;
	}

	public int getX_case() {
		return x_case;
	}

	public void setX_case(int x_case) {
		this.x_case = x_case;
	}

	public int getY_case() {
		return y_case;
	}

	public void setY_case(int y_case) {
		this.y_case = y_case;
	}

	public String getImgUser() {
		return imgUser;
	}

	public void setImgUser(String imgUser) {
		this.imgUser = imgUser;
	}

	
	public int getX_last() {
		return x_last;
	}

	public void setX_last(int x_last) {
		this.x_last = x_last;
	}

	public int getY_last() {
		return y_last;
	}

	public void setY_last(int y_last) {
		this.y_last = y_last;
	}
	public String getNomEmetteur() {
		return nomEmetteur;
	}

	public void setNomEmetteur(String nomEmetteur) {
		this.nomEmetteur = nomEmetteur;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PaquetCom o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
