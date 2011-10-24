package sources.client.vue;


import com.google.gwt.user.client.ui.LayoutPanel;

public class AdminPanel extends LayoutPanel{

	public AdminPanel(){
		configPanel();
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("adminPanel");
	}
}
