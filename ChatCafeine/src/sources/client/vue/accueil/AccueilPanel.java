package sources.client.vue.accueil;

import sources.client.model.User;
import sources.client.service.CompteService;
import sources.client.service.ConnexionService;
import sources.client.service.SalleService;
import sources.client.vue.Core;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AccueilPanel extends VerticalPanel{
	public AbsolutePanel headPanel;
	public HorizontalPanel bodyPanel;
	public VerticalPanel leftBodyPanel;
	public AbsolutePanel rightBodyPanel;
	public static boolean DEBUG = true;

	public AccueilPanel(){
		configPanel();
		createHeadPanel();
		createBodyPanel();
		createLeftPanel();
		createRightPanel();
	}
	/**
	 * Méthodes de classe
	 */
	private void configPanel() {
		this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setBorderWidth(1);
		this.setWidth("99%");
		this.setHeight(Core.HEIGHT+10+"px");
	}

	/*
	 * Panel Verticaux
	 */
	private void createHeadPanel() {

		headPanel = new AbsolutePanel();
		headPanel.setWidth("100%");
		headPanel.setHeight("80px");
		//headPanel.setBorderWidth(1);
		headPanel.setStyleName("headPanel");
		//headPanel.setVerticalAlignment(ALIGN_TOP);
		this.add(headPanel);

		// 2 text box, 2 label, un bouton
		final TextBox loginBox = new TextBox();
		loginBox.setText("Mainmain");
		loginBox.setMaxLength(20);
		loginBox.setHeight("15px");
		final PasswordTextBox mdpBox = new PasswordTextBox();
		mdpBox.setText("azer");
		mdpBox.setHeight("15px");
		mdpBox.setMaxLength(20);

		Label loginLabel = new Label();
		loginLabel.setText("Login");
		loginLabel.setStyleName("labelHeadPanel");
		Label mdpLabel = new Label();
		mdpLabel.setText("Mot de passe");
		mdpLabel.setStyleName("labelHeadPanel");
		final HTML errorHTML = new HTML();
		errorHTML.setHTML("");
		Button connexButton = new Button(
				"Connexion", new ClickHandler(){
					public void onClick(ClickEvent event) {
						if(casesRemplies()){
							ConnexionService.Util.getInstance().authentifier(loginBox.getText(), mdpBox.getText(), 
									new AsyncCallback<User>(){
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Erreur : "+ caught.getMessage());
								}
								@Override
								public void onSuccess(User result) {
									if (result==null)
										errorHTML.setHTML("<font color=\"#FF0000\"><em><small>Identifiant et/ou " +
										"mot de passe incorrect(s)</small></em></font>");
									else {
										Core.userEnCours = result;
										Core.creerApplicationPanel();
										Core.modeConnecte();
										errorHTML.setHTML("<font color=\"#FFFFFF\"><em><small>Ok !</small></em></font>");
										//userConnected=result;
										//ecran.majAdmin();
										//dialogBox.hide();
									}
								}
							});						
						}else errorHTML.setHTML("<font color=\"#FFCC00\"><em><small>Erreur : Vous devez remplir " +
						"tout les champs !</small></em></font>");
					}
					private boolean casesRemplies() {
						return (loginBox.getText().length()!=0 && mdpBox.getText().length()!=0);
					}
				});
		//connexButton.setText("Connexion");
		connexButton.setHeight("28px");
		connexButton.setStyleName("connexButton");

		headPanel.add(loginLabel, 700, 12);
		headPanel.add(mdpLabel, 900, 12);
		headPanel.add(errorHTML, 900, 55);
		headPanel.add(loginBox, 700, 30);
		headPanel.add(mdpBox, 900, 30);
		headPanel.add(connexButton, 1116, 30);

		Image logoImg = new Image("images/fon-40_2.png");
		headPanel.add(logoImg, 90, 10);
		//headPanel.add(logoImg);
	}


	private void createBodyPanel() {
		bodyPanel = new HorizontalPanel();
		bodyPanel.setWidth("100%");
		bodyPanel.setHeight("560px");
		//bodyPanel.setBorderWidth(1);
		bodyPanel.setStyleName("bodyPanel");
		this.add(bodyPanel);
	}

	/*
	 * Panels horizontaux
	 */
	private void createLeftPanel() {
		leftBodyPanel = new VerticalPanel();
		//leftBodyPanel.setWidth("600px");
		leftBodyPanel.setWidth("80%");
		leftBodyPanel.setHeight("100%");
		//leftBodyPanel.setBorderWidth(1);
		leftBodyPanel.setStyleName("leftBodyPanel");
		bodyPanel.add(leftBodyPanel);

		HTML presentLabel = new HTML();
		presentLabel.setHTML("<br>Le chat cafeiné est un salon de détente virtuel où vous pourrez échanger avec " +
		"<br> vos connaissances, le tout basé autour d'une indispensable machine à café !");
		presentLabel.setStyleName("presentLabel");
		leftBodyPanel.add(presentLabel);

		Image cafeImg = new Image("images/test2.gif");
		cafeImg.setSize("640px", "480px");
		cafeImg.setStylePrimaryName("accImg");
		leftBodyPanel.add(cafeImg);

	}

	private void createRightPanel() {
		rightBodyPanel = new AbsolutePanel();
		rightBodyPanel.setWidth("400px");
		rightBodyPanel.setHeight("100%");
		//leftBodyPanel.setBorderWidth(1);
		rightBodyPanel.setStyleName("rightBodyPanel");

		bodyPanel.add(rightBodyPanel);


		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		layout.setWidth("350px");
		layout.setHeight("450px");
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		layout.setHTML(0, 0, "<strong><h1>Inscription</h1></strong><br><i>Il ne vous suffira que de remplir " +
		"ces infos pour vous inscrire !<hr>");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Create TextBox
		final TextBox login2Box = new TextBox();
		final PasswordTextBox pass2Box = new PasswordTextBox();
		final PasswordTextBox pass2VerifBox = new PasswordTextBox();
		final TextBox mailBox = new TextBox();
		final TextBox mailVerifBox = new TextBox();
		final RadioButton hRadio = new RadioButton("Sexe", "Homme");
		final RadioButton fRadio = new RadioButton("Sexe", "Femme");
		final HTML error2HTML = new HTML("");
		error2HTML.setHTML("<font color=\"#000000\"><em><small>Le login et le mot de passe doivent faire au moins 4 caractères.</small></em></font>");

		// Add a drop box with the list types
		final ListBox ageBox = new ListBox(false);
		for (int i = 10; i <= 99; i++) {
			ageBox.addItem(""+i+" ans");
		}

		// Add some standard form options
		layout.setHTML(1, 0, "Login:");
		layout.setWidget(1, 1, login2Box);
		layout.setHTML(2, 0, "Mot de<br>passe:");
		layout.setWidget(2, 1, pass2Box);
		layout.setHTML(3, 0, "Confirmer<br>passe:");
		layout.setWidget(3, 1, pass2VerifBox); // RAJOUTER EVENEMENT QUI CONTROLE LE PASS EN DIRECT ?
		layout.setHTML(4, 0, "Age:");
		layout.setWidget(4, 1, (Widget) ageBox);
		layout.setHTML(5, 0, "Sexe:");
		layout.setWidget(5, 1, hRadio);
		layout.setWidget(6, 1, fRadio);
		layout.setHTML(7, 0, "Email:");
		layout.setWidget(7, 1, mailBox);
		layout.setHTML(8, 0, "Confirmer<br>email:");
		layout.setWidget(8, 1, mailVerifBox);
		layout.setWidget(9,1, error2HTML);
		layout.setWidget(10, 1, new Button(
				"S'inscrire", new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (casesRemplies()){
							if (mailValide()){
								if (passIdentiques()){
									if (mailIdentiques()){
										int age = Integer.parseInt(ageBox.getItemText(ageBox.getSelectedIndex()).substring(0,2));
										error2HTML.setHTML("<font color=\"green\"><em><small>Inscription complétée !</small></em></font>");
										if(hRadio.getValue()!=null){
											CompteService.Util.getInstance().inscription(login2Box.getText(), pass2Box.getText(), age,
													hRadio.getText(), mailBox.getText(), new AsyncCallback<Boolean>(){
												@Override
												public void onFailure(Throwable caught) {
													Window.alert("Erreur : "+ caught.getMessage());
												}
												@Override
												public void onSuccess(Boolean result) {
													if (result==null)
														Window.alert("Erreur lors de l'inscription !");
												}
											});
										}
										else{
											CompteService.Util.getInstance().inscription(login2Box.getText(), pass2Box.getText(), age,
													fRadio.getText(), mailBox.getText(), new AsyncCallback<Boolean>(){
												@Override
												public void onFailure(Throwable caught) {
													Window.alert("Erreur : "+ caught.getMessage());
												}
												@Override
												public void onSuccess(Boolean result) {
													if (result==null)
														Window.alert("Erreur lors de l'inscription !");
												}
											});
										}
									}else error2HTML.setHTML("<font color=\"#FF00\"><em><small>Erreur : Les adresses " +
									"mails de sont pas identiques !</small></em></font>");
								}else error2HTML.setHTML("<font color=\"#FF00\"><em><small>Erreur : Les mot de passes" +
								" ne sont pas identiques !</small></em></font>");
							}else error2HTML.setHTML("<font color=\"#FF00\"><em><small>Erreur : Adresse mail invalide" +
							" !</small></em></font>");
						}else error2HTML.setHTML("<font color=\"#FF00\"><em><small>Erreur : Vous devez correctement remplir tout les " +
						"champs Le login et mot de passe doivent faire au moins 4 caractères !</small></em></font>");
					}

					private boolean casesRemplies() {
						return (login2Box.getText().length()>=4 && 
								pass2Box.getText().length()>=4 && 
								pass2VerifBox.getText().length()!=0 && 
								mailBox.getText().length()!=0 && 
								mailVerifBox.getText().length()!=0 &&
								(hRadio.getValue() || fRadio.getValue())
						);
					}

					private boolean mailValide(){
						return (mailBox.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
					}
					private boolean passIdentiques(){
						return ((pass2Box.getText()).equals(pass2VerifBox.getText()));
					}

					private boolean mailIdentiques(){
						return ((mailBox.getText()).equals(mailVerifBox.getText()));
					}
				}));
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		layout.setStyleName("formInscription");
		decPanel.setWidget(layout);
		rightBodyPanel.add(decPanel, 20, 20);


	}


}
