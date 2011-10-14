package sources.client.vue;

import com.google.gwt.user.client.ui.*;

public class AccueilPanel extends AbsolutePanel{
	public VerticalPanel mainPanel;
	public VerticalPanel headPanel;
	public HorizontalPanel bodyPanel;
	
	public AccueilPanel(){
		//setWidth("99%");
		setStyleName("accueilPanel");

		mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(mainPanel.ALIGN_CENTER);
		//mainPanel = new VerticalPanel();
		mainPanel.setBorderWidth(1);
		mainPanel.setWidth("100%");
		mainPanel.setHeight("100%");
		mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		createHeadPanel();
		createBodyPanel();
		createLeftPanel();
		createRightPanel();
		
		mainPanel.add(headPanel);
		mainPanel.add(bodyPanel);
		add(mainPanel);
		
		/*
		Image cafeImg = new Image("img/Accueil.gif");
		VerticalPanel panelLeft = new VerticalPanel();
		panelLeft.setSpacing(10);
		add(panelLeft, mainPanel.WEST);
		panelLeft.add(cafeImg);
		*/
	}
	private void createHeadPanel() {
		headPanel = new VerticalPanel();
		headPanel.setWidth("99%");
		headPanel.setHeight("100%");
		headPanel.setBorderWidth(1);
		headPanel.setStyleName("headPanel");
		
		headPanel.add(new HTML("CoucouHead"));
	}

	private void createBodyPanel() {
		bodyPanel = new HorizontalPanel();
		bodyPanel.setWidth("99%");
		//bodyPanel.setHeight("200px");
		bodyPanel.setBorderWidth(1);
		bodyPanel.setStyleName("bodyPanel");
		
		bodyPanel.add(new HTML("CoucouBody"));
	}

	/**
	 * 
	 */
	private void createRightPanel() {
	}

	/**
	 * 
	 */
	private void createLeftPanel() {
	}

}
