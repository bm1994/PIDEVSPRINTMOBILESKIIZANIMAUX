/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceAnnonce;
import com.mycompany.Entite.Animal;
import com.mycompany.Entite.Annonce;
import com.mycompany.Entite.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class AnnonceF extends BaseForm {
   Annonce ad1=new Annonce();
    public AnnonceF(Resources res) {
        super("Gestion des annonce", BoxLayout.y());
        System.out.println("list des anoonce");
    header(res);
    
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(String img, String title, String desc, String date,Annonce ad) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(photo(height, width, img));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       Label details = new Label("details");
       details.addPointerPressedListener(l->{
          new detailsAnnonce(Resources.getGlobalResources(),ad).show();
       });
       //cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label desc1 = new Label(desc , "NewsBottomLine");
       desc1.setTextPosition(RIGHT);
      
       Label date1 = new Label("date: "+ date , "NewsBottomLine");
       FontImage.setMaterialIcon(desc1, FontImage.MATERIAL_CHAT);
         ServiceAnnonce as=new ServiceAnnonce();
        
         Label likes12=new Label(String.valueOf(as.nbrlike(ad.getId_annonce()))+" likes");
         Style likeStyle1 = new Style(likes12.getUnselectedStyle());
        likeStyle1.setFgColor(0xff2d55);
        FontImage likeImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, likeStyle1);
        likes12.setIcon(likeImage);
        likes12.setTextPosition(RIGHT);
         Label aime = new Label();
        
        Style heartStyle = new Style(aime.getUnselectedStyle());
       
        aime.setTextPosition(RIGHT);
        
       
         
         if(as.liked(ad.getId_annonce(),User.connected.getNd()))
         {
             aime.setText("je n'aime plus");
              heartStyle.setFgColor(0xe83333);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, heartStyle);
        aime.setIcon(heartImage);
         }else
                {
             aime.setText("j'aime");
              heartStyle.setFgColor(0x2a73ea);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, heartStyle);
        aime.setIcon(heartImage);
         }
         
         aime.addPointerPressedListener(l1->{
             if(aime.getText().equalsIgnoreCase("j'aime"))
                     {
                      as.aimerannonce(ad.getId_annonce(),User.connected.getNd());
                         Dialog.show("succés", "vous avez aimé l'annonce: "+ad.getTitre_annonce(), "ok", null);
                       aime.setText("je n'aime plus");
                       likes12.setText(String.valueOf(as.nbrlike(ad.getId_annonce()))+" likes");
                        heartStyle.setFgColor(0xe83333);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, heartStyle);
        aime.setIcon(heartImage);
                     }else{
                  as.annuleraimerannonce(ad.getId_annonce(),User.connected.getNd());
                  Dialog.show("succés", "vous avez choisi de ne plus aimer l'annonce: "+ad.getTitre_annonce(), "ok", null); 
                  aime.setText("j'aime");
                  likes12.setText(String.valueOf(as.nbrlike(ad.getId_annonce()))+" likes");
                   heartStyle.setFgColor(0x2a73ea);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, heartStyle);
        aime.setIcon(heartImage);
             }
         });
        
           
       Image imge=Resources.getGlobalResources().getImage("Facebook.png");
        Button Partage = new Button(imge.fill(20, 20));
        Partage.setUIID("Label");
        Partage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            Dialog.show("succés", "vous avez partage  l'annonce: "+ad.getTitre_annonce(), "ok", null);
                String token = "EAACEdEose0cBADTOUz71GCZBAyV5b0TIWzvrZBrcZAZAQ5QS0WdSUccqdHiotvkZBSVSc6ZB5T3m29wTm9AnD35cZBfUs20kS28xrNHuVZCEQmZAGLYbLxxxz6ZAsG9UM71QdY6X2OZATFIFoiqFkv4z4wUVfLx9p0mZCQFZBcnMQMNbebjyAamYWjAugCn6g76KXKi8PhQEsUbKsjAZDZD";
                FacebookClient fb = new DefaultFacebookClient(token);
                FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", "Je partage avec vous cet annonce : "+title+"\n description : "+desc));

                System.out.println("fb.com" + r.getId());

            }
        });
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseY(desc1, date1,BoxLayout.encloseX(details,Partage)),
                       BorderLayout.west(
                       BoxLayout.encloseX(aime,likes12))
               ));
       
       add(cnt);
       
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
    
    
    public void header(Resources res)
    {
         Toolbar tb = new Toolbar(true);
       setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Gestion des annonces");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
       tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
       // addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton Adoption1 = RadioButton.createToggle("Adoption", barGroup);
        Adoption1.setUIID("SelectBar");
        RadioButton Accouplement1 = RadioButton.createToggle("Accouplement", barGroup);
        Accouplement1.setUIID("SelectBar");
        RadioButton Vente1 = RadioButton.createToggle("Vente", barGroup);
        Vente1.setUIID("SelectBar");
        RadioButton AjoutA = RadioButton.createToggle("Déposer votre Annonce", barGroup);
        AjoutA.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, Adoption1, Accouplement1, Vente1, AjoutA),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Adoption1.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Adoption1, arrow);
        });
        bindButtonSelection(Adoption1, arrow);
        bindButtonSelection(Accouplement1, arrow);
        bindButtonSelection(Vente1, arrow);
        bindButtonSelection(AjoutA, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
      Adoption1.addActionListener(l->{
          Dialog ip = new InfiniteProgress().showInifiniteBlocking();
          removeAll();
          header(res);
          show();
          affichageAdoption();
          ip.dispose();
      });
      AjoutA.addActionListener(l->{
          TextArea t= new TextArea("Avez vous deja un animal que vous voulez lui déposer une annonce?");
          t.setSingleLineTextArea(false);
          
          Command [] cmds = new Command[3];
        cmds[0] = new Command("Oui"){
            public void actionPerformed(ActionEvent evt) {
                //do Option1
                //removeAll();  
             Dialog dlg = (Dialog)Display.getInstance().getCurrent();
             dlg.dispose();
              new  MesPropresAnimaux(res).show();
             
            }        
        };
        cmds[1] = new Command("Non"){
            public void actionPerformed(ActionEvent evt) {
                
                //do Option2
                
                 Dialog dlg = (Dialog)Display.getInstance().getCurrent();
             dlg.dispose();
              new  AjoutAnimal(res).show();
            }        
        };
        cmds[2] = new Command("Cancel"){
            public void actionPerformed(ActionEvent evt) {
                //do Option3
                
            }        
        };
        Dialog.show("AjoutAnnonce",t, cmds);
      }
      );
           
      Accouplement1.addActionListener(l->{
                    Dialog ip = new InfiniteProgress().showInifiniteBlocking();

          removeAll();
          header(res);
          affichageAccouplement();
          ip.dispose();
      });
       Vente1.addActionListener(l->{
                     Dialog ip = new InfiniteProgress().showInifiniteBlocking();

          removeAll();
          header(res);
          affichageVente();
          ip.dispose();
      });
    }
     public void affichageAdoption()
     {   ServiceAnnonce ser = new ServiceAnnonce();
         for (int i=0 ; i< ser.getList4().size();i++)
         {
             addButton(ser.getList4().get(i).getPhoto_annonce(), ser.getList4().get(i).getTitre_annonce(), ser.getList4().get(i).getDescription(), ser.getList4().get(i).getDate_annonce(),ser.getList4().get(i));
             show();
         }
     }
   //  (Image.createImage(hi.getWidth() / 2, hi.getWidth() / 2, 0xffff0000), true)
     public URLImage photo(int w,int h ,String pa)
     {
         URLImage photo2 = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(h, w , 0xffff0000), true), pa,
                "http://localhost/skiizanimauxfinale/web/ressources/" + pa
        );
        photo2.fetch();
        return photo2;
}
     
     
     
      public void affichageAccouplement()
     {   ServiceAnnonce ser = new ServiceAnnonce();
         for (int i=0 ; i< ser.getList5().size();i++)
         {
             addButton(ser.getList5().get(i).getPhoto_annonce(), ser.getList5().get(i).getTitre_annonce(), ser.getList5().get(i).getDescription(), ser.getList5().get(i).getDate_annonce(),ser.getList5().get(i));
         }
     }
   //  (Image.createImage(hi.getWidth() / 2, hi.getWidth() / 2, 0xffff0000), true)
      
       public void affichageVente()
     {   ServiceAnnonce ser = new ServiceAnnonce();
         for (int i=0 ; i< ser.getList6().size();i++)
         {
             addButton(ser.getList6().get(i).getPhoto_annonce(), ser.getList6().get(i).getTitre_annonce(), ser.getList6().get(i).getDescription(), ser.getList6().get(i).getDate_annonce(),ser.getList6().get(i));
         }
     }
    
       
       
       
       
}
