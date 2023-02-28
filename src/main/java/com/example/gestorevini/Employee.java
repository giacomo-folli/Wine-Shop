package com.example.gestorevini;

public class Employee {

    private String Nome;
    private String Cognome;
    private String cf;
    private String email;
    private String cell;
    private String indirizzoResidenza;

    public Employee(String nome, String cognome, String cf, String email, String cell, String indirizzoResidenza) {
        this.Nome = nome;
        this.Cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.cell = cell;
        this.indirizzoResidenza = indirizzoResidenza;
    }

    //getters
    public String getNome() {return Nome;}
    public String getCognome() {return Cognome;}
    public String getCf() {return cf;}
    public String getEmail() {return email;}
    public String getCell() {return cell;}
    public String getIndirizzoResidenza() {return indirizzoResidenza;}

    //setters
    public void setCell(String cell) {this.cell = cell;}
    public void setNome(String nome) {this.Nome = nome;}
    public void setCognome(String cognome) {this.Cognome = cognome;}
    public void setCf(String cf) {this.cf = cf;}
    public void setEmail(String email) {this.email = email;}
    public void setIndirizzoResidenza(String indirizzoResidenza) {this.indirizzoResidenza = indirizzoResidenza;}
}
