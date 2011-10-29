package sources.client.vue;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;

public class AdminPanel extends AbsolutePanel{

	public AdminPanel(){
		configPanel();
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("adminPanel");
		
		 //Create a new stack layout panel.
		StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
		stackPanel.setWidth("100%");
		stackPanel.setHeight("575px");
		// Add the list User.
		Widget listUserPanelHeader = createHeaderWidget("Liste des utilisateurs");
		stackPanel.add(new ListeUser().onInitialize(), listUserPanelHeader, 4);
		// Add the list room.
		Widget listRoomPanelHeader = createHeaderWidget("Liste des salles");
		stackPanel.add(creerListeSallePanel(), listRoomPanelHeader, 4);
		//add(stackPanel);
	}
	
	private Widget creerListeSallePanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		return a;
	}
	
	private static Widget createHeaderWidget(String text) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHeight("100%");
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		hPanel.add(headerText);
		return new SimplePanel(hPanel);
	}
}
