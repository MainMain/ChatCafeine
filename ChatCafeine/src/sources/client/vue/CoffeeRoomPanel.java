package sources.client.vue;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class CoffeeRoomPanel extends LayoutPanel{
	
	public CoffeeRoomPanel(){
		setHeight("600px");
		setStyleName("CoffeeRoomStyle");
		
		Button my = new Button();
		my.setHTML("niiark");
		add(my);
	}
}
