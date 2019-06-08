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
public class Ship {

    private ShipType type;
    private int busySections;
    private int duration;
    private ShipState state;
    private Positioning positioning;
    private int x, y;

    public Ship(ShipType type) {
        this.type = type;
        this.busySections = getSize(type);
        this.duration = getSize(type);
        this.state = ShipState.OPERATIVE;
    }

    public Ship(ShipType type, int x, int y) {
        this.type = type;
        this.busySections = getSize(type);
        this.duration = getSize(type);
        this.state = ShipState.OPERATIVE;
        this.x = x;
        this.y = y;
    }

    public int getBusySections() {
        return busySections;
    }

    public void setBusySections(int busySections) {
        this.busySections = busySections;
    }

    public ShipType getType() {
        return type;
    }

    public String getShipImage() {
        return positioning.toString() + type.toString() + state.toString();
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ShipState getState() {
        return state;
    }

    private int getSize(ShipType type) {
        int size = 0;
        switch (type) {
            case GUN_SHIP:
                size = 1;
                break;
            case BRIG:
                size = 2;
                break;
            case FRIGATE:
                size = 3;
                break;
            case MAN_OF_WAR:
                size = 4;
                break;
        }
        return size;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    public Positioning getPositioning() {
        return positioning;
    }

    public void setPositioning(Positioning positioning) {
        this.positioning = positioning;
    }

    @Override
    public String toString() {
        return "Ship:\t" + "type:  " + type + ", busySections:  " + busySections + ", duration:  " + duration + ", state:  " + state + ", positioning:  " + positioning + '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }

}
