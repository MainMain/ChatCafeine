package sources.client.vue.administration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sources.client.model.Salle;
import sources.client.model.User;
import sources.client.service.AdminService;
import sources.client.service.CompteService;
import sources.client.service.SalleService;
import sources.client.vue.Core;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;


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
		//stackPanel.setWidth("1310px");
		//stackPanel.setHeight("575px");
		stackPanel.setWidth("100%");
		stackPanel.setHeight("700px");

		// Add the list user
		Widget presentationPanelHeader = createHeaderWidget("Présentation de l'administration");
		stackPanel.add(creerPresentationPanel(),presentationPanelHeader, 4);


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

	private ArrayList<Salle> listeSalles = new ArrayList<Salle>();

	private Widget creerPresentationPanel(){
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Présentation de l'administration");
		HTML textFiche = new HTML();
		textFiche.setHTML("<h3>Vous pouvez à partir de cet onglet : </h3> <BR><h5>- supprimer un utilisateur </h5><BR><h5> - supprimer une salle </h5><BR><h5> - modifier le nom et le thème d'une salle</h5> <BR><h5> - Ajouter une nouvelle Salle</h5>");
		a.add(textFiche);
		return a;
	}

	private Widget creerListeSallePanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Liste des salles");

		final CellTable<Salle> table = new CellTable<Salle>();

		// RECUPERER LES INSTANCES DES SALLES
		SalleService.Util.getInstance().getToutesSalles(new AsyncCallback<ArrayList<Salle>>() {
			@Override
			public void onSuccess(ArrayList<Salle> result) {
				if (result==null)
					Window.alert("Erreur lors de la récupération de la liste des utilisateurs");
				listeSalles = result;
				// The list of data to display.
				List<Salle> SALLES = listeSalles;
				// Create a data provider.
				ListDataProvider<Salle> dataProvider = new ListDataProvider<Salle>();

				// Connect the table to the data provider.
				dataProvider.addDataDisplay(table);

				// Add the data to the data provider, which automatically pushes it to the
				// widget.
				List<Salle> list = dataProvider.getList();
				for (Salle salle : SALLES) {
					list.add(salle);
				}
				// Add a ColumnSortEvent.ListHandler to connect sorting to the
				// java.util.List.
				ListHandler<Salle> columnSortHandler = new ListHandler<Salle>(
						list);
				table.addColumnSortHandler(columnSortHandler);
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Erreur lors de la récupération de la liste des salles");
			}
		});

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
				return salle.getNom();
			}
		},"Nom");

		// Create theme column.
		table.addColumn(new Column<Salle, String>(editTextColumn) {
			public String getValue(Salle salle) {
				return salle.getTheme();
			}
		},"Theme");


		// Create nb place max column.
		table.addColumn(new Column<Salle, Number>(numberColumn) {

			@Override
			public Number getValue(Salle salle) {
				return salle.getNbrPlaceMax();
			}
		},"nb Places Max");

		// Create bouton supprimer salle column.
		table.addColumn(new Column<Salle, Salle>(principaleColumn) {

			@Override
			public Salle getValue(Salle object) {
				return object;
			}
		},"Suprimer Salle");


		// Add it to the root panel.
		a.add(table);

		return a;
	}

	private ArrayList<User> listeUtilisateurs = new ArrayList<User>();
	CellTable<User> table = new CellTable<User>();
	private Widget creerListeUserPanel() {
		SimplePanel a = new SimplePanel();
		setWidth("100%");
		setHeight("100%");
		a.isVisible();
		a.setTitle("Liste des utilisateurs");	

		AdminService.Util.getInstance().getAllUsers(new AsyncCallback<ArrayList<User>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erreur : "+ caught.getMessage());
			}
			@Override
			public void onSuccess(ArrayList<User> result) {
				if (result==null)
					Window.alert("Erreur lors de la récupération de la liste des utilisateurs");
				listeUtilisateurs = result;
				// The list of data to display.
				List<User> USERS = listeUtilisateurs;
				// Create a data provider.
				ListDataProvider<User> dataProvider = new ListDataProvider<User>();

				// Connect the table to the data provider.
				dataProvider.addDataDisplay(table);

				// Add the data to the data provider, which automatically pushes it to the
				// widget.
				List<User> list = dataProvider.getList();
				for (User user : USERS) {
					list.add(user);
				}
				// Add a ColumnSortEvent.ListHandler to connect sorting to the
				// java.util.List.
				ListHandler<User> columnSortHandler = new ListHandler<User>(
						list);
				table.addColumnSortHandler(columnSortHandler);
			}

		});

		class HasCellImpl implements HasCell<User, User> {
			private ActionCell<User> fCell;

			public HasCellImpl(String text, Delegate<User> delegate) {
				fCell = new ActionCell<User>(text, delegate);
			}

			@Override
			public Cell<User> getCell() {
				return fCell;
			}

			@Override
			public FieldUpdater<User, User> getFieldUpdater() {
				return null;
			}

			@Override
			public User getValue(User object) {
				return object;
			}
		}

		List<HasCell<User, ?>> cells = new LinkedList<HasCell<User, ?>>();
		cells.add(new HasCellImpl("supprimer", new Delegate<User>() {

			// SUPPRIMER ICI LE USER SELECTIONNE DE LA BDD
			@Override
			public void execute(User object) {
				Window.alert(object.getLogin());
			}
		}));

		CompositeCell<User> pseudoColumn = new CompositeCell<User>(cells);
		table.addColumn(new TextColumn<User>() {

			@Override
			public String getValue(User object) {
				return object.getLogin();
			}
		}, "Pseudo");

		// Create nombre de fois ejecter column.
		Cell<Number> nbEjectionColumn = new NumberCell();
		table.addColumn(new Column<User, Number>(nbEjectionColumn) {

			@Override
			public Number getValue(User user) {
				return user.getNbEjections();
			}
		},"nb Ejections");

		// Create nombre de fois bannis column.
		Cell<Number> nbBanniColumn = new NumberCell();
		table.addColumn(new Column<User, Number>(nbBanniColumn) {

			@Override
			public Number getValue(User user) {
				return user.getNbBannissements();
			}
		},"nb Bannis");

		// En format data aux cas ou on modifie :
		/*DateCell dateLastColumn = new DateCell();
		Column<User, Date> dateColumn = new Column<User, Date>(dateLastColumn) {
			@Override
			public Date getValue(User object) {
				return object.getDateLastConnexion();
			}
		};
		table.addColumn(dateColumn, "Date de derniere connexion");
		 */

		TextColumn<User> dateLastColumn = new TextColumn<User>() {
			@Override
			public String getValue(User user) {
				return user.getDateLastConnexion();
			}
		};
		table.addColumn(dateLastColumn, "Date de derniere connexion");

		table.addColumn(new Column<User, User>(pseudoColumn) {

			@Override
			public User getValue(User object) {
				return object;
			}
		},"Suprimer User");

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

		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		layout.setWidth("350px");
		layout.setHeight("450px");
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		layout.setHTML(0, 0, "<br><i>Il ne vous suffira que de remplir ces infos pour vous créer une nouvelle salle <hr>");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		final HTML error2HTML = new HTML("");

		// Create TextBox
		final TextBox nomSalle2Box = new TextBox();
		final TextBox theme2Box = new TextBox();
		final TextBox description2Box = new TextBox();

		// Add a drop box with the list types
		final ListBox nbPlaceBox = new ListBox(false);
		for (int i = 4; i <= 49; i++) {
			nbPlaceBox.addItem(""+i+" places");
		}

		// Add some standard form options
		layout.setHTML(1, 0, "Nom:");
		layout.setWidget(1, 1, nomSalle2Box);
		layout.setHTML(2, 0, "Theme");
		layout.setWidget(2, 1, theme2Box);
		layout.setHTML(3, 0, "Description");
		layout.setWidget(3, 1, description2Box);
		layout.setHTML(4, 0, "Nombre de places");
		layout.setWidget(4, 1, nbPlaceBox);
		layout.setWidget(5, 1, new Button(
				"Ajouter Salle", new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (casesRemplies()){
							int nbplace = Integer.parseInt(nbPlaceBox.getItemText(nbPlaceBox.getSelectedIndex()).substring(0,2));

							SalleService.Util.getInstance().creerSalle(nomSalle2Box.getText(), theme2Box.getText(),
									description2Box.getText(),nbplace, new AsyncCallback<Boolean>(){
								public void onFailure(Throwable caught) {
									Window.alert("Erreur : "+ caught.getMessage());
								}
								public void onSuccess(Boolean result) {
									if (result==null)
										Window.alert("Erreur lors de l'ajout de la salle !");
								}
							});
						}else error2HTML.setHTML("<font color=\"#FF00\"><em><small>Erreur : Vous n'avez " +
						"pas rempli tous les champs !</small></em></font>");
					}

					private boolean casesRemplies() {
						return (nomSalle2Box.getText().length()!=0 && 
								theme2Box.getText().length()!=0 && 
								description2Box.getText().length()!=0
						);
					}
				}));
		layout.setWidget(6, 0, error2HTML);

		a.add(layout);


		return a;
	}

	private static Widget createHeaderWidget(String text) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHeight("100%");
		hPanel.setWidth("100%");
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//hPanel.add(new Image(image));
		HTML headerText = new HTML(text);
		hPanel.add(headerText);
		return new SimplePanel(hPanel);
	}

}
