/**
 * 
 */
package sources.client.vue;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.dom.client.Style.Unit;

/**
 * @author : Johan
 *
 */
public class CreateStackTest {
	public CreateStackTest(){
		
	}

	/**
	 * Initialize this example.
	 */
	public static Widget onInitialize() {
		// Create a new stack layout panel.
		StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
		stackPanel.setPixelSize(900, 575);

		// Add the chat.
		Widget sallePanelHeader = createHeaderWidget("Tchat");
		stackPanel.add(createSallePanel(),sallePanelHeader, 4);

		// Add the chatBox.
		Widget chatBoxPanelHeader = createHeaderWidget("TchatBox");
		stackPanel.add(new ChatBoxPanel(), chatBoxPanelHeader, 4);

		// Return the stack panel.
		stackPanel.ensureDebugId("cwStackLayoutPanel");
		return stackPanel;
	}

	
	private static Widget createHeaderWidget(String text) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHeight("100%");
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		headerText.setStyleName("cw-StackPanelHeader");
		hPanel.add(headerText);
		return new SimplePanel(hPanel);
	}
	
	private void addItem(TreeItem root, ImageResource image, String label) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.append(SafeHtmlUtils.fromTrustedString(AbstractImagePrototype.create(
				image).getHTML()));
		sb.appendEscaped(" ").appendEscaped(label);
		root.addItem(sb.toSafeHtml());
	}
	
	
	/**
	 * @return
	 */
	private static Widget createSallePanel() {
		// TODO Auto-generated method stub
		return new SimplePanel();
	}

	
	/*
	private Widget createContactsItem(Images images) {
		// Create a popup to show the contact info when a contact is clicked
		HorizontalPanel contactPopupContainer = new HorizontalPanel();
		contactPopupContainer.setSpacing(5);
		contactPopupContainer.add(new Image(images.defaultContact()));
		final HTML contactInfo = new HTML();
		contactPopupContainer.add(contactInfo);
		final PopupPanel contactPopup = new PopupPanel(true, false);
		contactPopup.setWidget(contactPopupContainer);

		// Create the list of contacts
		VerticalPanel contactsPanel = new VerticalPanel();
		contactsPanel.setSpacing(4);
		String[] contactNames = constants.cwStackLayoutPanelContacts();
		String[] contactEmails = constants.cwStackLayoutPanelContactsEmails();
		for (int i = 0; i < contactNames.length; i++) {
			final String contactName = contactNames[i];
			final String contactEmail = contactEmails[i];
			final Anchor contactLink = new Anchor(contactName);
			contactsPanel.add(contactLink);

			// Open the contact info popup when the user clicks a contact
			contactLink.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					// Set the info about the contact
					SafeHtmlBuilder sb = new SafeHtmlBuilder();
					sb.appendEscaped(contactName);
					sb.appendHtmlConstant("<br><i>");
					sb.appendEscaped(contactEmail);
					sb.appendHtmlConstant("</i>");
					contactInfo.setHTML(sb.toSafeHtml());

					// Show the popup of contact info
					int left = contactLink.getAbsoluteLeft() + 14;
					int top = contactLink.getAbsoluteTop() + 14;
					contactPopup.setPopupPosition(left, top);
					contactPopup.show();
				}
			});
		}
		return new SimplePanel(contactsPanel);
	}

	private Widget createFiltersItem() {
		VerticalPanel filtersPanel = new VerticalPanel();
		filtersPanel.setSpacing(4);
		for (String filter : constants.cwStackLayoutPanelFilters()) {
			filtersPanel.add(new CheckBox(filter));
		}
		return new SimplePanel(filtersPanel);
	}

	private Widget createMailItem(Images images) {
		Tree mailPanel = new Tree(images);
		TreeItem mailPanelRoot = mailPanel.addItem("foo@example.com");
		String[] mailFolders = constants.cwStackLayoutPanelMailFolders();
		addItem(mailPanelRoot, images.inbox(), mailFolders[0]);
		addItem(mailPanelRoot, images.drafts(), mailFolders[1]);
		addItem(mailPanelRoot, images.templates(), mailFolders[2]);
		addItem(mailPanelRoot, images.sent(), mailFolders[3]);
		addItem(mailPanelRoot, images.trash(), mailFolders[4]);
		mailPanelRoot.setState(true);
		return mailPanel;
	}
*/

}
