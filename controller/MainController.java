package controller;

import com.google.gson.Gson;
import com.uptc.pgr3.clientWarShipRampage.comunication.Conection;
import com.uptc.pgr3.clientWarShipRampage.comunication.IObserver;
import com.uptc.pgr3.clientWarShipRampage.exceptions.Exceptions;
import com.uptc.pgr3.clientWarShipRampage.exceptions.MyException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import model.entity.Player;
import model.entity.Positioning;
import model.entity.SectionState;
import model.entity.Ship;
import model.entity.ShipType;
import model.entity.ShipsPerTable;
import model.entity.Table;
import controller.DialogCustomGame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Attack;
import model.entity.ShipState;
import model.manager.PlayerManager;
import model.manager.PlayerGameManager;
import util.MessageDialog;
import util.Messages;
import view.MainFrame;
import view.info.GraphShip;
import view.info.MarkX;

public class MainController implements ActionListener, MouseListener, IObserver {

    private MainFrame mainFrame;
    private PlayerManager playerManager;
    private MessageDialog messageDialog;
    private String word;
    private Player actualPlayer;

    // este talbe es al que se le esta agregndo los barcos al crear el tablero y a la vez el que se esta graficando
    // entoces se puede setear una nueva tablas para graficar los barcos en ella y a su vez graficar los aciertos y fallos
    private Table tableActual;
    private ShipsPerTable shipertable;
    private PlayerGameManager playerGame;
    private DialogCustomGame dilogGame;
    private ArrayList listVisualShips;
    private ArrayList listmark;
    private Exceptions exceptions;
    private Conection conection;
    private int lastClickX, widthTable;
    private int lastClickY, heighttable;
    private String typeBoatLink;
    private Positioning positionig;
    private Ship tempShip;
    private ShipType tempShipTipe;
    private Gson gson;
    //de estos booleanos depende si al dar cliec en la ventana es ataque o registro de barco
    boolean positioningShipPlayer, attackingCell, specialGame;

    public MainController() throws IOException {

        exceptions = new Exceptions();
        conection = new Conection("localhost", 11, this);
        conection.start();

        typeBoatLink = "";
        lastClickX = 0;
        lastClickY = 0;
        widthTable = 0;
        heighttable = 0;
        tempShipTipe = ShipType.GUN_SHIP;
        dilogGame = new DialogCustomGame(this);
        tempShip = null;
        shipertable = null;
        positioningShipPlayer = false;
        attackingCell = false;
        positionig = Positioning.PAINT_VERTICAL;

        listVisualShips = new ArrayList<>();
        listmark = new ArrayList<>();

        playerManager = new PlayerManager();
        gson = new Gson();
        mainFrame = new MainFrame(this);
        messageDialog = new MessageDialog(mainFrame);
    }

    private void showMessage(String message) {
        messageDialog.setMessage(message);
        messageDialog.setVisible(true);
    }

    @Override
    public void addCaptchaMessage(BufferedImage captcha) {
        mainFrame.setCaptchaImage(captcha);
        addSingInPanel();
    }

    private void addSingInPanel() {
        mainFrame.addSignInPanel();
    }

    @Override
    public void receiveValidationRegistry(boolean userName, boolean captcha) {

        try {
            exceptions.existUserName(userName);
            exceptions.wrongCaptcha(captcha);
            conection.sentInitValues(mainFrame.getSignInUserName(), mainFrame.getSingInPass());
            playerManager.login(mainFrame.getLoginUserName(), mainFrame.getLoginPassword());
            showMessage("Bienvenido");
        } catch (IOException e) {
        } catch (MyException e) {
            showMessage(e.getMessage());
        }
    }

