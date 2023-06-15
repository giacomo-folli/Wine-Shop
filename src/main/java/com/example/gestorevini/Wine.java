package com.example.gestorevini;
public class Wine {

    public int IDWine;
    public String nome;
    public String produttore;
    public String provenienza;
    public int prezzo;
    public int anno;
    public String note;
    public String vitigniProvenienza;
    public int num;

    public Wine(int IDWine, String nome, String produttore, String provenienza, int anno, String note, String vitigniProvenienza, int prezzo, int num) {
        this.IDWine = IDWine;
        this.nome = nome;
        this.produttore = produttore;
        this.provenienza = provenienza;
        this.anno = anno;
        this.note = note;
        this.vitigniProvenienza = vitigniProvenienza;
        this.prezzo = prezzo;
        this.num = num;
    }

    //getters
    public int getIDWine() {return this.IDWine;}
    public String getNome() {return this.nome;}
    public String getProduttore() {return this.produttore;}
    public String getProvenienza() {return this.provenienza;}
    public int getAnno() {return this.anno;}
    public String getNote() {return this.note;}
    public int getPrezzo() {return this.prezzo;}
    public int getNum() {return this.num;}
    public String getVitigniProvenienza() {return this.vitigniProvenienza;}

    //setters
    public void setIDWine(int IDWine) {this.IDWine = IDWine;}
    public void setVitigniProvenienza(String vitigniProvenienza) {this.vitigniProvenienza = vitigniProvenienza;}
    public void setNome(String nome) {this.nome = nome;}
    public void setProduttore(String produttore) {this.produttore = produttore;}
    public void setProvenienza(String provenienza) {this.provenienza = provenienza;}
    public void setAnno(int anno) {this.anno = anno;}
    public void setPrezzo(int prezzo) {this.prezzo = prezzo;}
    public void setNote(String note) {this.note = note;}
    public void setNum(int num) {this.num = num;}

}

