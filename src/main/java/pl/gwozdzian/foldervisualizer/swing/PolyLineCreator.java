/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer.swing;

import java.awt.Graphics;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author user
 */
class PolyLineCreator {
    
    Point beginingPoint = null;
    Point startPoint = new Point(0,0);
    
    private final Graphics g;
    
    
    public PolyLineCreator(Graphics graph) {
        this.g = graph;
    }


    

    public void moveTo(int x, int y) {
        Point p = new Point(x,y);
        startPoint =  p;
        if(beginingPoint == null) beginingPoint = p;
       
    }



    public void lineTo(int x, int y) {
        if(beginingPoint == null) beginingPoint = new Point(0,0);
        g.drawLine(startPoint.x, startPoint.y, x, y);
        startPoint.x = x;
        startPoint.y = y;
        
    }
    
    public void endLine(){
        endLine(false);
    }

    void endLine(boolean closeLine) {
        if(closeLine){
            g.drawLine(startPoint.x, startPoint.y, beginingPoint.x, beginingPoint.y); 
        }else{
            
        }
        startPoint = new Point(0,0);
        beginingPoint = null;
        
    }

}
