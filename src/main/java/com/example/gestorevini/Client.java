package com.example.gestorevini;

public class Client {
    private int IDClient;
    private String Username;
    private String Name;
    private String Surname;
    private String Cf;
    private String Email;
    private int Cell;
    private String Address;
    private String Password;

    public Client(String username,int IDClient, String nome, String cognome, String cf, String email, int cell, String indirizzoConsegna, String password) {
        this.IDClient = IDClient;
        this.Username = username;
        this.Name = nome;
        this.Surname = cognome;
        this.Cf = cf;
        this.Email = email;
        this.Cell = cell;
        this.Address = indirizzoConsegna;
        this.Password = password;
    }

    //getters
    public String getUsername() {return Username;}
    public int getIDClient() {return IDClient;}
    public String getName() {return Name;}
    public String getSurname() {return Surname;}
    public String getCf() {return Cf;}
    public String getEmail() {return Email;}
    public int getCell() {return Cell;}
    public String getAddress() {return Address;}
    public String getPassword() {return Password;}

    //setters
    public void setUsername(String username) {this.Username = username;}
    public void setIDClient(int IDClient) {this.IDClient = IDClient;}
    public void setAddress(String address) {this.Address = address;}
    public void setName(String name) {this.Name = name;}
    public void setSurname(String surname) {this.Surname = surname;}
    public void setCf(String cf) {this.Cf = cf;}
    public void setEmail(String email) {this.Email = email;}
    public void setCell(int cell) {this.Cell = cell;}
    public void setPassword(String password) {this.Password = password;}

}

