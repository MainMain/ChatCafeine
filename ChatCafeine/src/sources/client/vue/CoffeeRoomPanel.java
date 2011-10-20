package sources.client.vue;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.LayoutPanel;

public class CoffeeRoomPanel extends LayoutPanel{
	
	public CoffeeRoomPanel(){
		setHeight("600px");
		setStyleName("CoffeeRoomStyle");
		
		Button my = new Button();
		my.setHTML("niiark");
		add(my);
	}
}
