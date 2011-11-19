/**
 * 
 */
package sources.client.vue;

import sources.client.model.Salle;
import sources.client.model.User;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * @author : Johan
 *
 */
public class ApplicationPanel extends DockPanel{

	public ApplicationPanel() {
		creerUtilisateurDeTest();
		configPanel();
		
	}
	
	/**
	 * 
	 */
	private void creerUtilisateurDeTest() {
		User u = new User();
		u.setActivite("Etudiant");
		u.setAge("19");
		u.setDroit("administrateur");
		u.setEmail("mainmain@hotmail.fr");
		u.setGenre("Homme");
		u.setIdUser("2345");
		u.setLogin("MainMain");
		u.setMdp("azer");
		Core.userEnCours = u;
	}

	/**
	 * Méthodes de classe
	 */
	private void configPanel() {
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		//setBorderWidth(1);
		setWidth("99%");
		setHeight(Core.HEIGHT+10+"px");
		
		TabPanel tab = new TabPanel();
		tab.setStyleName("tabApplication");
		tab.setHeight("100%");
		tab.setWidth("100%");
		tab.setAnimationEnabled(true);
		String[] tabTitles = {"Coffee Room", "Mon profil", "Administration"};
	    tab.add(new CoffeeRoomPanel(), tabTitles[0]);
	    tab.add(new ProfilPanel(), tabTitles[1]);
	    if (Core.userEnCours.getDroit().equals("administrateur")) tab.add(new AdminPanel(), tabTitles[2]);
	    add(tab, DockPanel.NORTH);
	    
	    tab.selectTab(0); // Normalement défini sur 0
	}

}