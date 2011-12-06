package sources.client.vue;

import sources.client.model.Salle;

import com.google.gwt.user.client.ui.*;

public class CoffeeRoomPanel extends AbsolutePanel{
	private static StackLayoutPanel stackPanel;
	public static Salle salleEnCours = null;

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
	}
	/**
	 * 
	 */
	private void configChatBox() {
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		dec1Panel.setWidget(new ChatBoxPanel());
		add(dec1Panel, 900+10+10+10, 10);
	}
	/**
	 * 
	 */

	private void configVisualisationSalle() {
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(new VisualisationSallePanel());
		add(dec3Panel, 10, 10);
	}
	/**
	 * 
	 */
	private void configListUserPan() {
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		dec1Panel.setWidget(new ListUserPanel());
		add(dec1Panel, 10, 10);
	}
	/**
	 * 
	 */

	private void configEventsSallePan() {
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(new EventsPanel());
		add(dec3Panel, 385, 10);
	}

	/**
	 * 
	 */
	private void creerSalleDeTest() {
		Salle mySalle = new Salle ("0o0o°° Salle de test °°o0o0", "Développement", "Une superbe salle pour super tester le super développement ! Admirez donc la gestion de projet exceptionnelle par cet exceptionnel MainMain ! ", 40);
		salleEnCours = mySalle;
	}
	/**
	 * 
	 */

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
