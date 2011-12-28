package sources.client.vue;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.view.client.ListDataProvider;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.dom.client.Style.Unit;

import com.google.gwt.user.client.ui.*;


public class AdminPanel extends AbsolutePanel{

	public AdminPanel(){
		configPanel();
	}
	private void configPanel() {
		setWidth("100%");
		setHeight(Core.HEIGHT+10+"px");
		setStyleName("adminPanel");

		//Create a new stack layout panel.
		StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
		stackPanel.setWidth("50%");
		stackPanel.setHeight("575px");
		// Add the list User.
		/*Widget listUserPanelHeader = createHeaderWidget("Liste des utilisateurs");
		stackPanel.add(new ListeUser().onInitialize(), listUserPanelHeader, 4);*/




		// Add the list user
		Widget listUserPanelHeader = createHeaderWidget("Liste des utilisateurs");
		stackPanel.add(creerListeUserPanel(),listUserPanelHeader, 4);

		// Add the list room.
		Widget listRoomPanelHeader = createHeaderWidget("Liste des salles");
		stackPanel.add(creerListeSallePanel(), listRoomPanelHeader, 4);
		add(stackPanel);
	}

	private Widget creerListeSallePanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Liste des salles");
		return a;
	}

	private Widget creerListeUserPanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Liste des utilisateurs");
		class Contact {
			private final String pseudo;
			private final String nbBanni;
			private final String nbEjection;
			private final Date dateLast;

			public Contact(String pseudo, String nbBanni, String nbEjection,Date dateLast) {
				this.dateLast = dateLast;
				this.nbBanni = nbBanni;
				this.nbEjection = nbEjection;
				this.pseudo = pseudo;
			}

			public String getPseudo() {
				return pseudo;
			}

			/**
			 * @return
			 */
		}

		// The list of data to display.
		List<Contact> CONTACTS = Arrays.asList(new Contact("John","0","0",
				new Date(80, 4, 12)), new Contact("Mary","5","2", new Date(11, 6, 11)));

		
		class HasCellImpl implements HasCell<Contact, Contact> {
		    private ActionCell<Contact> fCell;

		    public HasCellImpl(String text, Delegate<Contact> delegate) {
		        fCell = new ActionCell<Contact>(text, delegate);
		    }

		    @Override
		    public Cell<Contact> getCell() {
		        return fCell;
		    }

		    @Override
		    public FieldUpdater<Contact, Contact> getFieldUpdater() {
		        return null;
		    }

		    @Override
		    public Contact getValue(Contact object) {
		        return object;
		    }
		}

		CellTable<Contact> table = new CellTable<Contact>();

		List<HasCell<Contact, ?>> cells = new LinkedList<HasCell<Contact, ?>>();
		cells.add(new HasCellImpl("supprimer", new Delegate<Contact>() {

			@Override
			public void execute(Contact object) {
				Window.alert(object.getPseudo());
			}
		}));

		CompositeCell<Contact> pseudoColumn = new CompositeCell<Contact>(cells);
		table.addColumn(new TextColumn<Contact>() {

			@Override
			public String getValue(Contact object) {
				return object.getPseudo();
			}
		}, "Pseudo");
		
		// Create nombre de fois ejecter column.
		TextColumn<Contact> nbEjectionColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact contact) {
				return contact.nbEjection;
			}
		};
		table.addColumn(nbEjectionColumn, "nb Ejections");

		// Create nombre de fois bannis column.
		TextColumn<Contact> nbBanniColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact contact) {
				return contact.nbBanni;
			}
		};
		table.addColumn(nbBanniColumn, "nb Bannis");
		
		
		DateCell dateLastColumn = new DateCell();
		Column<Contact, Date> dateColumn = new Column<Contact, Date>(dateLastColumn) {
			@Override
			public Date getValue(Contact object) {
				return object.dateLast;
			}
		};
		table.addColumn(dateColumn, "Date de derniere connexion");
		
		table.addColumn(new Column<Contact, Contact>(pseudoColumn) {

			@Override
			public Contact getValue(Contact object) {
				return object;
			}
		},"Suprimer User");

		// Create a data provider.
		ListDataProvider<Contact> dataProvider = new ListDataProvider<Contact>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);

		// Add the data to the data provider, which automatically pushes it to the
		// widget.
		List<Contact> list = dataProvider.getList();
		for (Contact contact : CONTACTS) {
			list.add(contact);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Contact> columnSortHandler = new ListHandler<Contact>(
				list);
		table.addColumnSortHandler(columnSortHandler);

		// Add it to the root panel.
		a.add(table);
		return a;
	}

	private static Widget createHeaderWidget(String text) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHeight("100%");
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		hPanel.add(headerText);
		return new SimplePanel(hPanel);
	}
}
