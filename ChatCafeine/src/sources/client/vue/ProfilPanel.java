package sources.client.vue;

import com.google.gwt.user.client.ui.LayoutPanel;


public class ProfilPanel extends LayoutPanel{

	public ProfilPanel(){
		configPanel();
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("profilPanel");
	}
}
