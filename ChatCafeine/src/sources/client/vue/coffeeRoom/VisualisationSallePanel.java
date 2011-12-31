/**
 * 
 */
package sources.client.vue.coffeeRoom;

import java.util.ArrayList;

import sources.client.model.Salle;
import sources.client.model.User;
import sources.client.service.SalleService;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import sources.client.vue.Core;
/**
 * @author : Johan
 *
 */
public class VisualisationSallePanel extends AbsolutePanel {
	private ListUserPanel listUser;
	private VueSalle vueSalle;

	public VisualisationSallePanel(Salle salleEnCours){
		setHeight("560px");
		setWidth("900px");
		//setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		// Add elements
		listUser = new ListUserPanel();
		vueSalle = new VueSalle(listUser);
		add(listUser);
		add(vueSalle, 226, 0);
		
		// PREVENIR LE SERVEUR QUE L'UTILISATEUR VIENT D'ENTRER DANS LA SALLE
		SalleService.Util.getInstance().entre1User(Core.userEnCours.getIdSalleEnCours(),
				Core.userEnCours, new AsyncCallback<ArrayList<User>>(){
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(ArrayList<User> result) {
				listUser.maj(result);
			}
		});
		// PREVENIR LES AUTRES QUE L'UTILISATEUR VIENT D'ENTRER DANS LA SALLE
		SalleService.Util.getInstance().envoiMessageFromClient(Core.userEnCours.getIdSalleEnCours(),
				Core.userEnCours.getLogin()+" vient d'entrer dans la salle !", 
				"Message automatique", new AsyncCallback<Void>(){
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Void result) {
			}
		});
	}
	
	public ListUserPanel getListUser(){
		return listUser;
	}
	public VueSalle getVueSalle(){
		return vueSalle;
	}
}
