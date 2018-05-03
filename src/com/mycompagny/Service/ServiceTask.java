/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Annonce;
import com.mycompany.Entite.Panier;
import com.mycompany.Entite.Produit;
import com.mycompany.Entite.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceTask {

    public void ajoutTask(Task ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://41.226.11.243:10004/tasks/" + ta.getNom() + "/" + ta.getEtat();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Task> getListTask(String json) {

        ArrayList<Task> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Task e = new Task();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setEtat(obj.get("state").toString());
                e.setNom(obj.get("name").toString());
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    ArrayList<Task> listTasks = new ArrayList<>();
    
    public ArrayList<Task> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
    
    
    
    
      public ArrayList<Produit> getListveto(String json) {

        ArrayList<Produit> listAnnonces = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                  Produit a = new Produit();

                // System.out.println(obj.get("id"));
                float aa = Float.parseFloat(obj.get("id").toString());
                a.setId((int)aa);
                float aaaa = Float.parseFloat(obj.get("stock").toString());
                a.setStock((int)aaaa);
                float aaa = Float.parseFloat(obj.get("prix").toString());
                a.setPrix((int)aaa);
               // a.setId(Integer.parseInt(obj.get("id").toString()));
                a.setCategorie(obj.get("categorie").toString());
                a.setMarque(obj.get("marque").toString());

                a.setNom(obj.get("nom").toString());
                a.setImage(obj.get("image").toString());
               // a.setDate_annonce(obj.get("date_annonce").getEditor().toString());

                System.out.println(a);
                listAnnonces.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnonces);
        return listAnnonces;

    }
    
    
    
        ArrayList<Produit> listuser = new ArrayList<>();

    
    
      public ArrayList<Produit> getList3(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobile/a");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listuser = ser.getListveto(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser ;
    }
      

      
      
      
      
      public ArrayList<Panier> getListPanier(String json) {

        ArrayList<Panier> listPanier = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                  Panier a = new Panier();

                // System.out.println(obj.get("id"));
                float aa = Float.parseFloat(obj.get("id").toString());
                a.setId((int)aa);
                
                float aaaa = Float.parseFloat(obj.get("idPersonne").toString());
                a.setIdPersonne((int)aaaa);
                
                float aaaaaa = Float.parseFloat(obj.get("idProduit").toString());
                a.setIdProduit((int)aaaaaa);

                float aaa = Float.parseFloat(obj.get("prix").toString());
                a.setPrix((int)aaa);
               // a.setId(Integer.parseInt(obj.get("id").toString()));
                a.setName(obj.get("nom").toString());


                System.out.println(a);
                listPanier.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPanier);
        return listPanier;

    }
     
      
      
      
      
      
      
      
      
      
      
}