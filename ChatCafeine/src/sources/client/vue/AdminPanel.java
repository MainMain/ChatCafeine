package sources.client.vue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import sources.client.model.User;
import sources.client.service.AdminService;
import sources.client.service.AdminServiceAsync;


import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.cell.client.NumberCell;
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

		// Add the create room.
		Widget createRoomPanelHeader = createHeaderWidget("Ajouter une salle");
		stackPanel.add(creerCreateRoomPanel(),createRoomPanelHeader, 4);
		add(stackPanel);
	}

	/**
	 * @return
	 */

	private Widget creerListeSallePanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Liste des salles");
		class Salle {
			private final String nom;
			private final String theme;
			private final int nbPlaces;

			public Salle(String nom, String theme, int nbPlaces) {
				this.nom = nom;
				this.theme = theme;
				this.nbPlaces = nbPlaces;
			}

			public String getNom() {
				return nom;
			}
		}
		
		
		
		// The list of data to display.
		List<Salle> SALLES = Arrays.asList(new Salle("geekmania","informatique",10), new Salle("naturaForm","sport",10));
		
		class HasCellImpl implements HasCell<Salle, Salle> {
			private ActionCell<Salle> fCell;

			public HasCellImpl(String text, Delegate<Salle> delegate) {
				fCell = new ActionCell<Salle>(text, delegate);
			}

			@Override
			public Cell<Salle> getCell() {
				return fCell;
			}

			@Override
			public FieldUpdater<Salle, Salle> getFieldUpdater() {
				return null;
			}

			@Override
			public Salle getValue(Salle object) {
				return object;
			}
		}
		

		CellTable<Salle> table = new CellTable<Salle>();

		List<HasCell<Salle, ?>> cells = new LinkedList<HasCell<Salle, ?>>();
		cells.add(new HasCellImpl("supprimer", new Delegate<Salle>() {

			// SUPPRIMER ICI LA SALLE SELECTIONNE DE LA BDD
			@Override
			public void execute(Salle object) {
				Window.alert(object.getNom());
			}
		}));
		
		CompositeCell<Salle> principaleColumn = new CompositeCell<Salle>(cells);
		Cell<Number> numberColumn = new NumberCell();
		Cell<String> editTextColumn = new EditTextCell();
		
		// Create nom column.
		table.addColumn(new Column<Salle, String>(editTextColumn) {
			public String getValue(Salle salle) {
	            return salle.nom;
	          }
		},"Nom");
		
		// Create theme column.
		table.addColumn(new Column<Salle, String>(editTextColumn) {
			public String getValue(Salle salle) {
	            return salle.theme;
	          }
		},"Theme");
		
		
		// Create nb place max column.
		table.addColumn(new Column<Salle, Number>(numberColumn) {

			@Override
			public Number getValue(Salle salle) {
				return salle.nbPlaces;
			}
		},"nb Places Max");
		
		// Create bouton supprimer salle column.
		table.addColumn(new Column<Salle, Salle>(principaleColumn) {

			@Override
			public Salle getValue(Salle object) {
				return object;
			}
		},"Suprimer Salle");
		
		

		// Create a data provider.
		ListDataProvider<Salle> dataProvider = new ListDataProvider<Salle>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);

		// Add the data to the data provider, which automatically pushes it to the
		// widget.
		List<Salle> list = dataProvider.getList();
		for (Salle contact : SALLES) {
			list.add(contact);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Salle> columnSortHandler = new ListHandler<Salle>(
				list);
		table.addColumnSortHandler(columnSortHandler);

		// Add it to the root panel.
		a.add(table);

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
		
		// Créer la liste des utilisateurs
		ArrayList<User> listUsers;
		AdminService.Util.getInstance().getAllUsers(new AsyncCallback<ArrayList<User>>(){
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Erreur : "+ caught.getMessage());
							}
							@Override
							public void onSuccess(ArrayList<User> result) {
								if (result==null)
									Window.alert("Erreur lors de la récupération de la liste des utilisateurs");
							}
						});
		
		

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

			// SUPPRIMER ICI LE USER SELECTIONNE DE LA BDD
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

	private Widget creerCreateRoomPanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Ajouter une salle");

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
