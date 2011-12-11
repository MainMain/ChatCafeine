/**
 * 
 */
package sources.client.vue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author : Johan
 *
 */
public class VueSalle extends AbsolutePanel {
	private int[][] matrice;
	FlexTable flextable;
	FlowPanel flowpanel;

	public VueSalle(){
		setHeight("100%");
		setWidth("100%");
		setStyleName("vueSalle");
		
		flextable = new FlexTable();
	    flextable.clear();
	    flowpanel = new FlowPanel();
	    flextable.setWidget(3, 0, flowpanel);
	    flowpanel.setSize("100%", "100%");
	    flextable.setCellSpacing(0);
	    add(flextable);
		matrice = new int[][]     
		                    {
				{ 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1 }, 
				{ 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 1, 1 },  
				{ 1, 1, 2, 3, 3, 3, 3, 3, 3, 3, 2, 1 },  
				{ 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 1, 1 },  
				{ 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5 }
		                    };
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 12; j++)
				flextable.setWidget(i, j,(new Case(matrice[i][j])));
	}

	public class Case extends AbsolutePanel {
		public Case(int x){
			setSize("54px", "54px");
			if (x == 1) { setStyleName("caseSol"); add(new HTML(""));}
			else if (x == 2) { add(new SiegeButton()); 		add(new HTML(""));}
			else if (x == 3) { setStyleName("caseTable");  	add(new HTML(""));}
			else if (x == 4) { add(new CafeButton()); 		add(new HTML(""));}
			else if (x == 5) { add(new SortieButton()); 	add(new HTML(""));}
		}
	}

	public class SiegeButton extends PushButton{
		public SiegeButton(){
			setSize("100%", "100%");
			setStyleName("caseSiege");
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Click sur une place !");
					Core.userEnCours.sinstaller();
					ChatBoxPanel.activerBoutonEnvoi();
					Image avatarImg;
					if (Core.userEnCours.getCheminAvatar() != null) 
						avatarImg = new Image("images/"+Core.userEnCours.getCheminAvatar());
					else
						avatarImg = new Image("images/anonyme.jpg");
					avatarImg.setSize("100%", "100%");
					//add(avatarImg);
					setStyleName("testImg");
					 new SiegeButton(avatarImg);
				}
			});
		}
		public SiegeButton(Image img){
			setSize("100%", "100%");
			setStyleName("caseSiege");
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Click sur une place !");
					Core.userEnCours.sinstaller();
					ChatBoxPanel.activerBoutonEnvoi();
				}
			});
		}
	}
	public class CafeButton extends Button{
		public CafeButton(){
			setSize("100%", "100%");
			setStyleName("caseCafe");
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Click sur la machine !");
					Core.userEnCours.quitterLaPlace();
					ChatBoxPanel.desactiverBoutonEnvoi();
				}
			});
		}
	}
	public class SortieButton extends Button{
		public SortieButton(){
			setSize("100%", "100%");
			setStyleName("caseSortie");;
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Click sur la sortie !");
					ChatBoxPanel.desactiverBoutonEnvoi();
				}
			});
		}
	}
}