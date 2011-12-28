/**
 * 
 */
package sources.client.vue;

import java.util.ArrayList;

import sources.client.model.Salle;
import sources.client.service.SalleService;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author : Johan
 *
 */
public class ChoixSallePanel extends AbsolutePanel{
	private ArrayList<Salle> listeSalles;
	FlexTable flextable;
	FlowPanel flowpanel;
	private static int cpt = 0;
	
	public ChoixSallePanel(CoffeeRoomPanel coffeeRoomPanel){
		setSize("100%", "100%");

		flextable = new FlexTable();
		flextable.clear();
		flowpanel = new FlowPanel();
		flextable.setWidget(1, 0, flowpanel);
		flowpanel.setSize("700px", "450px");
		flextable.setCellSpacing(0);
		flextable.setStyleName("tabApplication");
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(new HTML("TEST"));	
		dec3Panel.setWidget(flextable);
		add(dec3Panel, 10, 10);
		
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
	}
	
	private void afficherSalles() {
		int i = 0, j = 0;
		for (Salle s : listeSalles){
			System.out.println("Client : "+s.getIdSalle()+" - "+s.getNom()+" - "+s.getDescription());
			flextable.setWidget(j, i, new CarreChoixSalle(s.getNom(), s));
			i++;
			if (i == 10) { j++; i = 0;}
		}
	}
	
	public class CarreChoixSalle extends Button{
		public Salle salle;
		
		public CarreChoixSalle(String nomSalle, Salle s){
			super(nomSalle);
			this.salle = s;
			setSize("100px", "100px");
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event){
					Salle a =  ((CarreChoixSalle)event.getSource()).salle;
					CoffeeRoomPanel.getInstance().creerEcranInSalle(a);
				}
			});
		}
		public void test(){
		}
	}
}
