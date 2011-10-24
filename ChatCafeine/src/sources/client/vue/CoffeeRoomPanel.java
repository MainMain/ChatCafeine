package sources.client.vue;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.LayoutPanel;

public class CoffeeRoomPanel extends AbsolutePanel{
	private static final AbsolutePanel sallePan = new AbsolutePanel();
	private static final AbsolutePanel infosSallePan = new AbsolutePanel();
	private static final AbsolutePanel eventsSallePan = new AbsolutePanel();
	private static final AbsolutePanel chatboxPan = new AbsolutePanel();
	
	public CoffeeRoomPanel(){
		configPanel();
		configSallePan();
		configInfosSallePan();
		configEventsSallePan();
		configChatBoxPan();
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("coffeRoomPanel");
	}
	/**
	 * 
	 */
	private void configSallePan() {
		sallePan.setHeight("300px");
		sallePan.setWidth("900px");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		dec1Panel.setWidget(sallePan);
		add(dec1Panel, 10, 10);
	}
	/**
	 * 
	 */
	private void configInfosSallePan() {
		infosSallePan.setHeight("200px");
		infosSallePan.setWidth("355px");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec2Panel = new DecoratorPanel();
		dec2Panel.setWidget(infosSallePan);
		add(dec2Panel, (10+900+10+10), 10);
	}
	/**
	 * 
	 */
	private void configEventsSallePan() {
		eventsSallePan.setHeight("365px");
		eventsSallePan.setWidth("355px");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(eventsSallePan);
		add(dec3Panel, (10+900+10+10), 10+200+10+10);
	}
	/**
	 * 
	 */
	private void configChatBoxPan() {
		chatboxPan.setHeight("265px");
		chatboxPan.setWidth("900px");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec4Panel = new DecoratorPanel();
		dec4Panel.setWidget(chatboxPan);
		add(dec4Panel, 10, 10+300+10+10);
	}
	
	
}
