/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author Usuario
 */
public enum Positioning {
    NO_PAINTED("No painted"),
    PAINT_VERTICAL("resources/images/ships/verticalShips"),
    PAINT_HORIZONTAL("resources/images/ships/horizontalShips");
    private String text;

    private Positioning(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
