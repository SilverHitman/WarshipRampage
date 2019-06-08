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
public class Section {

    private SectionState state;
    private Ship ship;

    public Section(SectionState state) {
        this.state = state;
    }

    public SectionState getState() {
        return state;
    }

    public void setState(SectionState state) {
        this.state = state;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void decreaseShipDuration() {
        this.ship.setDuration(this.ship.getDuration() - 1);
    }

    public boolean validateDuration() {
        boolean isToDestroy = false;
        if (this.ship.getDuration() == 0) {
            isToDestroy = true;
        }
        return isToDestroy;
    }

    public void isToDestroyShip() {
        if (validateDuration() == true) {
            this.ship.setState(ShipState.INOPERATIVE);
        }
    }

    @Override
    public String toString() {
        return "Section:" + "state: " + state.toString() + ", " + ship;
    }

}
