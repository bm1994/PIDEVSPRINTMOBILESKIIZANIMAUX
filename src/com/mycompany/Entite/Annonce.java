/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author Admin
 */
public class Annonce {
    private int id_annonce;
    private String titre_annonce;
    private String description;
    private String date_annonce;
    private String photo_annonce;
    
    private String type_annonce;
   private Animal id_animal;
   private User id;

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getTitre_annonce() {
        return titre_annonce;
    }

    public void setTitre_annonce(String titre_annonce) {
        this.titre_annonce = titre_annonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(String date_annonce) {
        this.date_annonce = date_annonce;
    }

    public String getPhoto_annonce() {
        return photo_annonce;
    }

    public void setPhoto_annonce(String photo_annonce) {
        this.photo_annonce = photo_annonce;
    }

    public String getType_annonce() {
        return type_annonce;
    }

    public void setType_annonce(String type_annonce) {
        this.type_annonce = type_annonce;
    }

    public Animal getId_animal() {
        return id_animal;
    }

    public void setId_animal(Animal id_animal) {
        this.id_animal = id_animal;
    }

    public User getId() {
        return id;
    }

    public void setId(User id) {
        this.id = id;
    }

    public Annonce() {
    }

    public Annonce(int id_annonce, String titre_annonce, String description, String date_annonce, String photo_annonce, String type_annonce, Animal id_animal, User id) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description = description;
        this.date_annonce = date_annonce;
        this.photo_annonce = photo_annonce;
        this.type_annonce = type_annonce;
        this.id_animal = id_animal;
        this.id = id;
    }
   
    
}
