/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class JFolderStrip extends JFolder {

    private JPanel childContent;
    private JFolderStrip jFolderStrip;

    public JFolderStrip(File f, AbstractFolderFile parentFolderFile){
        this(f, parentFolderFile, false);
    }

    private JFolderStrip(File f, AbstractFolderFile parentFolderFile, boolean drawFolderContent) {
        super(f,parentFolderFile, drawFolderContent);
        
    }

    private JFolderStrip(FolderFileModel ffModel, AbstractFolderFile parentFolderFile, boolean drawFolderContent) {
        super(ffModel, parentFolderFile, drawFolderContent);
        
    }



    @Override
    protected void setBuildEnvirment() {
        this.jFolderStrip = this;
        this.folderFileContainer = new JPanel();
        this.add(folderFileContainer);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        childContent = new JPanel();
        this.add(childContent);
    }
    
    

    @Override
    protected void buildImplementation() {
               this.folderFileContainer.setLayout(new MarginLayout(FOLDER_TOP_HEIGHT, FOLDER_BOTTOM_HEIGHT, 0,0));
        
        //if(drawFolderContent){
          
            for(FolderFileModel m :model.getDirectChildModels()){
                FolderFile comp = FolderFile.createFolderFile(m, this, false/*drawFolderContent*/);
                folderFileContainer.add(comp);
                
                if(comp.isFolder()){
                    

                    comp.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            //super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                            childContent.removeAll();
                            JFolderStrip childFolderStrip = new JFolderStrip(comp.getModel().getFile(), comp);
                            childContent.add(new FolderStripContainer(childFolderStrip));
                            childFolderStrip.setScaleToSize(2500);
                            jFolderStrip.invalidate();
                            jFolderStrip.repaint();
                            SwingUtilities.getWindowAncestor(jFolderStrip).pack();

                        }

                    });
                }
            }
        //}
    }

    
    /*
    @Override
    public String getUIClassID() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUI() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    */


    
}
