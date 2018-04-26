/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author asus
 */
public class Produit {
    private int id;
    private String marque;
    private String nom;
    private String categorie;
    private float prix;
    private int stock;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit(int id, String marque, String nom, String categorie, float prix, int stock, String image) {
        this.id = id;
        this.marque = marque;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.stock = stock;
        this.image = image;
    }
    

    public Produit() {
    }

    public Produit(int id, String marque, String nom, String categorie, float prix, int stock) {
        this.id = id;
        this.marque = marque;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", marque=" + marque + ", nom=" + nom + ", categorie=" + categorie + ", prix=" + prix + ", stock=" + stock + '}';
    }
    
    
}
