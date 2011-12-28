package sources.client.vue;



import sources.client.model.Salle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;

public class CoffeeRoomPanel extends AbsolutePanel{
	private Salle salleEnCours = null;
	private ChatBoxPanel chatBox;
	private VisualisationSallePanel visualSalle;
	private ChoixSallePanel choixSalle;
	private static CoffeeRoomPanel instance = null;
	
	private CoffeeRoomPanel(){
		configPanel();
		creerEcranChoixSalle();
		//creerEcranInSalle(4);
	}
	
	public static CoffeeRoomPanel getInstance(){
		if (instance == null)
			instance = GWT.create(CoffeeRoomPanel.class);
		return instance;
	}
	
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT-20+"px");
		setStyleName("coffeRoomPanel");
	}

	public void creerEcranInSalle(Salle s){
		clear();
		salleEnCours = s;
		Core.userEnCours.setIdSalleEnCours(s.getIdSalle());
		Core.userEnCours.entrerInSalle();
		configChatBox();
		configVisualisationSalle();
	}
	
	public void creerEcranChoixSalle(){
		clear();
		Core.userEnCours.setIdSalleEnCours(-1);
		Core.userEnCours.sortirFromSalle();
		choixSalle = new ChoixSallePanel(this);
		add(choixSalle);
	}


	/*
	 * ECRAN DE TCHAT UNE FOIS ENTRE DANS LA SALLE
	 */
	private void configChatBox() {
		// Nom & thème salle
		DecoratorPanel dec2Panel = new DecoratorPanel();
		VerticalPanel infosBox = new VerticalPanel();
		infosBox.setSize("360px", "50px");
		infosBox.add(new HTML(salleEnCours.getNom()));
		dec2Panel.setWidget(infosBox);
		add(dec2Panel, 900+10+10+10, 10);
		
		// Chat
		DecoratorPanel dec1Panel = new DecoratorPanel();
		chatBox = new ChatBoxPanel();
		dec1Panel.setWidget(chatBox);
		add(dec1Panel, 900+10+10+10, 10+60);
	}
	
	private void configVisualisationSalle() {
		DecoratorPanel dec3Panel = new DecoratorPanel();
		visualSalle = new VisualisationSallePanel(salleEnCours);
		dec3Panel.setWidget(visualSalle);
		add(dec3Panel, 10, 10);
	}





	
	
	
	
	
	

	/**
	 * 
	 */
	private void creerSalleDeTest() {
		Salle mySalle = new Salle (9, "0o0o°° Salle de test °°o0o0", "Développement", "Une superbe salle pour super tester le super développement ! Admirez donc la gestion de projet exceptionnelle par cet exceptionnel MainMain ! ", 40);
		salleEnCours = mySalle;
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
