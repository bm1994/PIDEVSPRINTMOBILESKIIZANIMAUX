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
public class Animal {
    private int id_animal;
     private String nom_animal;
    private int age_animal;
    private String type_animal;
    private String race_animal;
    private float poids_animal;
    private String sexe;
    private String image;
    private User id_Utilisateur;

    public Animal() {
    }

    public User getId_Utilisateur() {
        return id_Utilisateur;
    }

    public void setId_Utilisateur(User id_Utilisateur) {
        this.id_Utilisateur = id_Utilisateur;
    }

    public Animal(String nom_animal, int age_animal, String type_animal, String race_animal, float poids_animal, String sexe, String image) {
        this.nom_animal = nom_animal;
        this.age_animal = age_animal;
        this.type_animal = type_animal;
        this.race_animal = race_animal;
        this.poids_animal = poids_animal;
        this.sexe = sexe;
        this.image = image;
    }

    public Animal(int id_animal, String nom_animal, int age_animal, String type_animal, String race_animal, float poids_animal, String sexe, String image) {
        this.id_animal = id_animal;
        this.nom_animal = nom_animal;
        this.age_animal = age_animal;
        this.type_animal = type_animal;
        this.race_animal = race_animal;
        this.poids_animal = poids_animal;
        this.sexe = sexe;
        this.image = image;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getNom_animal() {
        return nom_animal;
    }

    public void setNom_animal(String nom_animal) {
        this.nom_animal = nom_animal;
    }

    public int getAge_animal() {
        return age_animal;
    }

    public void setAge_animal(int age_animal) {
        this.age_animal = age_animal;
    }

    public String getType_animal() {
        return type_animal;
    }

    public void setType_animal(String type_animal) {
        this.type_animal = type_animal;
    }

    public String getRace_animal() {
        return race_animal;
    }

    public void setRace_animal(String race_animal) {
        this.race_animal = race_animal;
    }

    public float getPoids_animal() {
        return poids_animal;
    }

    public void setPoids_animal(float poids_animal) {
        this.poids_animal = poids_animal;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    
}
