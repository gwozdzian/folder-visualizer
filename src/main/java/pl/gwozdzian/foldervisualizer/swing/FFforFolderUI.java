/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import pl.gwozdzian.utils.Tools;

/**
 *
 * @author user
 */
public class FFforFolderUI extends FolderFileUI{
    
    protected int thickeness = 1;
    protected Color pointedColor = Color.ORANGE;
    protected Color oldColor;
    protected int cornerSize = 6;
    private Font folderLabelFont = new Font("Times New Roman", Font.TRUETYPE_FONT, 12);
    private Color borderColor = Color.GRAY;
    private Color fillColor = Color.YELLOW;
    
    
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
                        fillColor = Color.YELLOW;
                        ff.repaint();
                        //((JComponent)e.getSource()).setBackground(oldColor);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //oldColor = ff.getBackground();
                        //ff.setOpaque(true);
                        //ff.setBackground(pointedColor);
                        borderColor = Color.BLACK;
                        fillColor = Color.ORANGE;
                        ff.repaint();
                        
                        //oldColor = ((JComponent)e.getSource()).getBackground();
                        //((JComponent)e.getSource()).setBackground(pointedColor);                        
                        
                    }
                        
                        
			
		});
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        Tools.trace(Tools.getMethodName());
        FolderFile ff = (FolderFile) c;
        FolderFileModel model = ff.getModel();
        
        List<String> labelVersions = this.computeLabelVersions(model);
        int maxStringWidth = c.getWidth()-(5*cornerSize);
        String fittedString = this.getFittedString(g, labelVersions, maxStringWidth, JFolder.FOLDER_TOP_HEIGHT, folderLabelFont);
        Dimension fittedStringDimension = this.getFittedStringDimension(g, labelVersions, maxStringWidth, JFolder.FOLDER_TOP_HEIGHT, folderLabelFont);
        
        
        
        /*
        g.setColor(Color.RED);
        PolyLineCreator plc = new PolyLineCreator(g);
        plc.moveTo(0, 0);
        plc.lineTo(model.getGraphicSize()-thickeness, 0+5);
        plc.lineTo(model.getGraphicSize()-thickeness, ff.getHeight()-(thickeness));
        plc.lineTo(0, ff.getHeight()-(thickeness));
        plc.lineTo(0, 0);
        plc.endLine(true);
        
        */
        


        Graphics2D g2d = (Graphics2D)g;
        
             
        Shape ob = createFolderShape(g, c,  ff, model, fittedStringDimension);
        g2d.setColor(fillColor);
        g2d.fill(ob);
        g2d.setColor(borderColor);
        g2d.draw(ob);
        
        
        
        g.drawString(fittedString, cornerSize, cornerSize+fittedStringDimension.height);
        
        //this.drawString(g, labelVersions, c.getWidth(), 15, 12);
    }
    
    
    private Shape createFolderShape(Graphics g, JComponent c, FolderFile ff,FolderFileModel model, Dimension stringDimension){
                
       
        
        
        GeneralPath gp = new GeneralPath();
        
        if(model.getGraphicSize()>(4*cornerSize)+stringDimension.width ){//&& stringDimension.width>0
            gp.moveTo(0, cornerSize);
            gp.curveTo(0, cornerSize, 0, 0, cornerSize, 0);
            gp.lineTo(cornerSize+stringDimension.width, 0);
            gp.curveTo(cornerSize+stringDimension.width, 0,    (2*cornerSize)+stringDimension.width, 0,     (2*cornerSize)+stringDimension.width, 0+cornerSize);
            gp.lineTo((2*cornerSize)+stringDimension.width, JFolder.FOLDER_TOP_HEIGHT-cornerSize);
            gp.lineTo(c.getWidth()-cornerSize, JFolder.FOLDER_TOP_HEIGHT-cornerSize);
            gp.curveTo(c.getWidth()-cornerSize, JFolder.FOLDER_TOP_HEIGHT-cornerSize,         c.getWidth()-thickeness, JFolder.FOLDER_TOP_HEIGHT-cornerSize,        c.getWidth()-thickeness, JFolder.FOLDER_TOP_HEIGHT);
            gp.lineTo(c.getWidth()-thickeness ,c.getHeight()-cornerSize);
            gp.curveTo(c.getWidth()-thickeness ,c.getHeight()-cornerSize,          c.getWidth()-thickeness, c.getHeight()-(2*thickeness),               c.getWidth()-cornerSize-thickeness, c.getHeight()-(2*thickeness));
            gp.lineTo(cornerSize, c.getHeight()-(2*thickeness));
            gp.curveTo(cornerSize, c.getHeight()-(2*thickeness),             0, c.getHeight()-(2*thickeness),        0, c.getHeight()-cornerSize );
            gp.lineTo(0, cornerSize);
            
//            gp.lineTo(model.getGraphicSize()-(thickeness)-cornerSize, 0);
//            gp.lineTo(model.getGraphicSize()-(thickeness), 0+cornerSize);
//            gp.lineTo(model.getGraphicSize()-(thickeness), ff.getHeight()-(2*thickeness));
//            gp.lineTo(0, ff.getHeight()-(2*thickeness));
//            gp.lineTo(0, 0+thickeness);
        }else if(model.getGraphicSize()>2*cornerSize){
            gp.moveTo(0, cornerSize);
            gp.curveTo(0, cornerSize, 0, 0, cornerSize, 0);
            //gp.lineTo(cornerSize+stringDimension.width, 0);
            //gp.curveTo(cornerSize+stringDimension.width, 0,    (2*cornerSize)+stringDimension.width, 0,     (2*cornerSize)+stringDimension.width, 0+cornerSize);
            //gp.lineTo((2*cornerSize)+stringDimension.width, JFolder.FOLDER_TOP_HEIGHT-cornerSize);
            gp.lineTo(c.getWidth()-cornerSize, 0);
            gp.curveTo(c.getWidth()-cornerSize, 0,         c.getWidth()-thickeness, 0,        c.getWidth()-thickeness, cornerSize);
            gp.lineTo(c.getWidth()-thickeness ,c.getHeight()-cornerSize);
            gp.curveTo(c.getWidth()-thickeness ,c.getHeight()-cornerSize,          c.getWidth()-thickeness, c.getHeight()-(2*thickeness),               c.getWidth()-cornerSize-thickeness, c.getHeight()-(2*thickeness));
            gp.lineTo(cornerSize, c.getHeight()-(2*thickeness));
            gp.curveTo(cornerSize, c.getHeight()-(2*thickeness),             0, c.getHeight()-(2*thickeness),        0, c.getHeight()-cornerSize );
            gp.lineTo(0, cornerSize);         
        }else if(model.getGraphicSize()>cornerSize){
            gp.moveTo(0, 0);
            gp.lineTo(c.getWidth()-cornerSize, 0);
            gp.curveTo(c.getWidth()-cornerSize, 0,    c.getWidth()-thickeness, 0,        c.getWidth()-thickeness, cornerSize);
            gp.lineTo(c.getWidth()-thickeness, c.getHeight()-cornerSize);
            gp.curveTo(c.getWidth()-thickeness, c.getHeight()-cornerSize,        c.getWidth()-thickeness, c.getHeight()-(2*thickeness),            c.getWidth()/2, c.getHeight()-(2*thickeness));
            gp.curveTo(c.getWidth()/2, c.getHeight()-(2*thickeness),   0, c.getHeight()-(2*thickeness),    0, c.getHeight()-cornerSize);
            gp.lineTo(0, 0);
        }else if(model.getGraphicSize()>1){
            gp.moveTo(0, 0);
            gp.curveTo(0, 0,         c.getWidth()-thickeness, 0,                     c.getWidth()-thickeness, cornerSize);
            gp.lineTo(c.getWidth()-thickeness, c.getHeight()-(2*thickeness));
            gp.lineTo(0, c.getHeight()-(2*thickeness));
            gp.lineTo(0, 0);
        }else if(model.getGraphicSize()>0){
            gp.moveTo(0, 0);
            gp.lineTo(0, c.getHeight()-(2*thickeness));
        }
        
        return gp;
    }
    
    
    
    
    
    
    
}
