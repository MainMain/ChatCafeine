/**
 * 
 */
package sources.client.vue;

import sources.client.service.SalleService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author : Johan
 *
 */
public class EventsPanel extends VerticalPanel{
	private int cpt = 1;
	boolean erreurRecup = false;
	String[] historique = new String[15];
	VerticalPanel messPanel = new VerticalPanel();
	
	public EventsPanel(){
		// Config panel eventsSallePan
		setHeight("200px");
		setWidth("900px");
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setStyleName("eventsSallePan");
		setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		// Add elements
		HTML evntTitreLab = new HTML("<h2>- Les derniers évenements -	</h2><hr>");
		add(evntTitreLab);
		messPanel.setStyleName("test3");
		add(messPanel);
		
		refresh();
	}

	private void refresh() {
		SalleService.Util.getInstance().getNewEvent(cpt , new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				//Window.alert("Erreur lors de la récupération des messages ! \n Récupération stoppée !");
				erreurRecup = true;
			}
			@Override
			public void onSuccess(String result) {
				//conversationLab.setHTML(conversationLab.getText()+"<br>"+result); // A optimiser : mettre messages dans un tableau de 100 emplacements
				if (result != historique[14]) majHistorique(result); 
				cpt++;
				if (!erreurRecup) refresh();
			}
		});
		messPanel.clear();
		for (int i = 0; i < 14; i++){
			if (historique[i] != null) messPanel.add(new HTML(i+" : " +historique[i]));
			else messPanel.add(new HTML(""));
		}
	}
	private void majHistorique(String nvMess){ // Horrible algorithmiquement, je sais ;)
		for (int i = 0; i < 14; i++){ 
			if (historique[i+1] != null) historique[i] = historique[i+1];
		}
		historique[14] = nvMess;
	}
}
