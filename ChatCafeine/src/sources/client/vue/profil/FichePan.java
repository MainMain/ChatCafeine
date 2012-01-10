/**
 * 
 */
package sources.client.vue.profil;

import sources.client.model.User;
import sources.client.vue.outils.HeaderPanels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

/**
 * @author : Johan
 *
 */
public class FichePan extends AbsolutePanel {

	public FichePan(User u, boolean fromListUser){
		setHeight("400px");
		setWidth("450px");
		setStyleName("fichePan");

		if (fromListUser) add(new HeaderPanels("Fiche utilisateur"));
		else add(new HeaderPanels("Ma fiche utilisateur"));
		
		// AVATAR
		AbsolutePanel avatarPan = new AbsolutePanel();
		avatarPan.setHeight("200px");
		avatarPan.setWidth("200px");
		avatarPan.setStyleName("avatarPan");
		Image avatarImg;
		if (u.getCheminAvatar() != null) 
			avatarImg = new Image("images/"+u.getCheminAvatar());
		else
			avatarImg = new Image("images/anonyme.jpg");
		avatarImg.setSize("100%", "100%");
		avatarPan.add(avatarImg);
		add(avatarPan);
		setWidgetPosition(avatarPan, 220, 65);

		// BOUTON CHANGE AVATAR
		Button changeA = new Button("Changer d'avatar");
		changeA.setStyleName("boutonRech");
		changeA.setHeight("50px");
		changeA.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		if (!fromListUser) add(changeA, 275, 270);
		HTML textLogin = new HTML();
		textLogin.setHTML("<b>Login : </b>"+u.getLogin());
		add(textLogin);
		setWidgetPosition(textLogin, 10, 60);

		HTML textSexe = new HTML();
		textSexe.setHTML("<b>Sexe : </b>"+u.getGenre());
		add(textSexe);
		setWidgetPosition(textSexe, 10, 90);

		HTML textAge = new HTML();
		textAge.setHTML("<b>Age : </b>"+u.getAge());
		add(textAge);
		setWidgetPosition(textAge, 10, 120);

		HTML textActivite = new HTML();
		textActivite.setHTML("<b>Activit&eacute; : </b>"+u.getActivite());
		add(textActivite);
		setWidgetPosition(textActivite, 10, 150);

		HTML textAime = new HTML();
		textAime.setHTML("<b>Aime : </b>"+u.getAime()+"<br><br><b>N'aime pas : </b>"+u.getAimePas());
		add(textAime);
		setWidgetPosition(textAime, 10, 200);

		if (fromListUser){
		}

	}
}
