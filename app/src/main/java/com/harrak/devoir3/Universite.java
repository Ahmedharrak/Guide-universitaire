package com.harrak.devoir3;

public class Universite {

    private int id;
    private String nom;
    private String ville;
    private byte[] image;
    private String email;
    private String adresse;
    private int tel;
    private String eta;



    public Universite(int id, String nom, String ville, byte[] image, String email,String adresse,int tel,String eta) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.image = image;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.eta = eta;
    }


    public Universite(String nom, String ville, byte[] image,String email,String adresse,int tel,String eta) {
        this.nom = nom;
        this.ville = ville;
        this.image = image;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.eta = eta;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

}
