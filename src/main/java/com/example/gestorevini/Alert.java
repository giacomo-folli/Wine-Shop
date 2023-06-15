package com.example.gestorevini;

public class Alert {
    private int ID;
    private int ID_Wine;
    private String Name;
    private String Date;
    private int Availability;

    public Alert(int ID,int ID_Wine, String Name, String Date, int Availability) {
        this.ID = ID;
        this.ID_Wine = ID_Wine;
        this.Name = Name;
        this.Date = Date;
        this.Availability = Availability;
    }

    //getters
    public int getID() { return ID; }
    public int getID_Wine() { return ID_Wine; }
    public String getName() { return Name; }
    public String getDate() { return Date; }
    public int getAvailability() { return Availability; }
    //setters
    public void setID(int ID) { this.ID = ID; }
    public void setID_Wine(int ID_Wine) { this.ID_Wine = ID_Wine; }
    public void setName(String name) { this.Name = name; }
    public void setDate(String notes) { this.Date = notes; }
    public void setAvailability(int availability) { this.Availability = availability; }
}
