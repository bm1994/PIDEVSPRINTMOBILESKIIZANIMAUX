/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author habib
 */
public class events {
     private String titre;
    private String start_event;
    private String end_event;
     private String color;
      private int id;
      private int id_user;
      private int id_veto;
     private String etat;

    public events(String titre, String start_event, String end_event, String color, int id, int id_user, int id_veto, String etat) {
        this.titre = titre;
        this.start_event = start_event;
        this.end_event = end_event;
        this.color = color;
        this.id = id;
        this.id_user = id_user;
        this.id_veto = id_veto;
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getStart_event() {
        return start_event;
    }

    public void setStart_event(String start_event) {
        this.start_event = start_event;
    }

    public String getEnd_event() {
        return end_event;
    }

    public void setEnd_event(String end_event) {
        this.end_event = end_event;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_veto() {
        return id_veto;
    }

    public void setId_veto(int id_veto) {
        this.id_veto = id_veto;
    }

    public events() {
    }

    public events(String titre, String start_event, String end_event, String color, int id, int id_user, int id_veto) {
        this.titre = titre;
        this.start_event = start_event;
        this.end_event = end_event;
        this.color = color;
        this.id = id;
        this.id_user = id_user;
        this.id_veto = id_veto;
    }
}
