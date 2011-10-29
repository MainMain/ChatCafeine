package sources.client.vue;
import sources.client.model.User;
import sources.client.vue.ContactDatabase.Category;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ListeUser extends Widget{
	public ListeUser(){

	}
	/**
	 * The UiBinder interface used by this example.
	 */
	@UiTemplate("CwDataGrid.ui.xml")
	interface Binder extends UiBinder<Widget, CwDataGrid> {
		Widget createAndBindUi(ListeUser listeUser);
		}

	/**
	 * The main DataGrid.
	 */
	@UiField(provided = true) // Composants
	DataGrid<User> dataGrid;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager;


	/**
	 * Initialize this example.
	 */
	public Widget onInitialize() {
/*

		editableCells = new ArrayList<AbstractEditableCell<?, ?>>();
		
		// init la table avec les enigmes
		EnigmeDatabase.get();
		
		
		// Create a table.
		table = new CellTable<EnigmeInfo>(15, EnigmeInfo.KEY_PROVIDER);

		// Add the id column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return String.valueOf(object.getId());
			}
		}, "Id");
		//table.getColumn(0).setSortable(true);

		// Add the title column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getTitle();
			}
		}, "Titre");
		//table.getColumn(1).setSortable(true);
		// Add the desc column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getDescription();
			}
		}, "Description");

		// Add the date column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getCreationDate();
			}
		}, "Date");
		//table.getColumn(3).setSortable(true);
		// Add the lien column.
		table.addColumn(new Column<EnigmeInfo, String>(new ActionCell<String>("Voir", new ActionCell.Delegate<String>() {

			@Override
			public void execute(String object) {
				//pageRediriger("test.html");
				pageOuvrir(object);
			}
		} )){

			@Override
			public String getValue(EnigmeInfo object) {
				return object.getLien();
			}}, "Lien");

		addColumn(new TextInputCell(), "Ma reponse", new GetValue<String>() {
			public String getValue(EnigmeInfo eni) {
				return eni.getReponse();
			}
		}, new FieldUpdater<EnigmeInfo, String>() {
			public void update(int index, EnigmeInfo object, String value) {
				//pendingChanges.add(new LastReponseChange(object, value));
				object.setReponse(value);
				ReponseService.Util.getInstance().majReponseEnigme(FenetreConnexion.getIdU(),object.getId(),object.getReponse(),new AsyncCallback<Boolean>(){
					public void onFailure(Throwable caught) {
						Window.alert("Pb lors de la maj de la reponse avc le SGBD");
					}
					public void onSuccess(Boolean result) {
					}					
				});
				table.redraw();
			}
		});

		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				if (object.getSolution().equalsIgnoreCase(object.getReponse()))
					return "OK";
				else return "KO";
			}
		}, "Res");


		table.redraw();
		// Add the table to the database.
		
		EnigmeDatabase.get().addDataDisplay(table);

		//reponseColumn.getFieldUpdater().update(0, EnigmeDatabase.get().getDataProvider().getList().get(0), "poil");

		return table;
		
		
		
		/*
		// Create a DataGrid.

		// Définit un fournisseur clé qui fournit une clé unique pour chaque contact. Si la clé est
		// Utilisée pour identifier les contacts quand les champs (comme le nom et l'adresse)
		// Changent.
		dataGrid = new DataGrid<User>(User.KEY_PROVIDER);
		dataGrid.setWidth("100%");

		// Set the message to display when the table is empty.
		dataGrid.setEmptyTableWidget(new Label("Table vide !"));

		// "Attachez un gestionnaire de tri des colonnes à l'ListDataProvider pour trier la liste."
		//ListHandler<User> sortHandler = new ListHandler<User>(User.getDataProvider().getList());
		//dataGrid.addColumnSortHandler(sortHandler);

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(dataGrid);

		// Add a selection model so we can select cells.
		final SelectionModel<User> selectionModel =	new MultiSelectionModel<User>(ContactDatabase.User.KEY_PROVIDER);
		dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.<User> createCheckboxManager());

		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Add the CellList to the adapter in the database.
		ContactDatabase.get().addDataDisplay(dataGrid);

		// Create the UiBinder.
		Binder uiBinder = GWT.create(Binder.class);
		return uiBinder.createAndBindUi(this);
	}

	//Add the columns to the table.
	private void initTableColumns(final SelectionModel<User> selectionModel, ListHandler<User> sortHandler) {
		// Checkbox column. This table will uses a checkbox column for selection.
		// Alternatively, you can call dataGrid.setSelectionEnabled(true) to enable
		// mouse selection.
		Column<User, Boolean> checkColumn =
			new Column<User, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(User object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);

		// ******** First name.
		Column<User, String> firstNameColumn =
			new Column<User, String>(new EditTextCell()) {
			@Override
			public String getValue(User object) {
				return object.getFirstName();
			}
		};
		firstNameColumn.setSortable(true);
		sortHandler.setComparator(firstNameColumn, new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			}
		});
		dataGrid.addColumn(firstNameColumn, "Login");
		firstNameColumn.setFieldUpdater(new FieldUpdater<User, String>() {
			public void update(int index, User object, String value) {
				// Called when the user changes the value.
				object.setFirstName(value);
				ContactDatabase.get().refreshDisplays();
			}
		});
		dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);

		// ******** Last name.
		Column<User, String> lastNameColumn = new Column<User, String>(new EditTextCell()) {
			@Override
			public String getValue(User object) {
				return object.getLastName();
			}
		};
		lastNameColumn.setSortable(true);
		sortHandler.setComparator(lastNameColumn, new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getLastName().compareTo(o2.getLastName());
			}
		});
		dataGrid.addColumn(lastNameColumn, "2eme");
		lastNameColumn.setFieldUpdater(new FieldUpdater<User, String>() {
			public void update(int index, User object, String value) {
				// Called when the user changes the value.
				object.setLastName(value);
				ContactDatabase.get().refreshDisplays();
			}
		});
		dataGrid.setColumnWidth(lastNameColumn, 20, Unit.PCT);

		// ******** Age.
		Column<User, Number> ageColumn = new Column<User, Number>(new NumberCell()) {
			@Override
			public Number getValue(User object) {
				return object.getAge();
			}
		};
		lastNameColumn.setSortable(true);
		sortHandler.setComparator(ageColumn, new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getBirthday().compareTo(o2.getBirthday());
			}
		});
		Header<String> ageFooter = new Header<String>(new TextCell()) {
			@Override
			public String getValue() {
				List<User> items = dataGrid.getVisibleItems();
				if (items.size() == 0) {
					return "";
				} else {
					int totalAge = 0;
					for (User item : items) {
						totalAge += item.getAge();
					}
					return "Avg: " + totalAge / items.size();
				}
			}
		};
		dataGrid.addColumn(ageColumn, new SafeHtmlHeader(SafeHtmlUtils.fromSafeConstant("Coucou")), ageFooter);
		dataGrid.setColumnWidth(ageColumn, 7, Unit.EM);

		// ******** Category.
		final Category[] categories = ContactDatabase.get().queryCategories();
		List<String> categoryNames = new ArrayList<String>();
		for (Category category : categories) {
			categoryNames.add(category.getDisplayName());
		}
		SelectionCell categoryCell = new SelectionCell(categoryNames);
		Column<User, String> categoryColumn = new Column<User, String>(categoryCell) {
			@Override
			public String getValue(User object) {
				return object.getCategory().getDisplayName();
			}
		};
		dataGrid.addColumn(categoryColumn, "Category");
		categoryColumn.setFieldUpdater(new FieldUpdater<User, String>() {
			public void update(int index, User object, String value) {
				for (Category category : categories) {
					if (category.getDisplayName().equals(value)) {
						object.setCategory(category);
					}
				}
				ContactDatabase.get().refreshDisplays();
			}
		});
		dataGrid.setColumnWidth(categoryColumn, 130, Unit.PX);

		// ******** Address.
		Column<User, String> addressColumn = new Column<User, String>(new TextCell()) {
			@Override
			public String getValue(User object) {
				return object.getAddress();
			}
		};
		addressColumn.setSortable(true);
		sortHandler.setComparator(addressColumn, new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getAddress().compareTo(o2.getAddress());
			}
		});
		dataGrid.addColumn(addressColumn, "adresse");
		dataGrid.setColumnWidth(addressColumn, 60, Unit.PCT);
	}
	*/
		return new SimplePanel();
	}
}

