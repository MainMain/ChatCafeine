package sources.client.vue;

import sources.client.model.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.dom.client.Style.Unit;

public class Core implements EntryPoint {
	public static final int HEIGHT = 604;
	public static boolean connecte = false;
	public static User userEnCours = null;
	private static AccueilPanel accPan = new AccueilPanel();
	private static ApplicationPanel appPan = new ApplicationPanel(2.5, Unit.PX);
	private static DockPanel dockPanel = new DockPanel();
	
	public void onModuleLoad() {
		Window.setMargin("0px");
		appPan.setStyleName("applicationPanel");
		accPan.setStyleName("accueilPanel");
		
		/*
		 * Création du panel père (fond gris foncé)
		 */
		dockPanel.setWidth("99%");
		dockPanel.setHeight(Core.HEIGHT+"px");
		dockPanel.setStyleName("mainPanelIHM");
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(dockPanel);
		
		/*
		 * Ajouts à au RootPanel
		 */
		RootPanel.get().add(dockPanel);
		modeDeconnecte(); // Normalement on es en mode deconnecté mais pour développer la suite de l'application il est mieux de rester en mode co. 
	}
		public static MenuBar createMenuBar(){
			Command cmd = new Command() {
				public void execute() {	Window.alert("You selected a menu item!"); }
			};
			MenuBar menu = new MenuBar();
			menu.addItem("Fichier", cmd);
			menu.addItem("Application", cmd);
			menu.addItem("Aide", cmd); // On peut mettre que F5 = rafraichissement de tout l'appli -> A ne pas utiliser
			menu.addItem("A propos", cmd);
			if (connecte) menu.addItem("Se déconnecter", cmd);
			menu.setWidth("99%");
			menu.addStyleName("menu");
			
			return menu;
			
		}
		public static void modeConnecte(){
			dockPanel.remove(accPan);
			dockPanel.clear();
			dockPanel.add(createMenuBar(), DockPanel.NORTH);
			dockPanel.add(appPan, DockPanel.CENTER);
			connecte = true;
	}
		
		public static void modeDeconnecte(){
			dockPanel.remove(appPan);
			dockPanel.clear();
			dockPanel.add(createMenuBar(), DockPanel.NORTH);
			dockPanel.add(accPan, DockPanel.CENTER);
			connecte = false;
	}

} 
