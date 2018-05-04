/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceTask;
import com.mycompagny.Service.ServiceTask_1;
import com.mycompany.Entite.Produit;
import com.mycompany.Entite.User;
import com.mycompany.Entite.events;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Afficher_list_rendezvous extends   BaseForm {
    public Afficher_list_rendezvous(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Boutique SkiizAnimaux");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story "
                + "");
                
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
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
       ServiceTask_1 ser = new ServiceTask_1();
					ArrayList<events> Tab = ser.getListevents();
                                        String s="http://res.cloudinary.com/russie2k18/image/upload/04b5a52a7f9b06f6a8be474f3f2a4d75.jpg";
					for (int i = 0; i < Tab.size(); i++) {
						addButton(res,s, Tab.get(i).getStart_event()+ "  , " + Tab.get(i).getTitre()+" , "+Tab.get(i).getEtat(),Tab.get(i).getId(),false, 11, 9, 2);
					}
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
    
  private void addButton(Resources res,String imageUrl, String title,int id_event, boolean liked, int likeCount, int commentCount, int id) {
imageUrl="http://res.cloudinary.com/russie2k18/image/upload/04b5a52a7f9b06f6a8be474f3f2a4d75.jpg";
		ImageViewer im = new ImageViewer();
		Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
		EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

		im.setImage(URLImage.createToStorage(encImage, "Medium" + imageUrl, imageUrl, URLImage.RESIZE_SCALE));

		int height = Display.getInstance().convertToPixels(11.5f);
		int width = Display.getInstance().convertToPixels(14f);
		Button image = new Button(im.getImage().fill(width, height));
		image.setUIID("Label");
		Container cnt = BorderLayout.west(image);
		cnt.setLeadComponent(image);

		TextArea ta = new TextArea(title);
		ta.setUIID("NewsTopLine");
		ta.setEditable(false);
		Label likes = new Label(likeCount + " Plus Détails sur docteur  ", "NewsBottomLine");
		likes.setTextPosition(RIGHT);
		if (!liked) {
			FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
		} else {
			Style s = new Style(likes.getUnselectedStyle());
			s.setFgColor(0xff2d55);
			FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
			likes.setIcon(heartImage);
		}
		Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
		FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

		cnt.add(BorderLayout.CENTER,
				BoxLayout.encloseY(
						ta,
						BoxLayout.encloseX(likes, comments)
				));
		add(cnt);

		
   image.addActionListener(e ->  { Boolean x = Dialog.show("Alerte", "Etes vous pret pour ce module ?", "oui","non");
                        if(x)
                        {ServiceTask_1 a =new ServiceTask_1();
                        a.delete_event(id_event);
Afficher_list_rendezvous al = new Afficher_list_rendezvous(res);
                        al.show();                        }
                        else {veto_rendezvous vr= new veto_rendezvous(res);
                        vr.getF().show();
                        
                        }
       }); 	
	}
  
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}
