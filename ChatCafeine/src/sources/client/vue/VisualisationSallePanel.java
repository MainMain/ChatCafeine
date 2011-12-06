/**
 * 
 */
package sources.client.vue;

import com.google.gwt.user.client.ui.*;

/**
 * @author : Johan
 *
 */
public class VisualisationSallePanel extends AbsolutePanel {
	
	public VisualisationSallePanel(){
		// Config panel chatBoxPan
		setHeight("560px");
		setWidth("900px");
		//setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		// Add elements
		add(new ListUserPanel());
		add(new VueSalle(), 226, 0);
	}
}
