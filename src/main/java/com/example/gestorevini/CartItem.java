package com.example.gestorevini;

public class CartItem {
    private int IDCart;
    private int IDBuyer;
    private String WineName;
    private String NameProducer;
    private int WineQuantity;
    private int Price;
    private int Year;

    public CartItem(int IDCart, int IDBuyer, String WineName, String NameProducer, int WineQuantity, int Price, int Year) {
        this.IDCart = IDCart;
        this.IDBuyer = IDBuyer;
        this.WineName = WineName;
        this.NameProducer = NameProducer;
        this.WineQuantity = WineQuantity;
        this.Price = Price;
        this.Year = Year;
    }

    //getters
    public int getIDCart() { return IDCart; }
    public int getIDBuyer() { return IDBuyer; }
    public String getWineName() { return WineName; }
    public String getNameProducer() { return NameProducer; }
    public int getWineQuantity() { return WineQuantity; }
    public int getPrice() { return Price; }
    public int getYear() { return Year; }

    //setters
    public void setIDCart(int IDCart) { this.IDCart = IDCart; }
    public void setIDBuyer(int IDBuyer) { this.IDBuyer = IDBuyer; }
    public void setWineName(String WineName) { this.WineName = WineName; }
    public void setNameProducer(String NameProducer) { this.NameProducer = NameProducer; }
    public void setWineQuantity(int WineQuantity) { this.WineQuantity = WineQuantity; }
    public void setPrice(int Price) { this.Price = Price; }
    public void setYear(int Year) { this.Year = Year; }
}