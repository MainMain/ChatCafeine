package sources.client.vue;
import com.google.gwt.user.client.Command;

import com.google.gwt.user.client.Window; 
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

public class IHM implements EntryPoint {
	public Panel panelEnCours = null;
	public void onModuleLoad() {
		/*
		 * Création des commandes
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
		
		DockPanel dockPanel = new DockPanel();

		//dockPanel.setHorizontalAlignment(dockPanel.ALIGN_CENTER);
		dockPanel.setWidth("99%");
		dockPanel.setHeight("599px");
		dockPanel.setStyleName("mainPanelIHM");
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(dockPanel);
		
		MenuBar menu = new MenuBar();
		menu.addItem("Fichier", cmd);
		menu.addItem("Application", cmd);
		menu.addItem("Aide", cmd);
		menu.addItem("A propos", cmd);
		menu.addItem("Se déconnecter", cmd);
		menu.setWidth("99%");
		menu.addStyleName("menu");

		AccueilPanel accPan = new AccueilPanel();
		panelEnCours = accPan;
		panelEnCours.setStyleName("test");
		/*
		 * Ajouts à au RootPanel
		 */
		RootPanel.get().add(dockPanel);
		dockPanel.add(menu, dockPanel.NORTH);
		dockPanel.add(panelEnCours, dockPanel.CENTER);
		//RootPanel.get().add(panelEnCours);

	}

} 
