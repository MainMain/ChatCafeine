/**
 * 
 */
package sources.client.vue;

import java.util.ArrayList;

import sources.client.model.User;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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
		//setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setStyleName("listUserPanel");
		// Add elements
		add(new HTML("<h3> - Liste des connectés -</h3><hr>"));
	}
	
	public void maj(ArrayList<User> t){
		for (User u : t){
			add(new HTML(" - "+u.getLogin()));
		}
	}
}
