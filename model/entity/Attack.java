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
public class Attack {
    private int x,y;
    private boolean impact;
    public Attack(int x, int y) {
        this.x = x;
        this.y = y;
        this.impact = false;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isImpact() {
        return impact;
    }

    public void setImpact(boolean impact) {
        this.impact = impact;
    }
    
    @Override
    public String toString() {
        return  "(x:" + x + ", y=" + y + ')';
    }
    
}