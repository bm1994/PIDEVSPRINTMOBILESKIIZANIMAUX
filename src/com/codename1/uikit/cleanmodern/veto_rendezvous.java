/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.veterinaire;
import com.mycompagny.Service.ServiceTask;
import com.mycompagny.Service.ServiceTask_1;
import com.mycompany.Entite.events;

/**
 *
 * @author habib
 */
public class veto_rendezvous {
        Form fs;

 
    TextField tnom,sujet,titre,lieu,date,datef;
    TextField tetat;
    Button btnajout,btnaff,listaff;
        public veto_rendezvous(Resources res) {
        fs = new Form();
        titre = new TextField();
 
        date = new TextField();
     datef = new TextField();

        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        titre.setHint("Titre");
  
          date.setHint("Date");
        datef.setHint("Datefin");
                titre.setUIID("TextFieldBlack");
                                date.setUIID("TextFieldBlack");
                datef.setUIID("TextFieldBlack");

        fs.add(titre);
        fs.add(date);
                fs.add(datef);

        fs.add(btnajout);
        fs.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServiceTask_1 se = new ServiceTask_1();
            
            events ee=new events();
                   ee.setTitre(titre.getText());
            ee.setStart_event(se.reformatDate(date.getText()));
                     ee.setEnd_event(se.reformatDate(datef.getText()));

     
         
            System.out.println(ee.getEnd_event());
            se.ajoutdemande(ee);
            //ser.ajoutTask(t);
        });
        btnaff.addActionListener((e)->{
        veterinaire a=new veterinaire(res);
        a.show();
        });
    }
        public veto_rendezvous(Form f) {
        this.fs = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

    public TextField getSujet() {
        return sujet;
    }

    public void setSujet(TextField sujet) {
        this.sujet = sujet;
    }

    public TextField getTitre() {
        return titre;
    }

    public void setTitre(TextField titre) {
        this.titre = titre;
    }

    public TextField getLieu() {
        return lieu;
    }

    public void setLieu(TextField lieu) {
        this.lieu = lieu;
    }

    public TextField getDate() {
        return date;
    }

    public void setDate(TextField date) {
        this.date = date;
    }

    public TextField getDatef() {
        return datef;
    }

    public void setDatef(TextField datef) {
        this.datef = datef;
    }

    public TextField getTetat() {
        return tetat;
    }

    public void setTetat(TextField tetat) {
        this.tetat = tetat;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

    public Button getListaff() {
        return listaff;
    }

    public void setListaff(Button listaff) {
        this.listaff = listaff;
    }

    public Form getF() {
        return fs;
    }

}
