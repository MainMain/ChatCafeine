package sources.client.vue;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Label;

public class IHM implements EntryPoint {

	public void onModuleLoad() {
		/*
		 * Création de l'IHM
		 */
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("1024px", "2048px");
		rootPanel.setStyleName("test");
		
		AccueilPanel acc = new AccueilPanel();
		rootPanel.add(acc, 10, 10);
		
		/*
		TabLayoutPanel tabLayoutPanel = new TabLayoutPanel(2.5, Unit.EM);
		
		CoffeeRoomPanel coffePanel = new CoffeeRoomPanel();
		tabLayoutPanel.add(coffePanel, "Coffee Room", false);
		
		ProfilPanel ProfilPanel = new ProfilPanel();
		tabLayoutPanel.add(ProfilPanel, "Mon profil", false);
		rootPanel.add(tabLayoutPanel, 0, 0);

		AdminPanel AdmPanel = new AdminPanel();
		tabLayoutPanel.add(AdmPanel, "Administration", false);
		
		// Toujours à la fin : ajout de la barre
		rootPanel.add(tabLayoutPanel);
		*/
	}
}
