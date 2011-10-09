package sources.client.vue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class AccueilBox {
	static Button btnMeDeconnecter;
	static TextBox id;
	static PasswordTextBox mdp;
	//private static UserInfo userConnected=null;

	/**
	 * Create the dialog box
	 * @param ecran 
	 *
	 * @return the new dialog box
	 */
	public static DialogBox createDialogBox() {
		// Create a dialog box and set the caption text
		final DialogBox dialogBox = new DialogBox();
		dialogBox.ensureDebugId("cwDialogBox");
		dialogBox.setText("Connexion");

		id=new TextBox();
		id.setMaxLength(20);
		mdp=new PasswordTextBox();
		mdp.setMaxLength(20);

		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSize("400px","200px");
		dialogContents.setSpacing(4);
		dialogBox.setWidget(dialogContents);
		dialogContents.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		// Add some text to the top of the dialog
		HTML bienvenue = new HTML("<H3>Bienvenue !</h3>");
		dialogContents.add(bienvenue);
		dialogContents.setCellHorizontalAlignment(
				bienvenue, HasHorizontalAlignment.ALIGN_CENTER);

		// grid 2 * 2 
		Grid grille = new Grid(2,2);
		HTML login = new    HTML("Identifiant ");
		grille.setWidget(0,0,login);
		grille.setWidget(0,1,id);
		HTML password = new HTML("Mot de passe");
		grille.setWidget(1,0,password);
		grille.setWidget(1,1,mdp);
		dialogContents.add(grille);
		final HTML mess = new HTML("");
		dialogContents.add(mess);
		dialogContents.setCellHorizontalAlignment(
				mess, HasHorizontalAlignment.ALIGN_CENTER);
		// Add a close button at the bottom of the dialog
		Button closeButton = new Button(
				"Fermer", new ClickHandler() {
					public void onClick(ClickEvent event) {
						/*
						if (verifOK()) {
							Connexion.Util.getInstance().isIdentifie(id.getText(), mdp.getText(), new AsyncCallback<UserInfo>(){
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Probleme de communication avec le serveur Web ou le SGBD");
								}
								@Override
								public void onSuccess(UserInfo result) {
									if (result==null)
										mess.setHTML("<font color=\"red\"><em><small>Identifiant et/ou mot de passe incorrect(s)</small></em></font>");
									else {
										Window.alert("Bienvenue "+result.getPrenom()+" "+result.getNom());
										userConnected=result;
										ecran.majAdmin();
										dialogBox.hide();
									}
								}

							});
						}
						*/
					}

					private boolean verifOK() {
						return (id.getText().length()!=0 && mdp.getText().length()!=0);
					}
				});
		dialogContents.add(closeButton);
		dialogContents.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_CENTER);

		id.setFocus(true);
		return dialogBox;
	}

	public static String getIdU() {
		/*
		 if (userConnected == null)
			return "";
		else return userConnected.getId();
		*/
		return "";
	}

}
