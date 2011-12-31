/**
 * 
 */
package sources.client.vue.outils;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author : Johan
 *
 */
public class HeaderPanels extends VerticalPanel{
	public HeaderPanels( String texte){
		setStyleName("headerProfilPan");
		setWidth("100%");
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		HTML textFiche = new HTML();
		textFiche.setHTML("<h3>"+texte+"</h3>");
		textFiche.setStyleName("textHeaderProfilPan");
		add(textFiche);
	}
	public HeaderPanels(int width, int height, String texte){
		setStyleName("headerProfilPan");
		setWidth("100%");
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		HTML textFiche = new HTML();
		textFiche.setHTML("<h3>"+texte+"</h3>");
		textFiche.setStyleName("textHeaderProfilPan");
		add(textFiche);
	}
}
