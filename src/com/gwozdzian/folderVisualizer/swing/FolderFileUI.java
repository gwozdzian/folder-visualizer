/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author user
 */
public abstract class FolderFileUI extends ComponentUI {
    
    protected List<String> computeLabelVersions(FolderFileModel model) {
        List<String> titles = new LinkedList<String>();
        titles.add(model.getSimpleName() + " ,  " + model.getSize());
        titles.add(model.getSimpleName());
        if(model.getSimpleName().length()>18){
            titles.add(model.getSimpleName().substring(0, 13) + " ...");
        }
        titles.add(model.getSimpleName().substring(0, 2) + " ...");
        return titles;
    }
    



    protected String getFittedString(Graphics g,List<String> textes , int w, int h, Font font){
        //Font oldFont = g.getFont();
        //g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        for(String text : textes){
            int stringWidth = fontMetrics.stringWidth(text);
            int stringHeight = fontMetrics.getHeight();
            if(stringWidth<w){
                //g.setFont(oldFont);
                return text;
            }
        }
        //g.setFont(oldFont);
        return "";

    }
    
    protected Dimension getFittedStringDimension(Graphics g,List<String> textes , int w, int h, Font font){
        String fittedString = getFittedString( g,textes , w,  h, font);
        Font oldFont = g.getFont();
        g.setFont(font);
        int stringWidth = g.getFontMetrics().stringWidth(fittedString);
        int stringHeight = g.getFontMetrics().getHeight();
        g.setFont(oldFont);
        return new Dimension(stringWidth, stringHeight); //TODO
    }
    
    protected void drawString(Graphics g,List<String> textes , int w, int h, Font font){
        g.setFont(font);
        for(String text : textes){
            int stringWidth = g.getFontMetrics().stringWidth(text);
            int stringHeight = g.getFontMetrics().getHeight();
            if(stringWidth<w){
                g.drawString(text, 0, stringHeight);
                return;
            }
        }

    }
    
}
