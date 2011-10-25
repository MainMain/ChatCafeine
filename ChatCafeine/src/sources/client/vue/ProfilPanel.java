package sources.client.vue;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DecoratorPanel;

public class ProfilPanel extends AbsolutePanel{
	private static final AbsolutePanel fichePan = new AbsolutePanel();
	private static final AbsolutePanel modifPan = new AbsolutePanel();
	private static final AbsolutePanel supprPan = new AbsolutePanel();
	
	public ProfilPanel(){
		configPanel();
		configFichePan();
		configModifPan();
		configSupprPan();
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("profilPanel");
	}
	/**
	 * 
	 */
	private void configFichePan() {
		fichePan.setHeight("400px");
		fichePan.setWidth("450px");
		fichePan.setStyleName("fichePan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec1Panel = new DecoratorPanel();
		dec1Panel.setWidget(fichePan);
		add(dec1Panel, 10, 10);
	}
	/**
	 * 
	 */
	private void configModifPan() {
		modifPan.setHeight("585px");
		modifPan.setWidth("805px");
		modifPan.setStyleName("modifPan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec2Panel = new DecoratorPanel();
		dec2Panel.setWidget(modifPan);
		add(dec2Panel, 480, 10);
		
	}
	/**
	 * 
	 */
	private void configSupprPan() {
		supprPan.setHeight("165px");
		supprPan.setWidth("450px");
		supprPan.setStyleName("supprPan");
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec3Panel = new DecoratorPanel();
		dec3Panel.setWidget(supprPan);
		add(dec3Panel, 10, 430);
	}
	
}
