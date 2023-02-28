package com.example.gestorevini;

public class Wine {

    private String nome;
    private String produttore;
    private String provenienza;
    private int anno;
    private String note;
    private String[] vitigni_provenienza;

    public Wine(String nome, String produttore, String provenienza, int anno, String note, String[] vitigni_provenienza) {
        this.nome = nome;
        this.produttore = produttore;
        this.provenienza = provenienza;
        this.anno = anno;
        this.note = note;
        this.vitigni_provenienza = vitigni_provenienza;
    }

    //getters
    public String getNome() {return nome;}
    public String getProduttore() {return produttore;}
    public String getProvenienza() {return provenienza;}
    public int getAnno() {return anno;}
    public String getNote() {return note;}
    public String[] getVitigniProvenienza() {return vitigni_provenienza;}

    //setters
    public void setVitigniProvenienza(String[] vitigni_provenienza) {this.vitigni_provenienza = vitigni_provenienza;}
    public void setNome(String nome) {this.nome = nome;}
    public void setProduttore(String produttore) {this.produttore = produttore;}
    public void setProvenienza(String provenienza) {this.provenienza = provenienza;}
    public void setAnno(int anno) {this.anno = anno;}
    public void setNote(String note) {this.note = note;}

}

