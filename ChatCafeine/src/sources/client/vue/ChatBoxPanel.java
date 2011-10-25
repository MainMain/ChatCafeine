/**
 * 
 */
package sources.client.vue;

import sources.client.service.ConnexionService;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * @author : Johan
 *
 */
public class ChatBoxPanel extends VerticalPanel{
    TextArea zone2Text;
    HTML conversationLab = new HTML("");
    private int cpt = 1;
    

	public ChatBoxPanel(){
		// Config panel chatBoxPan
		setHeight("98%");
		setWidth("100%");
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setStyleName("chatboxPan");
		// Add elements
		HTML chatTitreLab = new HTML("<h2>- La t'chat box -	</h2><hr>");
		add(chatTitreLab);
		add(conversationLab);
		// Wrap the contents in a DecoratorPanel
		DecoratorPanel dec4Panel = new DecoratorPanel();
		dec4Panel.setWidget(this);
		
        zone2Text = new TextArea();
        zone2Text.setVisibleLines(10);
        Button bouton = new Button("Envoyer");
        bouton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                ConnexionService.Util.getInstance().envoiMessage(zone2Text.getText(), "login", new AsyncCallback<Void>(){
                    @Override
                    public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub
                    }
                    @Override
                    public void onSuccess(Void result) {
                    	//conversationLab.setText(conversationLab.getText()+"\n"+zone2Text.getText());
                    	zone2Text.setText("");
                    }
                });
            }
        });
        add(zone2Text);
        add(bouton);
        refresh();
    }

    private void refresh() {
    	ConnexionService.Util.getInstance().getNewMessage(cpt , new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onSuccess(String result) {
            	conversationLab.setHTML(conversationLab.getText()+"<br>"+result);
                cpt++;
                refresh();
            }
        });
    }
}

