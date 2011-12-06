package sources.client.vue;

import java.util.ArrayList;

import sources.client.model.Salle;
import sources.client.model.User;
import sources.client.service.SalleService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class CoffeeRoomPanel extends AbsolutePanel{
	private static Salle salleEnCours = null;
	private static ChatBoxPanel chatBox;
	private static VisualisationSallePanel visualSalle;
	
	public CoffeeRoomPanel(){
		creerSalleDeTest();

		configPanel();
		configChatBox();
		//configListUserPan();
		//configInfosSallePan();
		//configEventsSallePan(); // Idée : Mettre les évnts dans la tchat box (d'une couleur différente)
		configVisualisationSalle();
		
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT-20+"px");
		setStyleName("coffeRoomPanel");
		// Informer d'un nouvel arrivant***************************************************************************************************
		/*SalleService.Util.getInstance().entre1User(Core.userEnCours, salleEnCours, new AsyncCallback<ArrayList<User>>(){
			@Override
			public void onFailure(Throwable caught) {
				
			}
			@Override
			public void onSuccess(ArrayList<User> result) {
				visualSalle.getListUser().maj(result);
			}
		})*/;
	}
	/**
	 * 
	 */
	private void configChatBox() {
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		chatBox = new ChatBoxPanel();
		dec1Panel.setWidget(chatBox);
		add(dec1Panel, 900+10+10+10, 10);
	}
	/**
	 * 
	 */
	private void configVisualisationSalle() {
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		visualSalle = new VisualisationSallePanel(salleEnCours);
		dec3Panel.setWidget(visualSalle);
		add(dec3Panel, 10, 10);
	}
	/**
	 * 
	 */
	private void creerSalleDeTest() {
		Salle mySalle = new Salle ("0o0o°° Salle de test °°o0o0", "Développement", "Une superbe salle pour super tester le super développement ! Admirez donc la gestion de projet exceptionnelle par cet exceptionnel MainMain ! ", 40);
		salleEnCours = mySalle;
		//*******************************************************************
		//SalleService.Util.getInstance().ouvertureSalle(mySalle, null);
	}

	/**
	 * 
	 */
	private void configInfosSallePan() {
		DockPanel infosSallePan = new DockPanel();
		// Config panel infoSallePan
		infosSallePan.setHeight("200px");
		infosSallePan.setWidth("355px");
		infosSallePan.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		infosSallePan.setStyleName("infosSallePan");
		// Add elements
		HTML titreThemeLab = new HTML("<h2>"+salleEnCours.getNom()+"</h2><h3>Thème : "+salleEnCours.getTheme()+"</h3>");
		infosSallePan.add(titreThemeLab, DockPanel.NORTH);
		HTML descLab = new HTML("<em>"+salleEnCours.getDescription());
		infosSallePan.add(descLab, DockPanel.CENTER);
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec2Panel = new DecoratorPanel();
		dec2Panel.setWidget(infosSallePan);
		add(dec2Panel, 10, 10);
	}
}
