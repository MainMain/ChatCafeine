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
	private int cpt = 0;	// Au final, il faut se synchroniser au compteur du servlet
	boolean erreurRecup = false;
	String[] historique = new String[100];
	final VerticalPanel messPanel = new VerticalPanel();
	private HTML conversation = new HTML();
	static Button bouton;

	public ChatBoxPanel(){
		ChatService.Util.getInstance().getCptServeur(new AsyncCallback<Integer>(){
			@Override
			public void onFailure(Throwable caught) {
				
			}
			@Override
			public void onSuccess(Integer result) {
				cpt = result;
				System.out.println(Core.userEnCours.getLogin()+" : cpt récup ! : "+result);
			}
		});
		// Config panel chatBoxPan
		setHeight("560px");
		setWidth("360px");
		//setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setStyleName("chatboxPan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec4Panel = new DecoratorPanel();
		dec4Panel.setWidget(this);
		// Add elements
		HTML chatTitreLab = new HTML("<h3>- La t'chat box -	</h3><hr>");
		chatTitreLab.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(chatTitreLab);
		zone2Text = new TextBox();
		zone2Text.setWidth("80%");
		zone2Text.setMaxLength(200);

		/*
		 * CONFIG DU BOUTON 
		 */
		bouton = new Button("Envoyer");
		bouton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(!zone2Text.getText().isEmpty()){
					ChatService.Util.getInstance().envoiMessage(zone2Text.getText(), 
							Core.userEnCours.getLogin(), new AsyncCallback<Void>(){
						@Override
						public void onFailure(Throwable caught) {
						}
						@Override
						public void onSuccess(Void result) {
							zone2Text.setText("");
						}
					});
				}
			}
		});
		messPanel.setWidth("350px");
		messPanel.setHeight("420px");
		add(messPanel);
		add(new HTML("<hr>"));

		zone2Text.setStyleName("messBox");
		add(zone2Text);

		bouton.setStyleName("envoiButton");
		add(bouton);

		if (!erreurRecup) refresh();
	}

	public static void activerBoutonEnvoi(){
		bouton.setEnabled(true);
	}
	public static void desactiverBoutonEnvoi(){
		bouton.setEnabled(false);
	}

	/*
	 * METHODE DE RAFRAICHISSEMENT POUR NOUVEAUX MESSAGES
	 */
	private void refresh() {
		ChatService.Util.getInstance().getNewMessage(cpt , new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erreur lors de la récupération des messages ! \n Récupération stoppée !");
				erreurRecup = true;
			}
			@Override
			public void onSuccess(String result) {
				if (result != historique[99]) majHistorique(result+" "+cpt); // Si le mesg le plus récent est différent de celui qu'on vient de recevoir
				cpt++;
				refresh();
			}
		});
		messPanel.clear();
		/*for(String msg : historique){ // Puis on lui met les bons messages
			//if (msg != null) conversationLab.setText(conversationLab.getText()+"\n"+msg); 
			if (msg != null) messPanel.add(new HTML(msg)); // Le message le plus récent doit être le dernier ! (Donc position 0, puis 1 etc...)
		}*/
		String str = "";
		for (int i = 0; i < 100; i++){
			if (historique[i] != null) messPanel.add(new HTML(historique[i]));
			else messPanel.add(new HTML(""));
			//if (historique[i] != null) str += historique[i];
		}
		//conversation.setText(str);
		//messPanel.add(conversation);
		
	}

	/*
	 * MISE A JOUR DE L'HISTORIQUE
	 */
	private void majHistorique(String nvMess){ // Horrible algorithmiquement, je sais ;)
		for (int i = 0; i < 99; i++){ 
			if (historique[i+1] != null) historique[i] = historique[i+1];
		}
		historique[99] = nvMess;
	}
}

