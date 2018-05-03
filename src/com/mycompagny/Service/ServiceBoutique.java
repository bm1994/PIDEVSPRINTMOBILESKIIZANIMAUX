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

    
}
