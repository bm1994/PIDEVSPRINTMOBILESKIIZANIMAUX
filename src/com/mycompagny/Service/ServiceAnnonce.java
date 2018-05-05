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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Animal;
import com.mycompany.Entite.Annonce;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class ServiceAnnonce {
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
    
    
    
    
    
    
    
      public ArrayList<Annonce> getListAdoption(String json) {

        ArrayList<Annonce> listAnnonces = new ArrayList<>();

        try {
            //System.out.println(json);
            JSONParser j = new JSONParser();
           

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
           // System.out.println(list);
            for (Map<String, Object> obj : list) {
                  Annonce a = new Annonce();
                  Animal an = new Animal();
                  User u=new User();
                 //System.out.println(obj.get("id"));
              
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                a.setTitre_annonce(obj.get("titreAnnonce").toString());
                a.setDescription(obj.get("descriptionAnnonce").toString());
                a.setId_annonce((int)Float.parseFloat(obj.get("idAnnonce").toString()));
            
                
                
                a.setPhoto_annonce(((LinkedHashMap)obj.get("idAnimal")).get("image").toString());
                an.setNom_animal(((LinkedHashMap)obj.get("idAnimal")).get("nom").toString());
               
                float age1 = Float.parseFloat(((LinkedHashMap)obj.get("idAnimal")).get("age").toString());
               an.setAge_animal((int) age1);
               float poids1 = Float.parseFloat(((LinkedHashMap)obj.get("idAnimal")).get("poids").toString());
                an.setPoids_animal((int) poids1);
                an.setRace_animal(((LinkedHashMap)obj.get("idAnimal")).get("race").toString());
                an.setSexe(((LinkedHashMap)obj.get("idAnimal")).get("sexe").toString());
                
                
                u.setNom(((LinkedHashMap)obj.get("id")).get("nom").toString());
                u.setPrenom(((LinkedHashMap)obj.get("id")).get("prenom").toString());
                u.setEmail(((LinkedHashMap)obj.get("id")).get("email").toString());
                u.setUsername(((LinkedHashMap)obj.get("id")).get("username").toString());
                u.setAdresse(((LinkedHashMap)obj.get("id")).get("adresse").toString());
                
                a.setId(u);

            a.setId_animal(an);

    System.out.println(((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString().substring(0,9 ));

    
        String f1 = ((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString().substring(0,1 );
                String f2 = ((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString().substring(2,9 );
                String f3 = f1+f2+"00";
                System.out.println(f3);
                System.out.println(f3);
                 Long s1 = Long.parseLong(f3);
                 Long s2 = Long.parseLong("86400");
                 Long s3 = s1+s2;
                 Long s4 = s3*1000;
                 System.out.println("****************"+s4 );
             //   System.out.println((Long)Long.parseLong(f3)*1000+24*3600);
        Date d = new Date(s4);
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(f.format(d));

          a.setDate_annonce(f.format(d));
 //Date d = new Date(Long.parseLong(((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString())*1000);
       // DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'");
        //System.out.println(f.format(d));
              //  a.setDate_annonce(d.toString());
               // a.setDate_annonce(obj.get("date_annonce").getEditor().toString());


                listAnnonces.add(a);

            }

        } catch (IOException ex) {
        }
       // System.out.println(listAnnonces);
        return listAnnonces;

    }
    
    
    
        ArrayList<Annonce> listuser = new ArrayList<>();

    
    
      public ArrayList<Annonce> getList4(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/Madoption");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonce ser = new ServiceAnnonce();
                listuser = ser.getListAdoption(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser ;
    }
      
      
        public ArrayList<Annonce> getList5(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/Maccouplement");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonce ser = new ServiceAnnonce();
                listuser = ser.getListAdoption(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser ;
    }

          public ArrayList<Annonce> getList6(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/Mvente");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonce ser = new ServiceAnnonce();
                listuser = ser.getListAdoption(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser ;
    }
          
          
          //***********************************************************************************************************************
          
          
          
             public ArrayList<Animal> getListMesAnimaux(String json) {

        ArrayList<Animal> listAnimal = new ArrayList<>();

        try {
           // System.out.println(json);
            JSONParser j = new JSONParser();
           

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
           // System.out.println(list);
            for (Map<String, Object> obj : list) {
                 
                  Animal an = new Animal();
                  float id = Float.parseFloat(obj.get("idAnimal").toString());
                  System.out.println(id);
                  an.setId_animal((int) id);
                 //System.out.println(obj.get("id"));
              
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                an.setNom_animal(obj.get("nom").toString());
                float age1 = Float.parseFloat(obj.get("age").toString());
               an.setAge_animal((int) age1);
            
                an.setRace_animal(obj.get("race").toString());
                float poids1 = Float.parseFloat(obj.get("poids").toString());
                an.setPoids_animal((int) poids1);
                an.setSexe(obj.get("sexe").toString());
                an.setImage(obj.get("image").toString());
 
              

    
 //Date d = new Date(Long.parseLong(((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString())*1000);
       // DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'");
        //System.out.println(f.format(d));
              //  a.setDate_annonce(d.toString());
               // a.setDate_annonce(obj.get("date_annonce").getEditor().toString());


                listAnimal.add(an);

            }

        } catch (IOException ex) {
        }
      //  System.out.println(listAnimal);
        return listAnimal;

    }
    
    
    
        ArrayList<Animal> listMesanimaux1 = new ArrayList<>();

    
          
          
          
          //*******************************************************************
      
 
        
         
         public ArrayList<Animal> getListAnimal(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/Maffichage/"+User.connected.getNd());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonce ser = new ServiceAnnonce();
                listMesanimaux1 = ser.getListMesAnimaux(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMesanimaux1 ;
    } 
        
         
         
         public void ajoutAnnonce(Annonce a)
    { System.out.println("* debut *");
         ConnectionRequest con = new ConnectionRequest();
         System.out.println("test1");
        String Url = "http://localhost/skiizanimauxfinale/web/app_dev.php/MFormulaireAnnonce/?titre="+a.getTitre_annonce()+"&description="+a.getDescription()+"&image="+a.getPhoto_annonce()+"&idu="+a.getId().getNd()+"&ida="+a.getId_animal().getId_animal()+"&type="+a.getType_annonce() ;
        System.out.println(Url);
        con.setUrl(Url);
        System.out.println("test2");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
         
         
         
                 public void modifierAnnonce(Annonce a)
    { System.out.println("* debut *");
         ConnectionRequest con = new ConnectionRequest();
         System.out.println("test1");
        String Url = "http://localhost/skiizanimauxfinale/web/app_dev.php/mobilemodifierAnnonce/?titre="+a.getTitre_annonce()+"&description="+a.getDescription()+"&ida="+a.getId_annonce()+"&type="+a.getType_annonce() ;
        System.out.println(Url);
        con.setUrl(Url);
        System.out.println("test2");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
         
         
         
            public void ajoutAnimal(Animal a)
    { System.out.println("* debut *");
         ConnectionRequest con = new ConnectionRequest();
         System.out.println("test1");
        String Url = "http://localhost/skiizanimauxfinale/web/app_dev.php/MFormulaireAnimal/?nom="+a.getNom_animal()+"&race="+a.getRace_animal()+"&image="+a.getImage()+"&idu="+a.getId_Utilisateur().getNd()+"&sexe="+a.getSexe()+"&type="+a.getType_animal()+"&poids="+a.getPoids_animal()+"&age="+a.getAge_animal() ;
        System.out.println(Url);
        con.setUrl(Url);
        System.out.println("test2");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          
   
  
             public ArrayList<Annonce> getListMesAnnnonces(String json) {

        ArrayList<Annonce> listMAnnonce = new ArrayList<>();

        try {
           // System.out.println(json);
            JSONParser j = new JSONParser();
           

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
           // System.out.println(list);
            for (Map<String, Object> obj : list) {
                 
                  Annonce an = new Annonce();
                  Animal a = new Animal();
                  
                  
                  User u = new User();
                  u.setNd((int) Float.parseFloat(((LinkedHashMap)obj.get("id")).get("id").toString()));
                   a.setId_animal((int) Float.parseFloat(((LinkedHashMap)obj.get("idAnimal")).get("idAnimal").toString()));
                  
                  an.setId_annonce((int) Float.parseFloat(obj.get("idAnnonce").toString()));
                  System.out.println(an.getId_annonce());
                an.setTitre_annonce(obj.get("titreAnnonce").toString());
                an.setDescription(obj.get("descriptionAnnonce").toString());
            
                
                
                an.setPhoto_annonce(obj.get("image").toString());
               
                an.setId(u);

            an.setId_animal(a);

    System.out.println(((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString().substring(0,9 ));

    
        String f1 = ((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString().substring(0,1 );
                String f2 = ((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString().substring(2,9 );
                String f3 = f1+f2+"00";
                System.out.println(f3);
                System.out.println(f3);
                 Long s1 = Long.parseLong(f3);
                 Long s2 = Long.parseLong("86400");
                 Long s3 = s1+s2;
                 Long s4 = s3*1000;
                 System.out.println("****************"+s4 );
             //   System.out.println((Long)Long.parseLong(f3)*1000+24*3600);
        Date d = new Date(s4);
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(f.format(d));

          an.setDate_annonce(f.format(d));

    
 //Date d = new Date(Long.parseLong(((LinkedHashMap)obj.get("dateAnnonce")).get("timestamp").toString())*1000);
       // DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'");
        //System.out.println(f.format(d));
              //  a.setDate_annonce(d.toString());
               // a.setDate_annonce(obj.get("date_annonce").getEditor().toString());


                listMAnnonce.add(an);

            }

        } catch (IOException ex) {
        }
      //  System.out.println(listAnimal);
        return listMAnnonce;

    }
    
    
    
        ArrayList<Annonce> listMesAnnonces1 = new ArrayList<>();

    
          
          
          
          //*******************************************************************
      
 
        
         
         public ArrayList<Annonce> getListMAnnonce(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/MaffichageMesAnnonce/"+User.connected.getNd());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonce ser = new ServiceAnnonce();
                listMesAnnonces1 = ser.getListMesAnnnonces(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMesAnnonces1 ;
    } 
             
         
           public void supprimerAnimal(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobilesupprimeranimal/" + id );  
      
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    } 
   
              public void supprimerAnnoce(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobilesupprimerannonce/" + id );  
      
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    } 
     
                 public void aimerannonce(int ida,int idu){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobileaimer/" + ida+"/"+idu );  
      
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    } 
      public void annuleraimerannonce(int ida,int idu){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobileannuleraimer/" + ida+"/"+idu );  
      
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    } 
      
   public boolean liked(int ida,int idu) {
       
        ArrayList<Integer> list12 = new ArrayList<>();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobileverifjaime/"+ida+"/"+idu);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   // System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                       
                        
                        float nbr = Float.parseFloat(obj.get("total").toString());
                    //    int id=Integer.parseInt(obj.get("id").toString());
                     
                       
                        list12.add((int) nbr);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         if(list12.get(0)==0)
         {
             return false;
         }else{
             return true;
         }
    }   
      
  public int nbrlike(int ida) {
       
        ArrayList<Integer> list12 = new ArrayList<>();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/skiizanimauxfinale/web/app_dev.php/mobilecountaimer/"+ida);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   // System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                       
                        
                        float nbr = Float.parseFloat(obj.get("total").toString());
                    //    int id=Integer.parseInt(obj.get("id").toString());
                     
                       
                        list12.add((int) nbr);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       return list12.get(0);
    }
        
      
       
}
