package sources.client.vue;
import com.google.gwt.user.client.Command;

import com.google.gwt.user.client.Window; 
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

public class IHM implements EntryPoint {
	public Panel panelEnCours = null;
	public void onModuleLoad() {
		/*
		 * Création des  commande s
		 */
		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected a menu item!");
			}
		};

		/*
		 * Création de l'IHM
		 */
		Window.setMargin("0px");

		MenuBar menu = new MenuBar();
		menu.addItem("Fichier", cmd);
		menu.addItem("Application", cmd);
		menu.addItem("Aide", cmd);
		menu.addItem("A propos", cmd);
		menu.addItem("Se déconnecter", cmd);

		AccueilPanel accPan = new AccueilPanel();
		panelEnCours = accPan;

		/*
		 * Ajouts à au RootPanel
		 */
		RootPanel.get().add(menu);
		RootPanel.get().add(panelEnCours);

	}

} 
