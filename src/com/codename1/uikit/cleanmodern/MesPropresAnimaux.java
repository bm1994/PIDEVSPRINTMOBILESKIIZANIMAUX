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

/**
 *
 * @author Admin
 */
public class MesPropresAnimaux extends  BaseForm{
    
    public MesPropresAnimaux(Resources res) {
        super("Newsfeed", BoxLayout.y());
        System.out.println("redirected to mes propres animaux");
        header(res);
    affichageMesAnimaux();
        show();
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
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(String img, String nomA, String RaceA, String AgeA,Animal an1) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(photo(height, width, img));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
        
       //cnt.setLeadComponent(image);
       TextArea ta = new TextArea(nomA);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label RaceAn = new Label(RaceA , "NewsBottomLine");
       RaceAn.setTextPosition(RIGHT);
      
       Label age1 = new Label("age: "+ AgeA , "NewsBottomLine");
       FontImage.setMaterialIcon(RaceAn, FontImage.MATERIAL_CHAT);
       
       Label ajoutan = new Label("Nouvelle annonce", "NewsBottomLine");
       FontImage.setMaterialIcon(ajoutan, FontImage.MATERIAL_ADD);
       
       ajoutan.addPointerPressedListener(l->{
           new AjoutAnnonceForm(Resources.getGlobalResources(), an1).show();
       });
       
        Label supprimer = new Label();
        Style supprimerStyle = new Style(supprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xff2d55);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        supprimer.setIcon(supprimerImage);
        supprimer.setTextPosition(RIGHT);

        ServiceAnnonce sa=new ServiceAnnonce();
        
        supprimer.addPointerPressedListener(l->{
          sa.supprimerAnimal(an1.getId_animal());
            new MesPropresAnimaux(Resources.getGlobalResources()).show();
        });
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseY(RaceAn, age1,ajoutan),BorderLayout.west(supprimer)
               ));
       
       add(cnt);
       
       image.addActionListener(e -> ToastBar.showMessage(nomA, FontImage.MATERIAL_INFO));
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
        setTitle("SkiizAnimaux");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe,res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
      
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
     
    }
    
     public URLImage photo(int w,int h ,String pa)
     {
         URLImage photo2 = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(h, w , 0xffff0000), true), pa,
                "http://localhost/skiizanimauxfinale/web/ressources/" + pa
        );
        photo2.fetch();
        return photo2;
}
     
   
       public void affichageMesAnimaux()
     {   ServiceAnnonce ser = new ServiceAnnonce();
         for (int i=0 ; i< ser.getListAnimal().size();i++)
         {
             addButton(ser.getListAnimal().get(i).getImage(), ser.getListAnimal().get(i).getNom_animal(), ser.getListAnimal().get(i).getRace_animal(),String.valueOf(ser.getListAnimal().get(i).getAge_animal()),ser.getListAnimal().get(i));
         }
     }
    
}
