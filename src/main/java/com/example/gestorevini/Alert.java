package com.example.gestorevini;

public class Alert {
    private int ID;
    private String wineName;
    private String Date;

    public Alert(int ID, String wineName, String Date) {
        this.ID = ID;
        this.wineName = wineName;
        this.Date = Date;
    }

    //getters
    public int getID() { return ID; }
    public String getWineName() { return wineName; }
    public String getDate() { return Date; }
    //setters
    public void setID(int ID) { this.ID = ID; }
    public void setWineName(String wineName) { this.wineName = wineName; }
    public void setDate(String notes) { this.Date = notes; }

}
