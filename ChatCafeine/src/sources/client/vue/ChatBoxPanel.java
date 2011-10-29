/**
 * 
 */
package sources.client.vue;

import sources.client.service.ChatService;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * @author : Johan
 *
 */
public class ChatBoxPanel extends AbsolutePanel{
	TextBox zone2Text;
	private int cpt;
	boolean erreurRecup = false;
	String[] historique = new String[100];
	final VerticalPanel messPanel = new VerticalPanel();

	public ChatBoxPanel(){
		// Config panel chatBoxPan
		setHeight("98%");
		setWidth("100%");
		//setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setStyleName("chatboxPan");
		// Add elements
		HTML chatTitreLab = new HTML("<h2>- La t'chat box -	</h2><hr>");
		chatTitreLab.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(chatTitreLab);

		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec4Panel = new DecoratorPanel();
		dec4Panel.setWidget(this);

		zone2Text = new TextBox();
		zone2Text.setWidth("80%");
		zone2Text.setMaxLength(200);
		Button bouton = new Button("Envoyer");
		bouton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(!zone2Text.getText().isEmpty()){
					ChatService.Util.getInstance().envoiMessage(zone2Text.getText(), Core.userEnCours.getLogin(), new AsyncCallback<Void>(){
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
						}
						@Override
						public void onSuccess(Void result) {
							zone2Text.setText("");
						}
					});
				}
			}
		});
		messPanel.setWidth("100");
		messPanel.setHeight("350px");
		messPanel.setSpacing(2);
		messPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		//messPanel.setStyleName("test3");
		add(messPanel);
		add(new HTML("<hr>"));

		zone2Text.setStyleName("messBox");
		add(zone2Text);

		bouton.setStyleName("envoiButton");
		add(bouton);
		
		ChatService.Util.getInstance().getCptServeur(new AsyncCallback<Integer>(){
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Integer result) {
				//Window.alert("MAJ cpt");
				cpt = result; // !!!!!!!!! PB <- Répétition du 1er mess
			}
		});
		
		if (!erreurRecup) refresh();
	}

	private void refresh() {
		ChatService.Util.getInstance().getNewMessage(cpt , new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erreur lors de la récupération des messages ! \n Récupération stoppée !");
				erreurRecup = true;
			}
			@Override
			public void onSuccess(String result) {
				if (result != historique[99]) majHistorique(result); // Si le mesg le plus récent est différent de celui qu'on vient de recevoir
				cpt++;
				refresh();
			}
		});
		messPanel.clear();
		/*for(String msg : historique){ // Puis on lui met les bons messages
			//if (msg != null) conversationLab.setText(conversationLab.getText()+"\n"+msg); 
			if (msg != null) messPanel.add(new HTML(msg)); // Le message le plus récent doit être le dernier ! (Donc position 0, puis 1 etc...)
		}*/
		for (int i = 0; i < 100; i++){
			if (historique[i] != null) messPanel.add(new HTML(historique[i]));
			else messPanel.add(new HTML(""));
		}
	}

	private void majHistorique(String nvMess){ // Horrible algorithmiquement, je sais ;)
		for (int i = 0; i < 99; i++){ 
			if (historique[i+1] != null) historique[i] = historique[i+1];
		}
		historique[99] = nvMess;
	}
}

