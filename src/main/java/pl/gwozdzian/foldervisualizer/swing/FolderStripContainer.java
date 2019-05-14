/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer.swing;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class FolderStripContainer extends JPanel{

    private JFolderStrip jFolderStrip;
    public static final int SHACKLE_HEIGHT = 25;

    public FolderStripContainer(JFolderStrip jfs) {
        this.jFolderStrip = jfs;
        setLayout(new MarginLayout(SHACKLE_HEIGHT,0,0,0));
        add(jFolderStrip);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        AbstractFolderFile parentFolderFile = jFolderStrip.getParentFolderFile();
        if(parentFolderFile!=null){
            
            
                    Graphics2D g2d = (Graphics2D)g;
                    
            int shackleStart = parentFolderFile.getX();
            int shackleEnd = parentFolderFile.getX()+parentFolderFile.getWidth();
                    
                    Shape ob = createShackleShape(g, shackleStart, shackleEnd, this.getWidth());
                    //g2d.setColor(fillColor);
                    //g2d.fill(ob);
                    g2d.setColor(Color.BLACK);
                    
                    g2d.draw(ob);
            
            //g.drawLine(0, SHACKLE_HEIGHT, parentFolderFile.getX(), 0);
            //g.drawLine(this.getWidth(), SHACKLE_HEIGHT, parentFolderFile.getX()+parentFolderFile.getWidth(), 0);
            //int iks = parentFolderFile.getX();
            //int szerokosc = parentFolderFile.getWidth();
            //Tools.trace("x="+iks+", width="+szerokosc+" szerokość folderStripContainera: "+this.getWidth());
            
            
        }
        
        
    }

    private Shape createShackleShape(Graphics g, int shackleStart, int shackleEnd, int w) {
        GeneralPath gp = new GeneralPath();
        
        
        if(shackleStart>SHACKLE_HEIGHT){
            gp.moveTo(0, SHACKLE_HEIGHT);
            gp.curveTo(0, SHACKLE_HEIGHT,           0, SHACKLE_HEIGHT/2,        SHACKLE_HEIGHT/2, SHACKLE_HEIGHT/2 );
            gp.lineTo(shackleStart-(SHACKLE_HEIGHT/2), SHACKLE_HEIGHT/2);
            gp.curveTo(shackleStart-(SHACKLE_HEIGHT/2), SHACKLE_HEIGHT/2,             shackleStart, SHACKLE_HEIGHT/2,                    shackleStart, 0);
        }else{
            gp.moveTo(0, SHACKLE_HEIGHT);
            gp.curveTo(0, SHACKLE_HEIGHT,           0, SHACKLE_HEIGHT/2,        shackleStart/2, SHACKLE_HEIGHT/2 );
            gp.curveTo(shackleStart/2, SHACKLE_HEIGHT/2,             shackleStart, SHACKLE_HEIGHT/2,                    shackleStart, 0);
        }
        
        
        
        
        
        
        
        if(w-shackleEnd>SHACKLE_HEIGHT){
            gp.moveTo(w, SHACKLE_HEIGHT);
            gp.curveTo(w, SHACKLE_HEIGHT,           w, SHACKLE_HEIGHT/2,        w-(SHACKLE_HEIGHT/2), SHACKLE_HEIGHT/2 );
            gp.lineTo(shackleEnd+(SHACKLE_HEIGHT/2), SHACKLE_HEIGHT/2);
            gp.curveTo(shackleEnd+(SHACKLE_HEIGHT/2), SHACKLE_HEIGHT/2,             shackleEnd, SHACKLE_HEIGHT/2,                    shackleEnd, 0);
        }else{
            gp.moveTo(w, SHACKLE_HEIGHT);
            gp.curveTo(w, SHACKLE_HEIGHT,           w, SHACKLE_HEIGHT/2,        w-((w-shackleEnd)/2), SHACKLE_HEIGHT/2 );
            gp.curveTo(w-((w-shackleEnd)/2), SHACKLE_HEIGHT/2,             shackleEnd, SHACKLE_HEIGHT/2,                    shackleEnd, 0);
        }
        
        
        return gp;
    }
    
    
    
    
}
