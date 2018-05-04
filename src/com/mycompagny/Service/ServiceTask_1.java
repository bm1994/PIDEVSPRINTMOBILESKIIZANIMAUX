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
import com.mycompany.Entite.Task;
import com.mycompany.Entite.User;
import com.mycompany.Entite.events;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.objects.NativeString.substr;

/**
 *
 * @author sana
 */
public class ServiceTask_1 {

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
    
    
    
    
    
    
    
      public ArrayList<User> getListveto(String json) {

        ArrayList<User> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();

                // System.out.println(obj.get("id"));
              
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setPrenom(obj.get("username").toString());
                e.setNom(obj.get("nom").toString());
                e.setAdresse(obj.get("adresse").toString());

                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    
    
    
        ArrayList<User> listuser = new ArrayList<>();

    
    
      public ArrayList<User> getList3(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mlist");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask_1 ser = new ServiceTask_1();
                listuser = ser.getListveto(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser ;
    }

      
      
      
        public String reformatDate(String date)
    {   String a="";
        for (int i=0;i<date.length();++i)
        {
            if (date.charAt(i)!='-')
            {
                a+=date.charAt(i);
            }
        }
       
        return a;
    }
      
      
       public void ajoutdemande(events ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/skiizanimauxfinale/MobileAjout.php?title="+ta.getTitre()+"&start="+ta.getStart_event()+"&end="+ta.getEnd_event();
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
     
      
       public ArrayList<events> getListevents(String json) {

        ArrayList<events> listevents = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");

            for (Map<String, Object> obj : list) {
                events e = new events();
                 
                         float id_usr = Float.parseFloat(obj.get("id_user").toString());  
                  if (id_usr==2){
        
                 float idv = Float.parseFloat(obj.get("id").toString());  
               e.setId((int) idv);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
           
                e.setTitre(obj.get("title").toString());
                         e.setColor(obj.get("color").toString());

                        
                         if((obj.get("color").toString()).equalsIgnoreCase("yellow")){
                      e.setEtat("Demaande En cours");
                      
                         }
                      else
                         {
                      e.setEtat("Valider");
    }
String s=substr(obj.get("start").toString(),0,10);

// Print what date is today!
                e.setStart_event(s);
               e.setEnd_event(s);
               
              listevents.add(e);
                 }

            }

        } catch (IOException ex) {
        }
        System.out.println(listevents);
        return listevents;

    }
    
    
    
        ArrayList<events> list1 = new ArrayList<>();

    
    
      public ArrayList<events> getListevents(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/load.php");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask_1 ser = new ServiceTask_1();
                list1 = ser.getListevents(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return list1 ;
    }
      
      
      
      
         public void delete_event(int ide) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/skiizanimauxfinale/Mobile_remove_event.php?id="+ide;
        con.setUrl(Url);

        System.out.println("remove");

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
      
      
      
}
 