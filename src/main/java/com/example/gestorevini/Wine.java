package com.example.gestorevini;

public class Wine {

    private String nome;
    private String produttore;
    private String provenienza;
    private String prezzo;
    private int anno;
    private String note;
    private String vitigniProvenienza;

    public Wine(String nome, String produttore, String provenienza, int anno, String note, String vitigniProvenienza, String prezzo) {
        this.nome = nome;
        this.produttore = produttore;
        this.provenienza = provenienza;
        this.anno = anno;
        this.note = note;
        this.vitigniProvenienza = vitigniProvenienza;
        this.prezzo = prezzo;
    }

    //getters
    public String getNome() {return this.nome;}
    public String getProduttore() {return this.produttore;}
    public String getProvenienza() {return this.provenienza;}
    public int getAnno() {return this.anno;}
    public String getNote() {return this.note;}
    public String getPrezzo() {return this.prezzo;}
    public String getVitigniProvenienza() {return this.vitigniProvenienza;}

    //setters
    public void setVitigniProvenienza(String vitigniProvenienza) {this.vitigniProvenienza = vitigniProvenienza;}
    public void setNome(String nome) {this.nome = nome;}
    public void setProduttore(String produttore) {this.produttore = produttore;}
    public void setProvenienza(String provenienza) {this.provenienza = provenienza;}
    public void setAnno(int anno) {this.anno = anno;}
    public void setPrezzo(String prezzo) {this.prezzo = prezzo;}
    public void setNote(String note) {this.note = note;}

}

