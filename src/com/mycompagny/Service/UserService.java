/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import static com.codename1.uikit.cleanmodern.SignInForm.staticUser;
import com.mycompany.Entite.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class UserService {
    
    private ConnectionRequest connectionRequest;
    boolean test = false;

    public void adduser(User u) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "ajoute avec succes", "ok", null);

            }
        };

        connectionRequest.setUrl("http://localhost/CRUD/insert.php?"
                + "username=" + u.getUsername()
                + "&name=" + u.getNom()
                + "&prenom=" + u.getPrenom()
                + "&password=" + u.getMdp()
                + "&email=" + u.getEmail()
                + "&adresse=" + u.getAdresse()
                + "&role=" + u.getRole()
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
     public boolean verification(String username, String password) {
        List<User> u = new ArrayList<>();

        connectionRequest = new ConnectionRequest("http://localhost/CRUD/login.php");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        Map<String, Object> result = null;


        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(connectionRequest.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                u.add(new User(
                        Integer.parseInt(obj.get("id").toString()),
                        (String) obj.get("nom"),
                        (String) obj.get("prenom"),
                        (String) obj.get("adresse"),
                        (String) obj.get("password"),
                        (String) obj.get("username"),
                        (String) obj.get("email")
                       
                ));
            }

            for (User user : u) {
                if (username.equals(user.getUsername())
                        &&password.equals(user.getMdp())) {

                    System.out.println("it works!");
                    staticUser = new User();
                    User.setConnected(user);
                    staticUser.setNd(user.getNd());
                    staticUser.setUsername(user.getUsername());
                    staticUser.setNom(user.getNom());
                    staticUser.setPrenom(user.getPrenom());
                    staticUser.setEmail(user.getEmail());
                    staticUser.setMdp(user.getMdp());
                    staticUser.setAdresse(user.getAdresse());

                    test = true;
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        System.out.println("wrong ");
        return false;

    }
    


    
}
