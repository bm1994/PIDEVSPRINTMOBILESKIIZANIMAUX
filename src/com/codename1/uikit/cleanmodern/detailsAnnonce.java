/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceAnnonce;
import com.mycompany.Entite.Annonce;

/**
 *
 * @author Admin
 */
public class detailsAnnonce extends BaseForm{
     
     public detailsAnnonce(Resources res,Annonce ad) {
        super("Newsfeed", BoxLayout.y());
        
         ServiceAnnonce se = new ServiceAnnonce();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        
        addTab(photo(10, 10, ad.getPhoto_annonce()));
        
               Label nom= new Label("Nom : "+ad.getId_animal().getNom_animal());
    Label race= new Label("Race : "+ad.getId_animal().getRace_animal());
    Label age = new Label("Age : "+String.valueOf(ad.getId_animal().getAge_animal()));
    Label sexe = new Label("Sexe : "+ad.getId_animal().getSexe());
    Label poids = new Label("Poids : "+String.valueOf(ad.getId_animal().getPoids_animal()));
        
    
          
   Label username = new Label("username :"+ad.getId().getUsername());
   Label prenom = new Label("prenom :"+ad.getId().getPrenom());
   Label nom1 = new Label("nom :"+ad.getId().getNom());
   Label email = new Label("email :"+ad.getId().getEmail());
    
    nom.setUIID("leslabel");
    age.setUIID("leslabel");
    race.setUIID("leslabel");
    sexe.setUIID("leslabel");
    poids.setUIID("leslabel");
    username.setUIID("leslabel");
    prenom.setUIID("leslabel");
    nom1.setUIID("leslabel");
    email.setUIID("leslabel");
    
    Button retour =new Button("retour");
      
        Style retourStyle = new Style(retour.getUnselectedStyle());
        retourStyle.setFgColor(0xffff);
        FontImage retourImage = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_BACKSPACE , retourStyle);
        retour.setIcon(retourImage);
        retour.setTextPosition(RIGHT);
        
    retour.addActionListener(l->{
        System.out.println("button retour ");
       new AnnonceF(res).show();
        
        
     });
    
    Container c = new Container(BoxLayout.y());
//         c.add(nom);
//         c.add(race);
//         c.add(age);
//         c.add(sexe);
//         c.add(poids);
         Label infoanimal=new Label("A propos de l'animal :");
         Label infouser=new Label("A propos du proprietaire :");
            infouser.setUIID("detailsinfoanimal");
            infoanimal.setUIID("detailsinfoanimal");

         c.add(BoxLayout.encloseY(
                 BorderLayout.west(retour),
                 BorderLayout.center(infoanimal),BoxLayout.encloseX(nom,race),
                  BoxLayout.encloseX(age,sexe),poids,BorderLayout.center(infouser),
                  username,BoxLayout.encloseX(nom1,prenom),email
         ));
   
   
//               Container c1 = new Container(BoxLayout.y());
//
//        c1.add(username);
//        c1.add(prenom);
//        c1.add(nom1);
//        c1.add(email);
//        
//                 Container c2 = new Container(BoxLayout.x());
//c2.add(c);
//c2.add(c1);
//
// add(c2);
add(c);
         show();
      }
    
   
    private void addTab( Image img) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
      ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image
                
            );

        add(page1);
        show();
    }
    
     public URLImage photo(int w,int h ,String pa)
     {
         URLImage photo2 = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(h, w , 0xffff0000), true), pa,
                "http://localhost/skiizanimauxfinale/web/ressources/" + pa
        );
        photo2.fetch();
        return photo2;
}
  
}
