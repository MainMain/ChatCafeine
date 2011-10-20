package sources.client.vue;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class IHM implements EntryPoint {
	public Panel panelEnCours = null;
	public static final int HEIGHT = 604;
	
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
		dockPanel.setHeight(IHM.HEIGHT+"px");
		dockPanel.setStyleName("mainPanelIHM");
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(dockPanel);
		
		

		AccueilPanel accPan = new AccueilPanel();
		panelEnCours = accPan;
		panelEnCours.setStyleName("accueilPanel");
		
		MenuBar menu = new MenuBar();
		menu.addItem("Fichier", cmd);
		menu.addItem("Application", cmd);
		menu.addItem("Aide", cmd);
		menu.addItem("A propos", cmd);
		if (panelEnCours != accPan) menu.addItem("Se déconnecter", cmd);
		menu.setWidth("99%");
		menu.addStyleName("menu");
		
		/*
		 * Ajouts à au RootPanel
		 */
		RootPanel.get().add(dockPanel);
		dockPanel.add(menu, DockPanel.NORTH);
		dockPanel.add(panelEnCours, DockPanel.CENTER);
		//RootPanel.get().add(panelEnCours);

	}

} 
