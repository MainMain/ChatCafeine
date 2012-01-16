/**
 * 
 */
package sources.client.vue.coffeeRoom;

import sources.client.model.PaquetCom;
import sources.client.model.User;
import sources.client.service.SalleService;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import sources.client.vue.Core;
/**
 * @author : Johan
 *
 */
public class VueSalle extends AbsolutePanel {
	private int[][] matriceSalle = 
		new int[12][10];
	FlexTable flextable;
	FlowPanel flowpanel;
	private int cptVueSalle = 0;
	private ListUserPanel listUserPanel;

	public VueSalle(ListUserPanel a, int nbPlaces){
		matriceSalle = ListeMatriceSalle.getMatriceSalle(nbPlaces);
		listUserPanel = a;
		setHeight("100%");
		setWidth("100%");
		setStyleName("vueSalle");

		flextable = new FlexTable();
		flextable.clear();
		flowpanel = new FlowPanel();
		flextable.setWidget(1, 0, flowpanel);
		flowpanel.setSize("100%", "100%");
		flextable.setCellSpacing(0);
		add(flextable);

		// CREATION DE LA VUE DE LA SALLE
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 12; j++)
				flextable.setWidget(i, j,(new Case(matriceSalle[i][j],i ,j )));

		//System.out.println("Nouvelle connexion !");

		// RECUPERATION DU COMPTEUR SALLE DU SERVEUR
		SalleService.Util.getInstance().getCptSalle(Core.userEnCours.getIdSalleEnCours(), new AsyncCallback<Integer>(){
			@Override
			public void onFailure(Throwable caught) {
				//System.out.println("erreur !");
			}
			@Override
			public void onSuccess(Integer result) {
				cptVueSalle = result;
			}
		});

