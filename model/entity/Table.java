/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;

/**
 *
 * @author Brayan Pineda
 * @since 20-05-2019
 * @version 1.0
 */
public class Table {

    private Section[][] table;

    public Table(int horizontal, int vertical) {
        this.table = new Section[horizontal][vertical];
        createTable();
    }

    private void createTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                Section section = new Section(SectionState.VOID);
                table[i][j] = section;
            }
        }
    }

    public boolean attackSection(Attack attack) {
        boolean attackConfirmation = false;
        switch (table[attack.getX()][attack.getY()].getState()) {
            case VOID:
            case VOID_UNAVAIABLE:
                table[attack.getX()][attack.getY()].setState(SectionState.VOID_ATTACKED);
                attackConfirmation = true;
                break;
            case VOID_ATTACKED:
            case SHIP_ATTACKED:
                break;
            case WITH_SHIP:
                table[attack.getX()][attack.getY()].setState(SectionState.SHIP_ATTACKED);
                table[attack.getX()][attack.getY()].decreaseShipDuration();
                table[attack.getX()][attack.getY()].isToDestroyShip();
                attack.setImpact(true);
                attackConfirmation = true;
                break;
        }
        return attackConfirmation;
    }

    public boolean placeShip(int x, int y, Ship ship, Positioning positioning) {
        boolean isPlaced = false;
        switch (positioning) {
            case PAINT_VERTICAL:
                isPlaced = placeShipVertical(x, y, ship);
                ship.setPosition(x, y);
                break;
            case PAINT_HORIZONTAL:
                isPlaced = placeShipHorizontal(x, y, ship);
                ship.setPosition(x, y);
                break;
        }
        return isPlaced;
    }

    private boolean placeShipVertical(int x, int y, Ship ship) {
        int n = x;
        if (validateSectionsToPlaceVertical(x, y, ship)) {
            for (int i = 0; i < ship.getBusySections(); i++) {
                table[n][y].setShip(ship);
                n++;
            }
            disableSteadySectionsOfAShip(x, y, ship);
            disableSectionsOfShipVertical(x, y, ship.getBusySections());
        }
        return validateSectionsToPlaceVertical(x, y, ship);
    }

    private boolean placeShipHorizontal(int x, int y, Ship ship) {
        int n = y;
        if (validateSectionsToPlaceHorizontal(x, y, ship)) {
            for (int i = 0; i < ship.getBusySections(); i++) {
                table[x][n].setShip(ship);
                n++;
            }
            disableSteadySectionsOfAShip(x, y, ship);
            disableSectionsOfShipHorizontal(x, y, ship.getBusySections());
        }
        return validateSectionsToPlaceHorizontal(x, y, ship);
    }

    private boolean validateSectionsToPlaceHorizontal(int x, int y, Ship ship) {
        boolean isClear = true;
        try {
            for (int i = 0; i < ship.getBusySections(); i++) {
                switch (table[x][y + i].getState()) {
                    case SHIP_ATTACKED:
                    case VOID_ATTACKED:
                    case VOID_UNAVAIABLE:
                    case WITH_SHIP:
                        isClear = false;
                        break;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            isClear = false;
        }
        return isClear;
    }

    private boolean verifySection(int x, int y) {
        return x < table.length && x >= 0 && y < table[0].length && y >= 0;
    }

    private void disableSteadySection(int x, int y) {
        if (table[x][y].getState() != SectionState.WITH_SHIP) {
            table[x][y].setState(SectionState.VOID_UNAVAIABLE);
        } 
    }

    private void disableSteadySectionsOfAShip(int x, int y, Ship ship) {
        switch (ship.getPositioning()) {
            case PAINT_VERTICAL:
                toDisableAVerticalSteadySection(x, y, ship);
                break;
            case PAINT_HORIZONTAL:
                toDisableAHotizontalSteadySection(x, y, ship);
                break;
        }
    }

    private void toDisableAHotizontalSteadySection(int x, int y, Ship ship) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + ship.getBusySections(); j++) {
                if (verifySection(i, j)) {
                    disableSteadySection(i, j);
                }
            }
        }
    }

    private void toDisableAVerticalSteadySection(int x, int y, Ship ship) {
        for (int i = x - 1; i <= x + ship.getBusySections(); i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (verifySection(i, j)) {
                    disableSteadySection(i, j);
                }
            }
        }
    }

    private void disableSectionsOfShipHorizontal(int x, int y, int shipBusySections) {
        for (int i = y; i < y + shipBusySections; i++) {
            table[x][i].setState(SectionState.WITH_SHIP);
        }
    }

    private void disableSectionsOfShipVertical(int x, int y, int shipBusySections) {
        for (int i = x; i < x + shipBusySections; i++) {
            table[i][y].setState(SectionState.WITH_SHIP);
        }
    }

    private boolean validateSectionsToPlaceVertical(int x, int y, Ship ship) {
        boolean isClear = true;
        try {
            for (int i = 0; i < ship.getBusySections(); i++) {
                switch (table[x + i][y].getState()) {
                    case SHIP_ATTACKED:
                    case VOID_ATTACKED:
                    case VOID_UNAVAIABLE:
                    case WITH_SHIP:
                        isClear = false;
                        break;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            isClear = false;
        }
        return isClear;
    }

    public String toStringTable() {
        String tablePrinter = "";
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                tablePrinter += table[i][j].toString() + "\tTHIS IS A SPACE" + "\t";
            }
            tablePrinter += "\n";
        }
        return tablePrinter;
    }

    public String toStringNumberTable() {
        String numberTablePrinter = "";
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                numberTablePrinter += toNumberTable(table[i][j]) + "\t ";
            }
            numberTablePrinter += "\n";
        }
        return numberTablePrinter;
    }

    public int toNumberTable(Section section) {
        int number = -2;
        switch (section.getState()) {
            case VOID:
                number = 0;
                break;
            case WITH_SHIP:
                number = 1;
                break;
            case VOID_UNAVAIABLE:
                number = 2;
                break;
            case SHIP_ATTACKED:
                number = 3;
                break;
            case VOID_ATTACKED:
                number = 4;
                break;
        }
        return number;
    }

    public String indicateActions() {
        return "0. vacia\n"
                + "1. con barco\n"
                + "2. vacia no disponible\n"
                + "3. barco atacado\n"
                + "4. vacia atacada\n";
    }

    public Section[][] getTable() {
        return table;
    }

}
