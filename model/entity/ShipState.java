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
public enum ShipState {
    OPERATIVE("/Operative.png"),
    INOPERATIVE("/Inoperative.png");
    private String text;

    private ShipState(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
