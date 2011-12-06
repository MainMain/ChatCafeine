/**
 * 
 */
package sources.client.vue;

import sources.client.model.Salle;

import com.google.gwt.user.client.ui.*;

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
		vueSalle = new VueSalle();
		add(listUser);
		add(vueSalle, 226, 0);
	}
	
	public ListUserPanel getListUser(){
		return listUser;
	}
	public VueSalle getVueSalle(){
		return vueSalle;
	}
}
