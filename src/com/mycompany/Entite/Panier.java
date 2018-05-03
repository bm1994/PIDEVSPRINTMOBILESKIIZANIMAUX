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
public class Panier {
    private int id;
    private String name;
    private int idProduit;
    private int idPersonne;
    public float prix;

    public Panier(String name, int idProduit, int idPersonne, float prix) {
        this.name = name;
        this.idProduit = idProduit;
        this.idPersonne = idPersonne;
        this.prix = prix;
    }

    public Panier(int id, String name, int idProduit, int idPersonne, float prix) {
        this.id = id;
        this.name = name;
        this.idProduit = idProduit;
        this.idPersonne = idPersonne;
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Panier() {
    }

    public Panier(int id, String name, int idProduit, int idPersonne) {
        this.id = id;
        this.name = name;
        this.idProduit = idProduit;
        this.idPersonne = idPersonne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
}
