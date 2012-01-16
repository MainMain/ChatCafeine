/**
 * 
 */
package sources.client.vue.coffeeRoom;

import java.sql.Date;
import java.util.ArrayList;

import sources.client.model.Salle;
import sources.client.service.SalleService;
import sources.client.vue.Core;
import sources.client.vue.outils.HeaderPanels;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author : Johan
 *
 */
public class ChoixSallePanel extends AbsolutePanel{
	private ArrayList<Salle> listeSalles;
	FlexTable flextable;
	FlowPanel flowpanel;
	
	public ChoixSallePanel(CoffeeRoomPanel coffeeRoomPanel){
		setSize("100%", "100%");
		VerticalPanel sallesPan = new VerticalPanel();
		sallesPan.setSize("960px", "555px");
		sallesPan.add(new HeaderPanels("Choix de la salle"));	
		
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setSize("100%", "505px");
		scrollPanel.setStyleName("choixSallePan");
		flextable = new FlexTable();
		flextable.clear();
		flextable.setSize("100%", "100%");
		flextable.setCellSpacing(50);
		scrollPanel.add(flextable);
		sallesPan.add(scrollPanel);
		
		DecoratorPanel dec2Panel = new DecoratorPanel();
		dec2Panel.setWidget(sallesPan);
		add(dec2Panel, 10, 10);	
		
		listeSalles = new ArrayList<Salle>();
		
		// RECUPERER LES INSTANCES DES SALLES
		SalleService.Util.getInstance().getToutesSalles(new AsyncCallback<ArrayList<Salle>>() {
			@Override
			public void onSuccess(ArrayList<Salle> result) {
				listeSalles = result;
				System.out.println("ok");
				afficherSalles();
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("nok");
			}
		});
		
		creerPanelRechercheSalle();
		creerPanelRechercheLogin();
	}
	
	/**
	 * 
	 */
	private void creerPanelRechercheSalle() {
		VerticalPanel panel = new VerticalPanel();
		TextBox themeBox = new TextBox();
		TextBox nomBox = new TextBox();
		Button validerButton = new Button("Rechercher");
		HTML a = new HTML("<B>Par thème :</B>");
		HTML b = new HTML("<B>Par nom :</B>");
		a.setStyleName("textRed");
		b.setStyleName("textRed");
		validerButton.setStyleName("boutonRech");
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.setSize("300px", "300px");
		panel.add(new HeaderPanels("Rechercher une salle"));
		panel.setStyleName("recherchePan");
		panel.add(a);
		themeBox.setText("Thème à rechercher");
		panel.add(themeBox);
		panel.add(b);
		nomBox.setText("Nom à rechercher");
		panel.add(nomBox);
		panel.add(validerButton);
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(panel);
		add(decPanel, 990, 10);	
	}

	/**
	 * 
	 */
	private void creerPanelRechercheLogin() {
		VerticalPanel panel = new VerticalPanel();
		TextBox loginBox = new TextBox();
		Button validerButton = new Button("Rechercher");
		HTML a = new HTML("<B>Par thème :</B>");
		a.setStyleName("textRed");
		validerButton.setStyleName("boutonRech");
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.setSize("300px", "235px");
		panel.add(new HeaderPanels("Rechercher une personne"));
		panel.setStyleName("recherchePan");
		panel.add(a);
		loginBox.setText("Login à rechercher");
		panel.add(loginBox);
		panel.add(validerButton);
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(panel);
		add(decPanel, 990, 10+300+10+10);	
	}

	private void afficherSalles() {
		int i = 0, j = 0;
		for (Salle s : listeSalles){
			System.out.println("[Client] : "+s.getIdSalle()+" - "+s.getNom()+" - "+s.getDescription());
			flextable.setWidget(j, i, new CarreChoixSalle(s.getNom(), s));
			System.out.println("**** Salle "+s.getNom()+" installée en "+j+" - "+i);
			i++;
			if (i == 4) { j++; i = 0;}
		}
	}
	
	public class CarreChoixSalle extends Button{
		public Salle salle;
		
		public CarreChoixSalle(String nomSalle, Salle s){
			super("<h2>"+nomSalle+"</h2><br>Thème : "+s.getTheme()+"<br>Nb places max : "+s.getNbrPlaceMax());
			this.salle = s;
			setSize("150px", "160px");
				setStyleName("boutonChoixSalle");
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event){
					Salle a =  ((CarreChoixSalle)event.getSource()).salle;
					// Vérifier si banni
					SalleService.Util.getInstance().isBanned(a.getIdSalle(),
							Core.userEnCours.getIdInt(), new AsyncCallback<Date>() {
								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									System.out.println("sa plante !");
								}

								@Override
								public void onSuccess(Date result) {
									System.out.println("[Client] : Banni de la salle? "+salle.getIdSalle()+" - "
											+Core.userEnCours.getIdInt()+" - "+ result);
									if (result == null )CoffeeRoomPanel.getInstance().creerEcranInSalle(salle);
									else com.google.gwt.user.client.Window.alert("Vous êtes banni" +
											"de cette salle ! Date de fin : "+result);
								}
							});
					
				}
			});
		}
	}
}
