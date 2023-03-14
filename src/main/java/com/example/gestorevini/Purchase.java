package com.example.gestorevini;

public class Purchase {
    private int ID;
    private int IDBuyer;
    private String wineName;
    private int wineQuantity;
    private int winePrice;
    private String cardName;
    private String cardNumber;

    public Purchase(int id, int idBuyer, String wineName, int wineQuantity, int winePrice, String cardName, String cardNumber) {
        this.ID = id;
        this.IDBuyer = idBuyer;
        this.wineName = wineName;
        this.wineQuantity = wineQuantity;
        this.winePrice = winePrice;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
    }

    //getters
    public int getID() { return ID; }
    public int getIDBuyer() { return IDBuyer; }
    public String getWineName() { return wineName; }
    public int getWineQuantity() { return wineQuantity; }
    public int getWinePrice() { return winePrice; }
    public String getCardName() { return cardName; }
    public String getCardNumber() { return cardNumber; }

    //setters
    public void setID(int id) { this.ID = id; }
    public void setIDBuyer(int idBuyer) { this.IDBuyer = idBuyer; }
    public void setWineName(String wineName) { this.wineName = wineName; }
    public void setWineQuantity(int wineQuantity) { this.wineQuantity = wineQuantity; }
    public void setWinePrice(int winePrice) { this.winePrice = winePrice; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

}
