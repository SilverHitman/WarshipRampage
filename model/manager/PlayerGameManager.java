/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.manager;

import java.util.ArrayList;
import model.entity.Attack;
import model.entity.Player;
import model.entity.Positioning;
import model.entity.Ship;
import model.entity.ShipType;
import model.entity.ShipsPerTable;
import model.entity.Table;

/**
 *
 * @author Usuario
 */
public class PlayerGameManager {

    private Player player;
    private ShipsPerTable listShips;
    private Table myGameTable;
    private Table myAttackTable;
    private boolean turn;
    private PlayerGameManager team;
    private ArrayList<Attack> attackList;
    private int[] toPlaceShips;

    public PlayerGameManager(int size, Player player) {
        this.player = player;
        this.myGameTable = new Table(size, size);
        this.myAttackTable = new Table(size, size);
        this.listShips = new ShipsPerTable(myGameTable);
        this.attackList = new ArrayList<>();
        this.turn = false;
        this.team = null;
        this.toPlaceShips = new int[5];
        this.toPlaceShips[0] = listShips.getGunnerShipsNumber(myGameTable);
        this.toPlaceShips[1] = listShips.getBrigsNumber(myGameTable);;
        this.toPlaceShips[2] = listShips.getFrigatesNumber(myGameTable);;
        this.toPlaceShips[3] = listShips.getMansOfWarNumber(myGameTable);;
        this.toPlaceShips[4] = listShips.getShipsAccount(myGameTable);
    }

    public void placeAShip(Ship ship, int x, int y, Positioning positioning) {
        if (this.toPlaceShips[4] != 0) {
            ship= new Ship(ship.getType(),x,y);
            ship.setPositioning(positioning);
            if (!myGameTable.placeShip(x, y, ship, positioning)) {
                switch (ship.getType()) {
                    case GUN_SHIP:
                        if (this.toPlaceShips[0]>0) {
                            listShips.addShip(ship);
                            this.toPlaceShips[0]--;
                        }
                        break;
                    case BRIG:
                        if (this.toPlaceShips[1]>0) {
                            listShips.addShip(ship);
                            this.toPlaceShips[1]--;
                        }
                        break;
                    case FRIGATE:
                        if (this.toPlaceShips[2]>0) {
                            listShips.addShip(ship);
                            this.toPlaceShips[2]--;
                        }
                        break;
                    case MAN_OF_WAR:
                        if (this.toPlaceShips[3]>0) {
                            listShips.addShip(ship);
                            this.toPlaceShips[3]--;
                        }
                        break;
                }
                this.toPlaceShips[4]--;
            }
        }
    }

    public boolean toStartGame() {
        return this.toPlaceShips[4] == 0;
    }

    public ShipsPerTable getListShips() {
        return listShips;
    }

    public Table getMyGameTable() {
        return myGameTable;
    }

    public Player getPlayer() {
        return player;
    }

    public void attack(Attack attack) {
        if (this.turn) {
            if (this.myAttackTable.attackSection(attack)) {
                this.attackList.add(attack);
            }
        }
    }

    public ArrayList<Attack> getAttackList() {
        return attackList;
    }

    public Table getMyAttackTable() {
        return myAttackTable;
    }

    public void setMyAttackTable(Table myAttackTable) {
        this.myAttackTable = myAttackTable;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public PlayerGameManager getTeam() {
        return team;
    }

    public void setTeam(PlayerGameManager team) {
        this.team = team;
    }

    public int[] getToPlaceShips() {
        return toPlaceShips;
    }
}


