/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author user
 */
class FFforFileUI extends FolderFileUI{
    
    protected int thickeness = 1;
    protected Color pointedColor = Color.GREEN;
    protected Color oldColor;
    
    private Font fileLabelFont = new Font("Times New Roman", Font.TRUETYPE_FONT, 10);
    
    protected Color borderColor = Color.GRAY;
    protected Color fillColor = Color.LIGHT_GRAY.brighter().brighter();
    
    
    
    
    @Override
    public void installUI(JComponent c) {
                FolderFile ff = (FolderFile) c;
		FolderFileModel model = ff.getModel();
		c.addMouseListener(new MouseAdapter() {
                    
                    

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                            System.out.println(model.getSimpleName());
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //ff.setBackground(oldColor);
                        //ff.setOpaque(false);
                        borderColor = Color.GRAY;
                        fillColor = Color.LIGHT_GRAY.brighter().brighter();
                        ff.repaint();
                        //((JComponent)e.getSource()).setBackground(oldColor);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //oldColor = ff.getBackground();
                        //ff.setOpaque(true);
                        //ff.setBackground(pointedColor);
                        borderColor = Color.BLACK;
                        fillColor = Color.LIGHT_GRAY;
                        ff.repaint();
                        
                        //oldColor = ((JComponent)e.getSource()).getBackground();
                        //((JComponent)e.getSource()).setBackground(pointedColor);                        
                        
                    }
                        
                        
                        
			
		});
    }
    
    
    @Override
    public void paint(Graphics g, JComponent c) {
        FolderFile ff = (FolderFile) c;
        FolderFileModel model = ff.getModel();
        
        
        g.setColor(Color.BLACK);
        
        /*
        PolyLineCreator plc =new PolyLineCreator(g);
        plc.moveTo(0, 0);
        plc.lineTo(model.getGraphicSize()-(thickeness), 0);
        plc.lineTo(model.getGraphicSize()-(thickeness), ff.getHeight()-(2*thickeness));
        plc.lineTo(0, ff.getHeight()-(2*thickeness));
        plc.lineTo(0, 0+thickeness);
        plc.endLine(true);
        */
        
        
        
        Graphics2D g2d = (Graphics2D)g;
        
        
        GeneralPath gp = new GeneralPath();
        int cornerSize = 5;
        if(model.getGraphicSize()>2*cornerSize){
            gp.moveTo(0, 0);
            gp.lineTo(model.getGraphicSize()-(thickeness)-cornerSize, 0);
            gp.lineTo(model.getGraphicSize()-(thickeness), 0+cornerSize);
            gp.lineTo(model.getGraphicSize()-(thickeness), ff.getHeight()-(2*thickeness));
            gp.lineTo(0, ff.getHeight()-(2*thickeness));
            gp.lineTo(0, 0+thickeness);
        }else if(model.getGraphicSize()>1){
            gp.moveTo(0, 0);
            gp.lineTo(model.getGraphicSize()-(thickeness), 0);
            gp.lineTo(model.getGraphicSize()-(thickeness), ff.getHeight()-(2*thickeness));
            gp.lineTo(0, ff.getHeight()-(2*thickeness));
            gp.lineTo(0, 0+thickeness);            
        }else if(model.getGraphicSize()>0){
            gp.moveTo(0, 0);
            gp.lineTo(0, ff.getHeight()-(2*thickeness));
        }

        
        
                
        Shape ob = gp;
        g2d.setColor(fillColor);
        g2d.fill(ob);
        g2d.setColor(borderColor);
        g2d.draw(ob);
        
        
   
        List<String> labelVersions = this.computeLabelVersions(model);
        this.drawString(g, labelVersions, c.getWidth(), 12, fileLabelFont);
    }

    
    
}
