/**
 * 
 */
package sources.client.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.client.ui.Image;

/**
 * @author : Johan
 *
 */
public class PaquetCom implements Comparable<PaquetCom>, Serializable{
	private long idPaquet;
	private static long cpt = 0;
	private int idSalleDestination;
	
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
	public boolean siege_last;
	
	private String userAEjecter = null;
	

	


	public PaquetCom(){
		idPaquet = cpt++;
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
	
	public int getIdSalleDestination() {
		return idSalleDestination;
	}

	public void setIdSalleDestination(int idSalleDestination) {
		this.idSalleDestination = idSalleDestination;
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
	public long getIdPaquet(){
		return idPaquet;
	}
	
	public void setSiege_last(boolean b){
		siege_last = b;
	}
	public boolean getSiege_last(){
		return siege_last;
	}
	
	public String getUserAEjecter() {
		return userAEjecter;
	}

	public void setUserAEjecter(String userAEjecter) {
		this.userAEjecter = userAEjecter;
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
