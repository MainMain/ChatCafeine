package sources.client.vue.profil;

import sources.client.model.User;
import sources.client.service.CompteService;
import sources.client.service.ProfilService;
import sources.client.service.ProfilServiceAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import sources.client.vue.Core;
import sources.client.vue.outils.HeaderPanels;
/**
 * @author : Audrey & Johan
 *
 */
public class ProfilPanel extends AbsolutePanel{
	private static final AbsolutePanel fichePan = new AbsolutePanel();
	private static final AbsolutePanel modifPan = new AbsolutePanel();
	private static final VerticalPanel supprPan = new VerticalPanel();

	public ProfilPanel(){
		configPanel();
		configFichePan();
		configModifPan();
		configSupprPan();

	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("profilPanel");
	}
	/**
	 * configuration profil
	 */
	private void configFichePan() {
		fichePan.setHeight("400px");
		fichePan.setWidth("450px");
		fichePan.setStyleName("fichePan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		dec1Panel.setWidget(fichePan);
		add(dec1Panel, 10, 10);

		fichePan.add(new HeaderPanels("Ma fiche utilisateur"));

		// AVATAR
		AbsolutePanel avatarPan = new AbsolutePanel();
		avatarPan.setHeight("200px");
		avatarPan.setWidth("200px");
		avatarPan.setStyleName("avatarPan");
		Image avatarImg;
		if (Core.userEnCours.getCheminAvatar() != null) 
			avatarImg = new Image("images/"+Core.userEnCours.getCheminAvatar());
		else
			avatarImg = new Image("images/anonyme.jpg");
		avatarImg.setSize("100%", "100%");
		avatarPan.add(avatarImg);
		fichePan.add(avatarPan);
		fichePan.setWidgetPosition(avatarPan, 220, 65);

		// BOUTON CHANGE AVATAR
		Button changeA = new Button("Changer d'avatar");
		changeA.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		fichePan.add(changeA, 250, 270);
		HTML textLogin = new HTML();
		textLogin.setHTML("<b>Login : </b>"+Core.userEnCours.getLogin());
		fichePan.add(textLogin);
		fichePan.setWidgetPosition(textLogin, 10, 60);

		HTML textSexe = new HTML();
		textSexe.setHTML("<b>Sexe : </b>"+Core.userEnCours.getGenre());
		fichePan.add(textSexe);
		fichePan.setWidgetPosition(textSexe, 10, 90);

		HTML textAge = new HTML();
		textAge.setHTML("<b>Age : </b>"+Core.userEnCours.getAge());
		fichePan.add(textAge);
		fichePan.setWidgetPosition(textAge, 10, 120);

		HTML textActivite = new HTML();
		textActivite.setHTML("<b>Activit&eacute; : </b>"+Core.userEnCours.getActivite());
		fichePan.add(textActivite);
		fichePan.setWidgetPosition(textActivite, 10, 150);

		HTML textAime = new HTML();
		textAime.setHTML("<b>Aime : </b>"+Core.userEnCours.getAime()+"<br><br><b>N'aime pas : </b>"+Core.userEnCours.getAimePas());
		fichePan.add(textAime);
		fichePan.setWidgetPosition(textAime, 10, 200);


	}
	/**
	 * modifications profil
	 */
	private void configModifPan() {
		modifPan.setHeight("585px");
		modifPan.setWidth("805px");
		modifPan.setStyleName("modifPan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec2Panel = new DecoratorPanel();
		dec2Panel.setWidget(modifPan);
		add(dec2Panel, 480, 10);

		modifPan.add(new HeaderPanels("Modifier le profil / mot de passe"));

		// mod generales
		// mod age
		HTML textAge = new HTML();
		textAge.setHTML("Age : ");
		final TextBox boxAge = new TextBox();
		boxAge.setWidth("20px");
		boxAge.setValue(Core.userEnCours.getAge());

		modifPan.add(textAge);
		modifPan.add(boxAge);
		modifPan.setWidgetPosition(textAge, 30, 90);
		modifPan.setWidgetPosition(boxAge, 120, 90);


		// mod activite
		HTML textActivite = new HTML();
		textActivite.setHTML("Activite : ");
		final TextBox boxActivite = new TextBox();
		boxActivite.setWidth("90px");
		boxActivite.setValue(Core.userEnCours.getActivite());

		modifPan.add(textActivite);
		modifPan.add(boxActivite);
		modifPan.setWidgetPosition(textActivite, 420, 90);
		modifPan.setWidgetPosition(boxActivite, 530, 90);

		//mod aime
		HTML textAime = new HTML();
		textAime.setHTML("Aime : ");
		final TextArea boxAime = new TextArea();
		boxAime.setSize("500px", "60px");
		boxAime.setValue(Core.userEnCours.getAime());

		modifPan.add(textAime);
		modifPan.add(boxAime);
		modifPan.setWidgetPosition(textAime, 30, 130);
		modifPan.setWidgetPosition(boxAime, 120, 130);

		//mod aime pas
		HTML textAimePas = new HTML();
		textAimePas.setHTML("N'aime pas : ");
		final TextArea boxAimePas = new TextArea();
		boxAimePas.setSize("500px", "60px");
		boxAimePas.setValue(Core.userEnCours.getAimePas());

		modifPan.add(textAimePas);
		modifPan.add(boxAimePas);
		modifPan.setWidgetPosition(textAimePas, 30, 210);
		modifPan.setWidgetPosition(boxAimePas, 120, 210);


		Button modifButton = new Button(
				"Modifier", new ClickHandler(){
					public void onClick(ClickEvent event) {
						int age = 0;
						//actions click mod
						if(boxAge.getValue()!=""){
							try {
								age = Integer.parseInt(boxAge.getValue());
								Core.userEnCours.setAge(boxAge.getValue());
							}
							catch (Exception e) {
								age = 0;
							}

							if(boxActivite.getValue()!=""){
								Core.userEnCours.setActivite(boxActivite.getValue());
							}
							if(boxAime.getValue()!=""){
								Core.userEnCours.setAime(boxAime.getValue());
							}
							if(boxAimePas.getValue()!=""){
								Core.userEnCours.setAimePas(boxAimePas.getValue());
							}
							fichePan.clear();
							configFichePan();
							ProfilService.Util.getInstance().modifInfos(Core.userEnCours.getIdInt(), 
									boxAime.getValue(), boxAimePas.getValue(), age, boxActivite.getValue(), new AsyncCallback<Boolean>(){
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Erreur : "+ caught.getMessage());
								}
								@Override
								public void onSuccess(Boolean result) {
								}
							});				
						}
					}

				});

		modifButton.setWidth("200px");
		modifPan.add(modifButton);
		modifPan.setWidgetPosition(modifButton, 250, 300);
		// fin mod generales

		// debut mod pass
		HTML textModifPass = new HTML();
		textModifPass.setHTML("<br><h3>Modifier mon mot de passe</h3><br>");
		modifPan.add(textModifPass);
		modifPan.setWidgetPosition(textModifPass, 60, 320);

		// ancien pass
		HTML textMdp = new HTML();
		textMdp.setHTML("Ancien mot de passe : ");
		final TextBox boxMdp = new TextBox();
		boxMdp.setWidth("140px");

		modifPan.add(textMdp);
		modifPan.add(boxMdp);
		modifPan.setWidgetPosition(textMdp, 30, 410);
		modifPan.setWidgetPosition(boxMdp, 210, 410);

		// nouveau pass
		HTML textNewMdp = new HTML();
		textNewMdp.setHTML("Nouveau mot de passe : ");
		final TextBox boxNewMdp = new TextBox();
		boxNewMdp.setWidth("140px");

		modifPan.add(textNewMdp);
		modifPan.add(boxNewMdp);
		modifPan.setWidgetPosition(textNewMdp, 30, 450);
		modifPan.setWidgetPosition(boxNewMdp, 210,450);

		// confirmer pass
		HTML textConfMdp = new HTML();
		textConfMdp.setHTML("Confirmer : ");
		final TextBox boxConfMdp = new TextBox();
		boxConfMdp.setWidth("140px");

		modifPan.add(textConfMdp);
		modifPan.add(boxConfMdp);
		modifPan.setWidgetPosition(textConfMdp, 30, 490);
		modifPan.setWidgetPosition(boxConfMdp, 210,490);

		Button modifPassButton = new Button(
				"Modifier", new ClickHandler(){
					public void onClick(ClickEvent event) {
						// actions click mod pass
						// si mdp entré != vide et de ancien mdp annuler mod
						if(boxMdp.getValue().equals(Core.userEnCours.getMdp())){
							if(boxNewMdp.getValue().equals(boxConfMdp.getValue())){
								if(boxNewMdp.getValue()!=""){
									Window.alert("Votre mot de passe à été modifié");
									ProfilService.Util.getInstance().modifMdp(Core.userEnCours.getIdInt(), 
											boxNewMdp.getValue(), new AsyncCallback<Boolean>() {
										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Erreur : "+ caught.getMessage());
										}
										@Override
										public void onSuccess(Boolean result) {
										}
									});
									Core.userEnCours.setMdp(boxMdp.getValue());
									boxNewMdp.setValue("");
									boxMdp.setValue("");
									boxConfMdp.setValue("");
								}else{
									//popup nouveau mot de passe incorrect
									Window.alert("Nouveau mot de passe incorrect");
								}
							}else{
								//popup les mdp ne correspondent pas
								Window.alert("Les mots de passe ne correspondent pas");
							}
						}else{
							//le mdp n'est pas le bon
							Window.alert("Mot de passe incorrect");
						}

					}				
				});
		// fin mod pass

		modifPassButton.setWidth("200px");
		modifPan.add(modifPassButton);
		modifPan.setWidgetPosition(modifPassButton, 250,530);
	}
	/**
	 * suppression profil
	 */
	private void configSupprPan() {
		supprPan.setHeight("165px");
		supprPan.setWidth("450px");
		supprPan.setStyleName("supprPan");
		supprPan.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(supprPan);
		add(dec3Panel, 10, 430);

		/* author Audrey */

		supprPan.add(new HeaderPanels("Supprimer mon compte"));

		HTML textSuppr2 = new HTML();
		textSuppr2.setHTML("Attention, cette action est irreversible.Toutes les informations li&eacute;es &agrave; votre " +
		"compte seront supprim&eacute;es.");
		supprPan.add(textSuppr2);
		//supprPan.setWidgetPosition(textSuppr2, 10, 60);

		Button supprButton = new Button(
				"Supprimer mon compte", new ClickHandler(){
					public void onClick(ClickEvent event) {
						CompteService.Util.getInstance().desincription(Core.userEnCours.getIdInt(), new AsyncCallback<Boolean>(){
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Erreur : "+ caught.getMessage());
							}
							@Override
							public void onSuccess(Boolean result) {
								if (result==null)
									Window.alert("Erreur lors de la suppression du compte !");
								else{
									
								}
							}
						});
					}				
				});
		supprButton.setWidth("200px");
		supprPan.add(supprButton);
		//supprPan.setWidgetPosition(supprButton, 80, 110);
		/* fin audrey */
	}

}