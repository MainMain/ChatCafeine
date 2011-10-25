package sources.client.vue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sources.client.model.Salle;

import com.google.gwt.user.client.ui.*;

public class CoffeeRoomPanel extends AbsolutePanel{
	private static final AbsolutePanel sallePan = new AbsolutePanel();
	private static final DockPanel infosSallePan = new DockPanel();
	private static final VerticalPanel eventsSallePan = new VerticalPanel();
	private static final VerticalPanel chatboxPan = new VerticalPanel();
	private static StackLayoutPanel stackPanel;
	public static Salle salleEnCours = null;

	public CoffeeRoomPanel(){
		creerSalleDeTest();

		configPanel();
		//configSallePan();
		configInfosSallePan();
		configEventsSallePan();
		//configChatBoxPan();
		stackPanel = (StackLayoutPanel) CreateStackTest.onInitialize();
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec0Panel = new DecoratorPanel();
		dec0Panel.setWidget(stackPanel);
		add(dec0Panel, 10, 10);
	}

	/**
	 * 
	 */
	private void creerSalleDeTest() {
		Salle mySalle = new Salle ("0o0o°° Salle de test °°o0o0", "Développement", "Une super salle pour super tester le super développement ! Admirez donc la gestion de projet exceptionnelle par cet exceptionnel MainMain ! ", 40);
		salleEnCours = mySalle;
	}
	/**
	 * 
	 */
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("coffeRoomPanel");
	}

	/**
	 * 
	 */
	private void configInfosSallePan() {
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
		add(dec2Panel, (10+900+10+10), 10);
	}
	/**
	 * 
	 */
	private void configEventsSallePan() {
		// Config panel eventsSallePan
		eventsSallePan.setHeight("365px");
		eventsSallePan.setWidth("355px");
		eventsSallePan.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		eventsSallePan.setStyleName("eventsSallePan");
		// Add elements
		HTML evntTitreLab = new HTML("<h2>- Les derniers évenements -	</h2><hr>");
		eventsSallePan.add(evntTitreLab);
		HTML evntLab = new HTML(Core.userEnCours+" vient de rejoindre la salle !");
		eventsSallePan.add(evntLab);
		evntLab.setHTML(evntLab.getHTML()+"<br>"+Core.userEnCours+" vient de rejoindre la salle !");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(eventsSallePan);
		add(dec3Panel, (10+900+10+10), 10+200+10+10);
	}
	/**
	 * 
	 */
	/*
	private void configChatBoxPan() {
		// Config panel chatBoxPan
		chatboxPan.setHeight("265px");
		chatboxPan.setWidth("900px");
		chatboxPan.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		chatboxPan.setStyleName("chatboxPan");
		// Add elements
		HTML chatTitreLab = new HTML("<h2>- La t'chat box -	</h2><hr>");
		chatboxPan.add(chatTitreLab);
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec4Panel = new DecoratorPanel();
		dec4Panel.setWidget(chatboxPan);
		add(dec4Panel, 10, 10+300+10+10);
	}
	/**
	 * 

	private void configSallePan() {
		sallePan.setHeight("300px");
		sallePan.setWidth("900px");
		sallePan.setStyleName("sallePan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		dec1Panel.setWidget(sallePan);
		add(dec1Panel, 10, 10);
	}
	 */
	
}