    @Override
    public void receiveValidationLogin(long points) {
        try {
            exceptions.wrongPlayer(points);

            addNewGamePanel();
            playerManager.login(mainFrame.getLoginUserName(), mainFrame.getLoginPassword());

        } catch (MyException e) {
            showMessage(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Actions.valueOf(e.getActionCommand())) {
            case SHOW_SIGN_IN_PANEL:
                showSignInPanel();
                break;
            case SHOW_BEST_PLAYERS:
                showBestPlayers();
                break;
            case SHOW_LOGIN_IN_PANEL:
                showLoginPanel();
                break;
            case LOGIN:
                login();
                break;
            case LOG_OUT:
                logOut();
                break;
            case CUSTOM_GAME:
                customGame();
                break;
            case CLASIC_GAME:
                clasicGame();
                break;
            case SING_IN:
                singIn();
                break;
            case SHIP_PRESSED:
                typeBoatLink = (((JButton) (e.getSource())).getName());

                if (typeBoatLink.contains("horizontalShips")) {
                    positionig = Positioning.PAINT_HORIZONTAL;
                } else {
                    positionig = Positioning.PAINT_VERTICAL;
                }
                //System.out.println(typeBoatLink);
                break;
            case RENDITION:
                mainFrame.addNewGamePanel();
                break;
            case CREATE_CUSTOM_GAME:
                newCustomGame();
                break;

            default:
                break;
        }
    }

    private void login() {
        try {
            conection.requesValidateLogin(mainFrame.getLoginUserName(), mainFrame.getLoginPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void singIn() {
        try {
            exceptions.emptyFields(mainFrame.getSignInUserName());
            exceptions.emptyFields(mainFrame.getSingInPass());
            exceptions.emptyFields(mainFrame.getVerifyPass());
            exceptions.emptyFields(mainFrame.getCaptcha());
            exceptions.validatePasswords(mainFrame.getSingInPass(), mainFrame.getVerifyPass());
            exceptions.passWordSize(mainFrame.getSingInPass());
            conection.requestvalidateRegistry(mainFrame.getSignInUserName(), mainFrame.getCaptcha());
        } catch (IOException e) {
        } catch (MyException e) {
            showMessage(e.getMessage());
        }
    }
    // aqui agrego las naves de forma logia   
    //veririfico cuando no es posible   

    public void addingNewShipToTable() {
        //aqui verifico que el clic este dentro del cuandro
        if (!(lastClickX < 1 || lastClickX > widthTable + 1)) {
            if (!(lastClickY < 1 || lastClickY > heighttable + 1)) {
                getTypeBoatFromTempShipLink();
                tempShip = new Ship(tempShipTipe);
                tempShip.setPosition(lastClickX, lastClickY);
                tempShip.setPositioning(positionig);

//                if(veryficatePlaceForShip(tempShipTipe)){
                playerGame.placeAShip(tempShip, lastClickX, lastClickY, positionig);
                if (itsFull() == true) {
                    itsFull();
                    positioningShipPlayer = false;
                    showCannon();
                }
//                }
            }
        }

    }

    public boolean itsFull() {
        boolean itsBad = false;

        int max = playerGame.getToPlaceShips()[4];
        int num = 0;
        num = num + playerGame.getToPlaceShips()[0];

        num = num + playerGame.getToPlaceShips()[1];
        num = num + playerGame.getToPlaceShips()[2];
        num = num + playerGame.getToPlaceShips()[3];
        if (max == 0) {
            itsBad = true;
        }
        return itsBad;
    }

    // guardo las naves grafica para ser mostrada luego
    public void createVisualEnemyShips(ArrayList<Ship> listShips) {
        listVisualShips.removeAll(listVisualShips);
        //System.out.println("" + playerTable.getGameTable().toStringNumberTable());
        String numSgips = "";

        for (Ship shiip : listShips) {
            if (attackingCell) {
                if (shiip.getState() == ShipState.INOPERATIVE) {
                    listVisualShips.add(new GraphShip(shiip.getX(), shiip.getY(),
                            getWidthFromLink(shiip.getShipImage()),
                            getHeigthFromLink(shiip.getShipImage()),
                            shiip.getShipImage()));
                }
            } else {
                listVisualShips.add(new GraphShip(shiip.getX(), shiip.getY(),
                        getWidthFromLink(shiip.getShipImage()),
                        getHeigthFromLink(shiip.getShipImage()),
                        shiip.getShipImage()));
                numSgips += "|";
            }
        }
    }

    public void createVisualOwnShips(ArrayList<Ship> listShips) {
        listVisualShips.removeAll(listVisualShips);
        //System.out.println("" + playerTable.getGameTable().toStringNumberTable());
        String numSgips = "";

        for (Ship shiip : listShips) {
            listVisualShips.add(new GraphShip(shiip.getX(), shiip.getY(),
                    getWidthFromLink(shiip.getShipImage()),
                    getHeigthFromLink(shiip.getShipImage()),
                    shiip.getShipImage()));
        }
    }

    // se necesita el panel donde vvan la naves
    @Override
    public void mouseClicked(MouseEvent e) {

        lastClickX = (int) mainFrame.getPanelmap().getPostTableX(e.getX());
        lastClickY = (int) mainFrame.getPanelmap().getPostTableY(e.getY());

//        //si no es true no agrega naves
        if (positioningShipPlayer) {

            addingNewShipToTable();
            //System.out.println(tableActual.toStringTable());

            createVisualEnemyShips(playerGame.getListShips().getPlayerShips());
            //aqui grafico los aciertos y fallos en la tabla
            createVisualMarks(playerGame.getMyAttackTable());
        }

        createVisualEnemyShips(playerGame.getListShips().getPlayerShips());

        // si esta en true registaria el ataque, se envia last click x y Y, de momento no puedo enviarlo al servidor
        //para los taques especiales seria envia un listado de cooordenadas
        if (attackingCell) {
            playerGame.attack(new Attack(lastClickX, lastClickY));

        }

        //esta infromacion llegaria por server, puede que el mismo jugador repita turno
        String namePlayer1 = "player 1";
        String namePlayer2 = "player 2";
        String nameActulaTurn = "player 1";

        mainFrame.showInfoTurn(namePlayer1, namePlayer2, nameActulaTurn);
        // estos metodos cargan visualmente los barcos y marcas
        //se recomienda cargar los datos del campo enemigo visual y no logicamente ya que el servidor verifica los impactos
        mainFrame.getPanelmap().setShips(listVisualShips);

        mainFrame.getPanelmap().setListMarks(listmark);

        // aqui se camibia los datos de barcos restante en pntalla
        mainFrame.setNumbersShips("|1x1| x " + playerGame.getToPlaceShips()[0],
                "|1x2| x " + playerGame.getToPlaceShips()[1],
                "|1x3| x " + playerGame.getToPlaceShips()[2],
                "|1x4| x " + playerGame.getToPlaceShips()[3]);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void customGame() {

        dilogGame.setVisible(true);
    }

    private void logOut() {
        mainFrame.addLoginPanel();
    }

    private void addNewGamePanel() {
        mainFrame.addNewGamePanel();
    }

    private void showLoginPanel() {
        mainFrame.addLoginPanel();
    }

    private void showBestPlayers() {
        mainFrame.addPanelScores();
    }

    public void showSignInPanel() {
        try {
            conection.requestGetCaptcha();
        } catch (IOException e) {
        }
    }

    private void clasicGame() {
        messageDialog.setMessage(Messages.INSTRUCTIONS.toString());
        messageDialog.setVisible(true);
        positioningShipPlayer = true;
        widthTable = 15;
        heighttable = 10;

        playerGame = new PlayerGameManager(heighttable, playerManager.getLoginPlayer());
        //shipertable = new ShipsPerTable(playerGame.getMyGameTable());
        mainFrame.addPanelBtn();
        mainFrame.setNumbersShips("|1x1| x " + playerGame.getToPlaceShips()[0],
                "|1x2| x " + playerGame.getToPlaceShips()[1],
                "|1x3| x " + playerGame.getToPlaceShips()[2],
                "|1x4| x " + playerGame.getToPlaceShips()[3]);
        mainFrame.addPanelMainShips(heighttable - 1, heighttable - 1, this, this);
        boolean positioningShipPlayer = true;

    }

    private void showCannon() {
        if (specialGame) {
            mainFrame.addSpecialCannon();

        } else {
            mainFrame.addCannon();
            
            
            try {
                conection.requestPlayerGame(gson.toJson(playerGame));
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        playerGame.toStartGame();
        attackingCell = true;

    }

    public void getTypeBoatFromTempShipLink() {
        tempShipTipe = ShipType.GUN_SHIP;

        if (typeBoatLink.contains("Brig")) {
            tempShipTipe = ShipType.BRIG;
        } else if (typeBoatLink.contains("Frigate")) {
            tempShipTipe = ShipType.FRIGATE;
        } else if (typeBoatLink.contains("ManOfWar")) {
            tempShipTipe = ShipType.MAN_OF_WAR;
        }

    }

    public int getHeigthFromLink(String link) {
        int heigth = 1;
        if (link.contains("verticalShips")) {
            if (link.contains("Brig")) {
                heigth = 2;
            } else if (link.contains("Frigate")) {
                heigth = 3;
            } else if (link.contains("ManOfWar")) {
                heigth = 4;
            } else {
                heigth = 1;
            }
        }
        return heigth;
    }

    public int getWidthFromLink(String link) {
        int width = 1;
        if (link.contains("horizontalShips")) {
            if (link.contains("Brig")) {
                width = 2;
            } else if (link.contains("Frigate")) {
                width = 3;
            } else if (link.contains("ManOfWar")) {
                width = 4;
            } else {
                width = 1;
            }
        }

        return width;
    }

    private void createVisualMarks(Table talbe) {
        listmark.removeAll(listmark);
        for (int i = 0; i < talbe.getTable().length - 1; i++) {
            for (int j = 0; j < talbe.getTable()[0].length - 1; j++) {
                if (talbe.getTable()[i][j].getState() == SectionState.SHIP_ATTACKED) {
                    listmark.add(new MarkX(i, j, true));
                }
                if (talbe.getTable()[i][j].getState() == SectionState.VOID_ATTACKED) {
                    listmark.add(new MarkX(i, j, false));
                }
                if (playerGame.getMyGameTable().getTable()[i][j].getShip() == null) {
                    listmark.add(new MarkX(i, j, true));
                }
            }
        }
    }

    private void newCustomGame() {
        messageDialog.setMessage(Messages.INSTRUCTIONS.toString());
        messageDialog.setVisible(true);
        positioningShipPlayer = true;
        widthTable = (int) dilogGame.getWidthDialog();
        heighttable = (int) widthTable - (dilogGame.getWidthDialog() / 3);
        specialGame = true;

        tableActual = new Table(widthTable, widthTable);
        playerGame = new PlayerGameManager(widthTable, playerManager.getLoginPlayer());

        shipertable = new ShipsPerTable(playerGame.getMyGameTable());
        mainFrame.addPanelBtn();
        mainFrame.setNumbersShips("|1x1| x " + playerGame.getToPlaceShips()[0],
                "|1x2| x " + playerGame.getToPlaceShips()[1],
                "|1x3| x " + playerGame.getToPlaceShips()[2],
                "|1x4| x " + playerGame.getToPlaceShips()[3]);
        mainFrame.addPanelMainShips(widthTable - 1, widthTable - 1, this, this);
        boolean positioningShipPlayer = true;
    }

}
