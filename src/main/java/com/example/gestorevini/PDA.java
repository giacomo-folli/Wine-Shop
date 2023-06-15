package com.example.gestorevini;

public class PDA {
    private int ID;
    private int idClient;
    private String wineName;
    private String wineProducer;
    private int wineYear;
    private int quantity;
    private String notes;
    private String date;

    public PDA(int ID, int idClient, String wineName, String wineProducer, int wineYear, int quantity, String notes, String date) {
        this.ID = ID;
        this.idClient = idClient;
        this.wineName = wineName;
        this.wineProducer = wineProducer;
        this.wineYear = wineYear;
        this.quantity = quantity;
        this.notes = notes;
        this.date = date;
    }

    //getters
    public int getID() { return ID; }
    public int getIdClient() { return idClient; }
    public String getWineName() { return wineName; }
    public String getWineProducer() { return wineProducer; }
    public int getWineYear() { return wineYear; }
    public int getQuantity() { return quantity; }
    public String getNotes() { return notes; }
    public String getDate() { return date; }
    //setters
    public void setID(int ID) { this.ID = ID; }
    public void setIdClient(int idClient) { this.idClient = idClient; }
    public void setWineName(String wineName) { this.wineName = wineName; }
    public void setWineProducer(String wineProducer) { this.wineProducer = wineProducer; }
    public void setWineYear(int wineYear) { this.wineYear = wineYear; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setDate(String date) { this.date = date; }
}