		// RECUPERATION DE LA MATRICE DES USERS
		SalleService.Util.getInstance().getMatriceUser(Core.userEnCours.getIdSalleEnCours(),
				cptVueSalle, new AsyncCallback<User[][]>(){
					@Override
					public void onFailure(Throwable caught) {
					}
					@Override
					public void onSuccess(User[][] result) {
						for (int i = 0; i < 10; i++)
							for (int j = 0; j < 12; j++){
								if (result[i][j] != null){
									flextable.setWidget(i,j,
											new SiegeButton("images/"+result[i][j].getCheminAvatar(),i,j,true));
								}
							}
						refresh();
					}
				});
	}

	/*
	 * METHODE DE RAFRAICHISSEMENT POUR DEPLACEMENTS
	 */
	private void refresh() {
		SalleService.Util.getInstance().getNewMatrice(Core.userEnCours.getIdSalleEnCours(),
				cptVueSalle, new AsyncCallback<PaquetCom>() {			
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(PaquetCom pc) {
				if (pc != null){
					// SI USER EJECTE
					if (pc.getUserAEjecter() != null)
						if (!pc.getUserAEjecter().equals(Core.userEnCours.getLogin())){
							// Coder ici l'ejection
							System.out.println("[Client] : Ejection demandé !");
							SalleService.Util.getInstance().envoiMessageFromClient(Core.userEnCours.getIdSalleEnCours(),
									Core.userEnCours.getLogin()+" à été éjecté la salle", 
									"Message automatique", new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									CoffeeRoomPanel.getInstance().creerEcranChoixSalle();
									Core.userEnCours.sortirFromSalle();
								}
								@Override
								public void onFailure(Throwable caught) {
								}
							});
						}

					if (pc.getIdSalleDestination() == Core.userEnCours.getIdSalleEnCours()){
						cptVueSalle++;
						if (pc.getX_case() > -1 && pc.getY_case() > -1)
							flextable.setWidget(pc.getX_case(), pc.getY_case(),
									new SiegeButton("images/"+pc.getImgUser(), pc.getX_case(), 
											pc.getX_case(),true));
						// SI IL VIENT D'UNE AUTRE PLACE
						if (pc.getX_last() > -1 && pc.getY_last() > -1){
							System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^ " +pc.getSiege_last());
							if(pc.getSiege_last())
								flextable.setWidget(pc.getX_last(), pc.getY_last(),
										new SiegeButtonModo("images/siege2_modo.png", pc.getX_last(), 
												pc.getY_last(),false));
							else
								flextable.setWidget(pc.getX_last(), pc.getY_last(),
										new SiegeButton("images/siege2.png", pc.getX_last(), 
												pc.getY_last(),false));
						}
						// SI LISTE UTILISATEUR A METTRE A JOUR
						if (pc.getListeUtilisateurs() != null){
							System.out.println("[Client - Vue] "+Core.userEnCours.getIdSalleEnCours()+
							" : Liste des utilisateurs mise à jour !");
							listUserPanel.maj(pc.getListeUtilisateurs());
						}
						// SI CAFE PRIS PAR UN MEMBRE
						if (pc.isCafePris()){

						}
					}
				}
				if (Core.userEnCours.isInSalle()) refresh();			// Arreter la récursivité s'il n'est plus dans une salle
			}
		});

	}

	public class Case extends AbsolutePanel {
		public Case(int a, int x, int y){
			setSize("54px", "54px");
			if (a == 1) { setStyleName("caseSol"); }
			else if (a == 2) { add(new SiegeButton("images/siege2.png",x,y,false)); 	}
			else if (a == 3) { add(new DecoButton("/images/table2.png"));  	}
			else if (a == 4) { add(new CafeButton()); 		}
			else if (a == 5) { add(new SortieButton()); 	}
			else if (a == 6) { add(new DecoButton("/images/plante2.png")); 	}
			else if (a == 7) { add(new DecoButton("/images/lampe1.png")); 	}
			else if (a == 8) { add(new SiegeButtonModo("/images/siege2_modo.png", x, y, false)); 	}
		}
	}

	public class SiegeButton extends Image{
		private int x_case;
		private int y_case;
		// Les 2 attributs suivants serviront si l'user change de place.
		private int x_last = -1;
		private int y_last = -1;
		private boolean occupee = false;

		public SiegeButton(String cheminImg, int x, int y, boolean oqp){
			super(cheminImg);
			setSize("54px", "54px");
			setStyleName("caseSol");
			occupee = oqp;
			x_case = x;
			y_case = y;
			//setStyleName("caseSiege");

			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					System.out.println("Clic sur une place ! 1");
					if (!occupee){
						//System.out.println(occupee);
						System.out.println("Clic sur une place ! 2");
						x_last = Core.userEnCours.getPos_x();							// On sauvegarde les anciennes positions...
						y_last = Core.userEnCours.getPox_y();							/// ... De l'utilisateur
						Core.userEnCours.setPos_x(x_case);								// On lui attribut celles....
						Core.userEnCours.setPox_y(y_case);								// De la place qu'il vient de prendre
						ChatBoxPanel.activerBoutonEnvoi();								// Activation du bouton d'envoi
						if (!Core.userEnCours.isInstalle()){
							SalleService.Util.getInstance().envoiMessageFromClient(			// Envoi du message pour prévenir
									Core.userEnCours.getIdSalleEnCours(),Core.userEnCours.getLogin()+" vient de s'installer", 
									"Message automatique", new AsyncCallback<Void>(){
										@Override
										public void onFailure(Throwable caught) {
										}
										@Override
										public void onSuccess(Void result) {
											System.out.println("Clic sur une place ! 3");
										}
									});
						}
						//System.out.println(x_case+" , "+ y_case+" , "+ x_last+" , "+ y_last);
						SalleService.Util.getInstance().sinstaller(Core.userEnCours.getIdSalleEnCours(),						// Prévenir serveur
								x_case, y_case, x_last, y_last, Core.userEnCours.getTypeSiegeInstalle(), Core.userEnCours, 
								new AsyncCallback<Boolean>(){
							@Override
							public void onFailure(Throwable caught) {
							}
							@Override
							public void onSuccess(Boolean result) {
								Core.userEnCours.setTypeSiegeInstalle(false);
								System.out.println("Clic sur une place ! 4");
							}
						});
						Core.userEnCours.sinstaller();									// User noté comme installé
					}
				}
			});
		}
	}

	public class SiegeButtonModo extends Image{
		private int x_case;
		private int y_case;
		// Les 2 attributs suivants serviront si l'user change de place.
		private int x_last = -1;
		private int y_last = -1;
		private boolean occupee = false;

		public SiegeButtonModo(String cheminImg, int x, int y, boolean oqp){
			super(cheminImg);
			setSize("54px", "54px");
			setStyleName("caseSol");
			occupee = oqp;
			x_case = x;
			y_case = y;
			//setStyleName("caseSiege");
			if (!Core.userEnCours.getDroit().equals("utilisateur")){
				addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (!occupee){
							System.out.println(Core.userEnCours.getDroit());


							x_last = Core.userEnCours.getPos_x();							// On sauvegarde les anciennes positions...
							y_last = Core.userEnCours.getPox_y();							/// ... De l'utilisateur
							Core.userEnCours.setPos_x(x_case);								// On lui attribut celles....
							Core.userEnCours.setPox_y(y_case);								// ... De la place qu'il vient de prendre
							ChatBoxPanel.activerBoutonEnvoi();								// Activation du bouton d'envoi
							if (!Core.userEnCours.isInstalle()){
								SalleService.Util.getInstance().envoiMessageFromClient(			// Envoi du message pour prévenir
										Core.userEnCours.getIdSalleEnCours(),Core.userEnCours.getLogin()+" vient de s'installer", 
										"Message automatique", new AsyncCallback<Void>(){
											@Override
											public void onFailure(Throwable caught) {
											}
											@Override
											public void onSuccess(Void result) {
											}
										});
							}
							//System.out.println(x_case+" , "+ y_case+" , "+ x_last+" , "+ y_last);
							SalleService.Util.getInstance().sinstaller(Core.userEnCours.getIdSalleEnCours(),						// Prévenir serveur
									x_case, y_case, x_last, y_last, Core.userEnCours.getTypeSiegeInstalle(), Core.userEnCours, 
									new AsyncCallback<Boolean>(){
								@Override
								public void onFailure(Throwable caught) {
								}
								@Override
								public void onSuccess(Boolean result) {
									Core.userEnCours.setTypeSiegeInstalle(true);
								}
							});
							Core.userEnCours.sinstaller();									// User noté comme installé
						}
					}
				});
			}
		}
	}

	public class CafeButton extends Image{
		public CafeButton(){
			super("images/MaC1.png");
			setSize("100%", "100%");
			setStyleName("caseSol");
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Core.userEnCours.getCompteurChat() < 5){
						Core.userEnCours.quitterLaPlace();
						int x_last = Core.userEnCours.getPos_x();							// On sauvegarde les anciennes positions...
						int y_last = Core.userEnCours.getPox_y();							/// ... De l'utilisateur
						Core.userEnCours.setPos_x(-1);
						Core.userEnCours.setPox_y(-1);
						Core.userEnCours.prendreCafe();
						ChatBoxPanel.desactiverBoutonEnvoi();
						SalleService.Util.getInstance().envoiMessageFromClient(
								Core.userEnCours.getIdSalleEnCours(),
								Core.userEnCours.getLogin()+" va prendre un café", 
								"Message automatique", new AsyncCallback<Void>(){
									@Override
									public void onFailure(Throwable caught) {
									}
									@Override
									public void onSuccess(Void result) {
									}
								});
						SalleService.Util.getInstance().prendre1Cafe(Core.userEnCours.getIdSalleEnCours(),
								x_last, y_last, Core.userEnCours.getLogin(), new AsyncCallback<Boolean>() {
							@Override
							public void onFailure(Throwable caught) {
							}
							@Override
							public void onSuccess(Boolean result) {
							}
						});
					}else Window.alert("Votre dose en caféine est encore trop élevée !");
				}
			});
		}
	}

	public class SortieButton extends Image{
		public SortieButton(){
			super("/images/porte2_bis.png");
			setSize("100%", "100%");
			setStyleName("caseSol");;
			addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ChatBoxPanel.desactiverBoutonEnvoi();
					System.out.println("[Client - Vue] "+Core.userEnCours.getIdSalleEnCours()
							+" : Sortie de "+Core.userEnCours.getLogin());
					SalleService.Util.getInstance().sortie1User(Core.userEnCours.getIdSalleEnCours(),
							Core.userEnCours, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
						}
						@Override
						public void onFailure(Throwable caught) {
						}
					});
					SalleService.Util.getInstance().envoiMessageFromClient(Core.userEnCours.getIdSalleEnCours(),
							Core.userEnCours.getLogin()+" à quitté la salle", 
							"Message automatique", new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
						}
						@Override
						public void onFailure(Throwable caught) {
						}
					});
					CoffeeRoomPanel.getInstance().creerEcranChoixSalle();
					Core.userEnCours.sortirFromSalle();
				}
			});
		}
	}
	public class DecoButton extends Image{
		public DecoButton(String a){
			super(a);
			setSize("100%", "100%");
			setStyleName("caseSol");;

		}
	}
}