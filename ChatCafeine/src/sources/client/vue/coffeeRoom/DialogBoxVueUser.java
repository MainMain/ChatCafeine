/**
 * 
 */
package sources.client.vue.coffeeRoom;

import sources.client.model.User;
import sources.client.vue.profil.FichePan;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * @author : Johan
 *
 */
public class DialogBoxVueUser extends DialogBox{

	public DialogBoxVueUser(User user){
		setModal(false);
		setSize("400px", "450px");
		add(new FichePan(user, true));
		setVisible(true);
		/*Button close = new Button("Fermer la fenètre");
		close.setStyleName("boutonRech");
		close.setHeight("50px");
		close.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
			}
		});
		add(close);*/
		show();
		center();
	}
	@Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                    hide();
                }
                break;
        }
    }
}
