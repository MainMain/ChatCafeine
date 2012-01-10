/**
 * 
 */
package sources.client.vue.coffeeRoom;

import sources.client.service.SalleService;
import sources.client.vue.Core;
import sources.client.vue.outils.HeaderPanels;
import sources.client.vue.profil.FichePan;

import java.util.ArrayList;

import sources.client.model.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author : Johan
 *
 */
public class ListUserPanel extends VerticalPanel{
	public ListUserPanel(){
		// Config panel chatBoxPan
		setHeight("560px");
		setWidth("200px");
		//setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		setStyleName("listUserPanel");
		// Add elements
		//add(new HTML("<h3> - Liste des connectés -</h3><hr>"));
	}
	
	public void maj(ArrayList<User> t){
		clear();
		add(new HeaderPanels("Liste des connectés"));
		for (User u : t){
			add(new loginHTML(u));
			if (Core.userEnCours.getDroit().equals("moderateur") || 
					Core.userEnCours.getDroit().equals("administrateur"))
					add(new ejectBouton(u));	
			add(new HTML("<hr>"));
		}
	}
	
	public class loginHTML extends HTML{
		private User user;
		public loginHTML(User u){
			super(" - <u>"+u.getLogin()+"</u> - "+u.getNbrCafePris()+" café(s) pris");
			user = u;
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					System.out.println("voir profil ");
					DialogBoxVueUser vueP = new DialogBoxVueUser(user);
				}
			});
		}
	}
	
	public class ejectBouton extends Button{
		private User user;
		public ejectBouton(User u){
			super("Ejecter !");
			user = u;
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					SalleService.Util.getInstance().ejecter(Core.userEnCours.getIdSalleEnCours()
							, user, new AsyncCallback<Void>(){
								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void onSuccess(Void result) {
									// TODO Auto-generated method stub
								}
					});					
				}
			});
		}
	}
}
