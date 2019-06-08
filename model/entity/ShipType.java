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
public enum ShipType {
    GUN_SHIP("/GunShip"),
    BRIG("/Brig"),
    FRIGATE("/Frigate"),
    MAN_OF_WAR("/ManOfWar");
    private String text;

    private ShipType(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
}
