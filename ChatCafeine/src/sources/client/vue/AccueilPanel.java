package sources.client.vue;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class AccueilPanel extends LayoutPanel{

	public AccueilPanel(){
		Label lblGhjhjk = new Label("GHJHJK");
		add(lblGhjhjk);
		setSize("639px", "587px");
		setWidgetLeftWidth(lblGhjhjk, 202.0, Unit.PX, 65.0, Unit.PX);
		setWidgetTopHeight(lblGhjhjk, 134.0, Unit.PX, 17.0, Unit.PX);
	}
}
