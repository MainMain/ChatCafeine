package sources.client.vue;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AccueilPanel extends DockPanel{

	public AccueilPanel(){
		Label test = new Label("Coucou !");
		add(test);
		setSize("1400px", "587px");
		setStyleName("accueilPanel");
		
		/*
		Image cafeImg = new Image("img/Accueil.gif");
		VerticalPanel panelLeft = new VerticalPanel();
		panelLeft.setSpacing(10);
		add(panelLeft, DockPanel.WEST);
		panelLeft.add(cafeImg);
		*/
	}
}
