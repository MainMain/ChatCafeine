/**
 * 
 */
package sources.client.vue;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * @author : Johan
 *
 */
public class ApplicationPanel extends TabLayoutPanel{

	public ApplicationPanel(double barHeight, Unit barUnit) {
		super(barHeight, barUnit);
		// Create a tab panel
		setAnimationDuration(1000);
		getElement().getStyle().setMarginBottom(10.0, Unit.PX);
		String[] tabTitles = {"Coffee Room", "Mon profil", "Administration"};
		
		// Add a home tab
		
		HTML homeText = new HTML();
		add(homeText, tabTitles[0]);
		add(homeText);

		// Add a tab with an image
		SimplePanel imageContainer = new SimplePanel();
		add(imageContainer, tabTitles[1]);
		
		// Add a tab
		HTML moreInfo = new HTML();
		add(moreInfo, tabTitles[2]);

		selectTab(0);
	}
}