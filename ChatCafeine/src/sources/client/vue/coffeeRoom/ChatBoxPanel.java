/**
 * 
 */
package sources.client.vue.coffeeRoom;

import sources.client.model.PaquetCom;
import sources.client.service.SalleService;
import sources.client.vue.Core;
import sources.client.vue.outils.HeaderPanels;

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
	private int cptTchat = 0;	// Au final, il faut se synchroniser au compteur du servlet
	boolean erreurRecup = false;
	String[] historique = new String[100];
	final ScrollPanel messPanel = new ScrollPanel();
	private HTML conversation;
	static Button bouton;
	String emetteur = "aaa";

	public ChatBoxPanel(){
		// CONFIGURATION DU PANEL
		setHeight("470px");
		setWidth("360px");
		setStyleName("chatboxPan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec4Panel = new DecoratorPanel();
		dec4Panel.setWidget(this);

		// AJOUT ENTETE
		add(new HeaderPanels("La t'chat box"));

		// RECUPERATION DU COMPTEUR TCHAT DU SERVEUR
		SalleService.Util.getInstance().getCptServeur(Core.userEnCours.getIdSalleEnCours(),
				new AsyncCallback<Integer>(){
			@Override
			public void onFailure(Throwable caught) {

			}
			@Override
			public void onSuccess(Integer result) {
				cptTchat = result;
				System.out.println("[Client - Tchat] "+Core.userEnCours.getIdSalleEnCours()+" : Cpt tchat = "+cptTchat);
			}
		});

		//CONFIG DU BOUTON 
		bouton = new Button("Envoyer");
		bouton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(!zone2Text.getText().isEmpty()){
					System.out.println("[Client] : degré caféine : "+Core.userEnCours.getCompteurChat());
					if (Core.userEnCours.getCompteurChat() > 0){
						SalleService.Util.getInstance().envoiMessageFromClient(Core.userEnCours.getIdSalleEnCours(),
								zone2Text.getText(), Core.userEnCours.getLogin(), new AsyncCallback<Void>(){
							@Override
							public void onFailure(Throwable caught) {
							}
							@Override
							public void onSuccess(Void result) {
								zone2Text.setText("");
								Core.userEnCours.unMsgEnvoye();
							}
						});
					}else Window.alert("Votre dose de caféine est trop basse pour pouvoir parler !");
				}
			}
		});

		messPanel.setWidth("350px");
		messPanel.setHeight("340px");
		conversation = new HTML();
		messPanel.add(conversation); 
		add(messPanel);
		add(new HTML("<hr>"));

		// AJOUT ZONE DE TEXTE
		zone2Text = new TextBox();
		zone2Text.setWidth("80%");
		zone2Text.setMaxLength(200);
		zone2Text.setStyleName("messBox");
		add(zone2Text);

		// AJOUT BOUTON
		bouton.setStyleName("envoiButton");
		bouton.setEnabled(false); // Désactivé au lancement car user pas assis
		add(bouton);

		// MESSAGE D'ACCUEIL + LANCEMENT METHODE REFRESH
		historique[99] = "Message automatique : <font color=\"#FF0000\"><em>Bienvenue "+Core.userEnCours.getLogin()+" ! N'oubliez pas de vous installer, ainsi que" +
		"de prendre une place afin de pouvoir communiquer ! </em></font>";
		if (!erreurRecup) refresh();
	}

	/*
	 * METHODE DE RAFRAICHISSEMENT POUR NOUVEAUX MESSAGES
	 */
	private void refresh() {
		SalleService.Util.getInstance().getNewMessage(Core.userEnCours.getIdSalleEnCours(),
				cptTchat , new AsyncCallback<PaquetCom>() {
			@Override
			public void onFailure(Throwable caught) {
				erreurRecup = true;
			}
			@Override
			public void onSuccess(PaquetCom result) {
				if (result != null){
					if (result.getIdSalleDestination() == Core.userEnCours.getIdSalleEnCours()){
						System.out.println("[Client - Tchat] "+Core.userEnCours.getIdSalleEnCours()+" : Paquet n°"+result.getIdPaquet()+" recu !");
						System.out.println("[Client - Tchat] "+Core.userEnCours.getIdSalleEnCours()+" : Message recu : "+result.getMessage());
						if (result.getMessage() != null){
							if (!result.getMessage().equals(historique[99]) // Si le msg le plus récent est différent de celui qu'on vient de recevoir
									&& (Core.userEnCours.isInstalle() || emetteur.equals("Message automatique"))) majHistorique(result.getMessage()); // On l'insère en tête de tableau
							emetteur = result.getNomEmetteur();
							cptTchat++;
							System.out.println("[Client - Tchat] "+Core.userEnCours.getIdSalleEnCours()+" : Cpt tchat = "+cptTchat);
						}
						if (Core.userEnCours.isInstalle() || emetteur.equals("Message automatique")){
							String str = "";
							for (int i = 0; i < 100; i++){
								if (historique[i] != null) 
									str += historique[i]+"<br>";
							}
							conversation.setHTML(str);
							messPanel.scrollToBottom();
						}
					}
				}
				if (Core.userEnCours.isInSalle()) refresh();			// Arreter la récursivité s'il n'est plus dans une salle
			}
		});
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

	public static void activerBoutonEnvoi(){
		bouton.setEnabled(true);
	}
	public static void desactiverBoutonEnvoi(){
		bouton.setEnabled(false);
	}
}

