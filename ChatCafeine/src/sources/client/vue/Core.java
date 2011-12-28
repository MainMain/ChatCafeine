package sources.client.vue;


import sources.client.GreetingService;
import sources.client.GreetingServiceAsync;
import sources.client.model.User;
import sources.client.service.SalleService;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;



/**
 * @author : Johan
 *
 */
public class Core implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";


	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = 
		GWT.create(GreetingService.class);


	public static final int HEIGHT = 604;
	public static User userEnCours = null;
	private static AccueilPanel accPan = new AccueilPanel();
	private static ApplicationPanel appPan;
	private static DockPanel dockPanel = new DockPanel();

	public void onModuleLoad() {
		Window.setMargin("0px");

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
		dockPanel.add(createMenuBar(), DockPanel.NORTH);
		dockPanel.add(accPan, DockPanel.CENTER);
		//modeConnecte(); // A ne plus appeler !

		// Quand le client ferme la fenètre ou la rafraichit
		CloseHandler<Window> closeHandler = new CloseHandler<Window>() {
			@Override
			public void onClose(CloseEvent<Window> event) {
				SalleService.Util.getInstance().sortie1User(Core.userEnCours.getIdSalleEnCours(),
						userEnCours, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
					}
					@Override
					public void onFailure(Throwable caught) {
					}
				});
				SalleService.Util.getInstance().envoiMessageFromClient(Core.userEnCours.getIdSalleEnCours(),
						userEnCours.getLogin()+" à quitté la salle", 
						"Message automatique", new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
					}
					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		};
		Window.addCloseHandler(closeHandler);
	}
	public static MenuBar createMenuBar(){
		Command cmd = new Command() {
			public void execute() {	Window.alert("You selected a menu item!"); }
		};
		Command cmdDeco = new Command() {
			public void execute() {	 }
		};
		MenuBar menu = new MenuBar();
		//menu.addItem("Fichier", cmd);
		menu.addItem("Application", cmd);
		menu.addItem("Aide", cmd); // "FAQ", "Compatibilite", On peut mettre que F5 = rafraichissement de tout l'appli -> A ne pas utiliser
		menu.addItem("A propos", cmd); // Créateurs (groupe5), copyright(iut Laval)
		if (userEnCours != null) menu.addItem("Se déconnecter", cmdDeco);
		menu.setWidth("99%");
		menu.addStyleName("menu");

		return menu;

	}
	public static void modeConnecte(){
		dockPanel.remove(accPan);
		dockPanel.clear();
		dockPanel.add(createMenuBar(), DockPanel.NORTH);
		dockPanel.add(appPan, DockPanel.CENTER);
	}

	public static void creerApplicationPanel(){
		appPan = new ApplicationPanel();
		appPan.setStyleName("applicationPanel");
	}



} 
