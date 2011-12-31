/**
 * 
 */
package sources.client.vue;

import sources.client.vue.administration.AdminPanel;
import sources.client.vue.coffeeRoom.CoffeeRoomPanel;
import sources.client.vue.profil.ProfilPanel;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * @author : Johan
 *
 */
public class ApplicationPanel extends DockPanel{
	public ApplicationPanel() {
		configPanel();
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
	    tab.add(CoffeeRoomPanel.getInstance(), tabTitles[0]);
	    tab.add(new ProfilPanel(), tabTitles[1]);
	    if (Core.userEnCours.getDroit().equals("administrateur")) tab.add(new AdminPanel(), tabTitles[2]);
	    add(tab, DockPanel.NORTH);
	    
	    tab.selectTab(0); // Normalement défini sur 0
	}

}