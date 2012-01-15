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
		if (u.getActivite().length() < 26)
			textActivite.setHTML("<b>Activit&eacute; : </b>"+u.getActivite());

		add(textActivite);
		setWidgetPosition(textActivite, 10, 150);

		HTML textAime = new HTML();
		if (u.getAime().length() < 26)
			textAime.setHTML("<b>Aime : </b>"
					+u.getAime());

		else if (u.getAime().length() < 51){
			textAime.setHTML("<b>Aime : </b>"
					+u.getAime().substring(0, 25)+"<br>"
					+u.getAime().substring(25, u.getAime().length()));
		}
		else if (u.getAime().length() < 76){
			textAime.setHTML("<b>Aime : </b>"
					+u.getAime().substring(0, 25)+"<br>"
					+u.getAime().substring(25, 50)+"<br>"
					+u.getAime().substring(50, u.getAime().length()));
		}
		else if (u.getAime().length() <= 91){
			textAime.setHTML("<b>Aime : </b>"
					+u.getAime().substring(0, 25)+"<br>"
					+u.getAime().substring(25, 50)+"<br>"
					+u.getAime().substring(50, 75)+"<br>"
					+u.getAime().substring(75, u.getAime().length()));
		}
		add(textAime);
		setWidgetPosition(textAime, 10, 200);

		HTML textAimePas = new HTML();
		if (u.getAimePas().length() < 26)
			textAimePas.setHTML("<b>N'aime pas : </b>"
					+u.getAimePas());

		else if (u.getAimePas().length() < 51){
			textAimePas.setHTML("<b>N'aime pas : </b>"
					+u.getAimePas().substring(0, 25)+"<br>"
					+u.getAimePas().
					substring(26, u.getAimePas().length()));
		}
		else if (u.getAimePas().length() < 76){
			textAimePas.setHTML("<b>N'aime pas : </b>"
					+u.getAimePas().substring(0, 25)+"<br>"
					+u.getAimePas().substring(25, 50)+"<br>"
					+u.getAimePas().substring(50, u.getAimePas().length()));
		}
		else if (u.getAimePas().length() <= 91){
			textAimePas.setHTML("<b>N'aime pas : </b>"
					+u.getAimePas().substring(0, 25)+"<br>"
					+u.getAimePas().substring(25, 50)+"<br>"
					+u.getAimePas().substring(50, 75)+"<br>"
					+u.getAimePas().substring(75, u.getAimePas().length()));
		}

		add(textAimePas);
		setWidgetPosition(textAimePas, 10, 280);
		if (fromListUser){
			HTML b = new HTML("<em>Appuyer sur \"échap\" pour fermer<em>");
			add (b);
			setWidgetPosition(b, 200, 370);
		}
	}
}
