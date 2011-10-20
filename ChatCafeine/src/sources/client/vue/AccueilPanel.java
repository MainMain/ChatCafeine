package sources.client.vue;

import sources.client.model.User;
import sources.client.service.ConnexionService;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
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
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AccueilPanel extends VerticalPanel{
	public AbsolutePanel headPanel;
	public HorizontalPanel bodyPanel;
	public VerticalPanel leftBodyPanel;
	public AbsolutePanel rightBodyPanel;

	/*
	 * Création des commandes
	 */
	Command connexionCmd = new Command() {
		public void execute() {
		}
	};

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
		this.setHeight(IHM.HEIGHT+10+"px");
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
		loginBox.setText("");
		final TextBox mdpBox = new TextBox();
		mdpBox.setText("****");

		Label loginLabel = new Label();
		loginLabel.setText("Login");
		loginLabel.setStyleName("labelHeadPanel");
		Label mdpLabel = new Label();
		mdpLabel.setText("Mot de passe");
		mdpLabel.setStyleName("labelHeadPanel");
		final HTML errorHTML = new HTML();
		errorHTML.setHTML("");
		Button connexButton = new Button(
				"Fermer", new ClickHandler() {
					public void onClick(ClickEvent event) {
						if(casesRemplies()){
							ConnexionService.Util.getInstance().authentifier(loginBox.getText(), loginBox.getText(), new AsyncCallback<User>(){
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Erreur de communication avec le serveur Web ou la base de données");
								}
								@Override
								public void onSuccess(User result) {
									if (result==null)
										errorHTML.setHTML("<font color=\"red\"><em><small>Identifiant et/ou mot de passe incorrect(s)</small></em></font>");
									else {
										Window.alert("Bienvenue "+result.getLogin());
										//userConnected=result;
										//ecran.majAdmin();
										//dialogBox.hide();
									}
								}
							});
						}else Window.alert("Erreur : Vous devez renseigner tout les champs !");
					}
					private boolean casesRemplies() {
						return (loginBox.getText().length()!=0 && mdpBox.getText().length()!=0);
					}
				});
		connexButton.setText("Connexion");
		connexButton.setStyleName("connexButton");

		headPanel.add(loginLabel, 700, 12);
		headPanel.add(mdpLabel, 900, 12);
		headPanel.add(errorHTML, 900, 55);
		headPanel.add(loginBox, 700, 30);
		headPanel.add(mdpBox, 900, 30);
		headPanel.add(connexButton, 1100, 30);

		Image logoImg = new Image("images/fon-40.png");
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

		Image cafeImg = new Image("images/test.gif");
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
		layout.setHTML(0, 0, "<strong><h1>Inscription</h1></strong><br><i>Il nous vous suffira que de remplir ces infos pour vous inscrire !<hr>");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		layout.setHTML(1, 0, "Login:");
		layout.setWidget(1, 1, new TextBox());
		layout.setHTML(2, 0, "Mot de<br>pass:");
		layout.setWidget(2, 1, new TextBox());
		layout.setHTML(3, 0, "Confirmer<br>pass:");
		layout.setWidget(3, 1, new TextBox());
		layout.setHTML(4, 0, "Age:");
		layout.setWidget(4, 1, new TextBox());
		layout.setHTML(5, 0, "Sexe:");
		layout.setWidget(5, 1, new RadioButton("Sexe", "Homme"));
		layout.setWidget(6, 1, new RadioButton("Sexe", "Femme"));
		layout.setHTML(7, 0, "Email:");
		layout.setWidget(7, 1, new TextBox());
		layout.setHTML(8, 0, "Confirmer<br>email:");
		layout.setWidget(8, 1, new TextBox());
		layout.setWidget(9, 1, new Button("S'inscrire"));

		// Wrap the contents in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		rightBodyPanel.add(decPanel, 20, 20);
	}


}
