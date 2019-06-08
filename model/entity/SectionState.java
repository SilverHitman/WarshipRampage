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
public enum SectionState {
    WITH_SHIP("with ship"),
    VOID("void"),
    VOID_ATTACKED("void attacked"),
    VOID_UNAVAIABLE("void unavaiable"),
    SHIP_ATTACKED("ship attacked");
    private String text;

    private SectionState(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}