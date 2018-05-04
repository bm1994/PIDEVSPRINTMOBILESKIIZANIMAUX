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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.cleanmodern.Boutique;
import com.mycompany.Entite.Panier;
import com.mycompany.Entite.Produit;
import com.mycompany.Entite.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceBoutique {
        
    private ConnectionRequest connectionRequest;
    boolean test = false;

    public void addPanier(Panier p) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "ajoute avec succes", "ok", null);

            }
        };

        connectionRequest.setUrl("http://localhost/CRUD/AjouterPanier.php?"
                + "&name=" + p.getName()
                + "&idProduit=" + p.getIdProduit()
                + "&idPersonne=" + p.getIdPersonne()
                + "&prix=" + p.getPrix()

        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    
    
    
    
    
            ArrayList<Panier> listpanierr = new ArrayList<>();

      public ArrayList<Panier> getListPanier(int id){ 
          id=User.connected.getNd();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobile/panier/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listpanierr = ser.getListPanier(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listpanierr ;
    }
      
      
      public void settstock(int id , int stock){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CRUD/SetStock.php?id="+id+"&stock="+stock);  
      
                NetworkManager.getInstance().addToQueueAndWait(con);

    }
      
      public void supprimerPanier(int id , int idPersonne){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CRUD/DeletePanier.php?id="+id+"&idPersonne="+idPersonne);  
      
                NetworkManager.getInstance().addToQueueAndWait(con);

    }

      public void setstockPanier(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CRUD/setstockPanier.php?id="+id);  
      
                NetworkManager.getInstance().addToQueueAndWait(con);

    }
      public Produit getvetoprofile(String json) {

       Produit p = new Produit();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
  for (Map<String, Object> obj : list) {
                // System.out.println(obj.get("id"));
              
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
             float stock = Float.parseFloat(obj.get("stock").toString());               
                            p.setStock((int)stock);

            
          

            }
          

        } catch (IOException ex) {
        }
        System.out.println(p);
        return p;

    }
      Produit a = new Produit();

    
    
      public Produit getveto(int d){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CRUD/setstockPanier.php?id"+d);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceBoutique ser = new ServiceBoutique();
                a = ser.getvetoprofile(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return a ;
    }
    
}
