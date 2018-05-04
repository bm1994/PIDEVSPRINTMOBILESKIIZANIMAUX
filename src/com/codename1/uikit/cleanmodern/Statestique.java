/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceTask;
import com.mycompany.Entite.Panier;
import com.mycompany.Entite.Produit;
import com.mycompany.Entite.User;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Statestique {
    
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    series.add("TPR",values[0]);
    series.add("PR",values[1]);
    
    return series;
}

public Form createPieChartForm( Resources res) {
   
    // Generate the values
    ServiceTask s = new ServiceTask ();
ArrayList <Produit> l = new ArrayList<>();
l= s.getList3();
int Nbtotal= l.size();
ArrayList <Panier> ll = new ArrayList<>();
int id=User.connected.getNd();
ll=s.getList4(id);
int NbtotlaPanier=ll.size();



    double[] values = new double[]{Nbtotal, NbtotlaPanier};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setLabelsColor(ColorUtil.BLACK);
    renderer.setLabelsTextSize(70);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLACK);
    r.setGradientStop(0, ColorUtil.MAGENTA);
    r.setHighlighted(true);
    r.setColor(ColorUtil.BLACK);
    
    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Produits statistiques", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Budget", new BorderLayout());
    f.add(BorderLayout.OVERLAY, c);
    Button b = new Button("<-");
    f.add(BorderLayout.SOUTH, b);
b.addActionListener((evt) -> {
    new NewsfeedForm( res).show();
});
    return f;
    

}
    
}
