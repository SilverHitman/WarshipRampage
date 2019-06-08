/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ShipsPerTable {

    private ArrayList<Ship> playerShips;

    public ShipsPerTable(Table table) {
        this.playerShips = new ArrayList<>();
    }

    public int getSectionAccount(Table table) {
        return table.getTable().length * table.getTable()[0].length;
    }

    public int getGunnerShipsNumber(Table table) {
        return (int) ((getSectionAccount(table) * 4) / 100);
    }

    public int getBrigsNumber(Table table) {
        return (int) ((getSectionAccount(table) * 3) / 100);
    }

    public int getFrigatesNumber(Table table) {
        return (int) ((getSectionAccount(table) * 2) / 100);
    }

    public int getMansOfWarNumber(Table table) {
        return (int) ((getSectionAccount(table) * 1) / 100);
    }

    public void addShip(Ship ship) {
        this.playerShips.add(ship);
    }

    public int getShipsAccount(Table table) {
        return getGunnerShipsNumber(table) + getBrigsNumber(table)
                + getFrigatesNumber(table)
                + getMansOfWarNumber(table);
    }

    public ArrayList<Ship> getPlayerShips() {
        return playerShips;
    }

    public String toStringPlayerShips() {
        String playerShipsPrintable = "";
        for (Ship playerShip : playerShips) {
            playerShipsPrintable += playerShip.toString();
        }
        return playerShipsPrintable;
    }

}
