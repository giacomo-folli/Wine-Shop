package com.example.gestorevini;

public class Client {

    private String Nome;
    private String Cognome;
    private String cf;
    private String email;
    private int cell;
    private String Residenza;

    public Client(String nome, String cognome, String cf, String email, int cell, String indirizzoConsegna) {
        this.Nome = nome;
        this.Cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.cell = cell;
        this.Residenza = indirizzoConsegna;
    }

    //getters
    public String getNome() {return Nome;}
    public String getCognome() {return Cognome;}
    public String getCf() {return cf;}
    public String getEmail() {return email;}
    public int getCell() {return cell;}
    public String getResidenza() {return Residenza;}

    //setters
    public void setResidenza(String residenza) {this.Residenza = residenza;}
    public void setNome(String nome) {this.Nome = nome;}
    public void setCognome(String cognome) {this.Cognome = cognome;}
    public void setCf(String cf) {this.cf = cf;}
    public void setEmail(String email) {this.email = email;}
    public void setCell(int cell) {this.cell = cell;}

}

