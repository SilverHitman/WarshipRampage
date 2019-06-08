package model.entity;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String userPassword;
    private boolean onLine;
    private boolean isAvaiableToPlay;
    private long points; 
    
    public Player(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.points=0;
        this.onLine = false;
        this.isAvaiableToPlay = false;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}