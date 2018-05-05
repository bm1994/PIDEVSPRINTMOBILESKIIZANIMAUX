/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Animal;
import com.codename1.ui.ComboBox;
import java.util.Map;
import java.lang.Object;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.mycompagny.Service.ServiceAnnonce;
import com.mycompagny.Service.UserService;
import com.mycompany.Entite.Annonce;
import com.mycompany.Entite.User;
/**
 *
 * @author Admin
 */
public class AjoutAnnonceForm extends BaseForm{
Label im=new Label();
    Animal an1;
    public AjoutAnnonceForm(Resources res,Animal an) {
        ajoutannonce(res,an);
    }
    
    public void ajoutannonce(Resources res,Animal an)
    {
       //  getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        TextField titre = new TextField("", "Titre", 20, TextField.ANY);
        TextField desc = new TextField("", "description", 20, TextField.ANY);
       ComboBox<String> combo = new ComboBox<> (
          "Adoption",
          "Accouplement", 
                "Vente"
          );
       
        /*
        titre.setUIID("PictureWhiteBackgrond");
        desc.setUIID("PictureWhiteBackgrond");
        combo.setUIID("PictureWhiteBackgrond");
       */
        Button ajouter =new  Button("Ajouter");
       Label ajout1=new Label("ajout");
       
       Label imga=new Label("Importer image");
        Style imgaStyle = new Style(imga.getUnselectedStyle());
        imgaStyle.setFgColor(0x2576f9);
        FontImage imgaImage = FontImage.createMaterial(FontImage.MATERIAL_COLLECTIONS, imgaStyle);
        imga.setIcon(imgaImage);
        imga.setTextPosition(RIGHT);
        
       imga.addPointerPressedListener(l-> {
      ActionListener callback = e->{
   if (e != null && e.getSource() != null) {
       String filePath = (String)e.getSource();
       System.out.println(filePath);

 im.setText(filePath);
       //  Now do something with this file
   }
};

if (FileChooser.isAvailable()) {
    FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
} else {
    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
}
  
       });
       /*
        Container c=BorderLayout.center(ajout1);
        c.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       titre,desc,combo,imga,ajouter));
        add(c);
*/
          Container content = BoxLayout.encloseY(
                new FloatingHint(titre),
                createLineSeparator(),
                new FloatingHint(desc),
                createLineSeparator(),
                combo,
                createLineSeparator(),
                imga,
                createLineSeparator(),
                ajouter
        );
        content.setScrollableY(true);
        add( content);
        
        ajouter.addActionListener(e->{ 
        if(verifstring(titre.getText())&&verifstring(desc.getText())&&verifstring(combo.getSelectedItem()))
        { Annonce ann =new Annonce();
           ann.setId_animal(an);
            System.out.println(an);
       ann.setDescription(desc.getText());
       ann.setTitre_annonce(titre.getText());
       ann.setType_annonce(combo.getSelectedItem());
       
       
       
       ann.setId(User.connected);
           
         
            //File f1=new File(im.getText());
//File f2=new File("C:/Users/hedi/Documents/GitHub/PiDev3A17/web/images/produits/"+p.getNom()+".png");
//f1.renameTo(f2);
////            System.out.println(f1.toString()+"**");
ann.setPhoto_annonce(im.getText());
            ServiceAnnonce sa=new ServiceAnnonce();
            System.out.println("|"+ann.getTitre_annonce()+"|"+ann.getPhoto_annonce()+"|"+ann.getType_annonce()+"|"+ann.getDescription()+"|"+ann.getId_animal().getId_animal()+"|"+ann.getId().getNd());
            sa.ajoutAnnonce(ann);
            Dialog.show("Ajout", "Ajout éffectué avec succés", "ok", null);
            removeAll();
          new MesPropresAnnonces(res).show();
        }
        else{
            Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
        }
        });
    }

    
    public  boolean verifstring(String entry)
    {         if (entry.equals(""))
        return false; 
 /* for(int i=0;i<entry.length();i++)
       {
       if(entry.charAt(i)=='0'||entry.charAt(i)=='1'||entry.charAt(i)=='2'||entry.charAt(i)=='3'||entry.charAt(i)=='4'||entry.charAt(i)=='5'||entry.charAt(i)=='6'||entry.charAt(i)=='7'||entry.charAt(i)=='8'||entry.charAt(i)=='9'||entry.contains(" "))
        {
            return false;
        }}
        return true;*/
         return true;   }
 
public  boolean verifnum(String entry)
    {
        if (entry.equals(""))
        {return false; }
        if(entry.toLowerCase().equals(entry.toUpperCase()))
        {
            return true;
        }
        else{
            return false;
        }
    
    }
}
