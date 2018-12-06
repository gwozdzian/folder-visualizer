/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import com.gwozdzian.folderVisualizer.utilities.Tools;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.File;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class JFolder extends FolderFile {
    public static final int FOLDER_TOP_HEIGHT = 25;
    public static final int FOLDER_BOTTOM_HEIGHT = 10;

    public JFolder(File f, AbstractFolderFile parentFolderFile, boolean drawFolderContent) {
        super(f, parentFolderFile, drawFolderContent);
         //Tools.trace(this, "JFold");
    }
    
    

    public JFolder(FolderFileModel ffModel,AbstractFolderFile parentFolderFile , boolean dfc) {
        super(ffModel, parentFolderFile,  dfc);
        //Tools.trace(this, "JFold");
    }

    @Override
    protected void buildImplementation(){
        //model.addScaleChangeListener(this);
        //LayoutManager layout = this.getLayout();
        
        this.folderFileContainer.setLayout(new MarginLayout(FOLDER_TOP_HEIGHT, FOLDER_BOTTOM_HEIGHT, 0,0));
        
        if(drawFolderContent){
          
            for(FolderFileModel m :model.getDirectChildModels()){

                folderFileContainer.add(FolderFile.createFolderFile(m, this, drawFolderContent));
            }
        }
        //layout = this.getLayout();
        //Tools.trace(layout, "to jest layout po podstawieniu");
        
        
    }

    @Override
    public String getUIClassID() {
            return FFforFolderUI.class.getCanonicalName();
    }

    @Override
    public void updateUI() {
        //setUI((FFforFolderUI) UIManager.getUI(this));
        setUI(new FFforFolderUI());
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension minDim = super.getMinimumSize();
        minDim.width = model.getGraphicSize(); //To change body of generated methods, choose Tools | Templates.
        return minDim;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension maxDim = super.getMaximumSize(); //To change body of generated methods, choose Tools | Templates.
        Dimension minDim = getMinimumSize();
        maxDim.width = model.getGraphicSize();
        maxDim.height = minDim.height;
        return maxDim;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension prefDim = super.getPreferredSize(); //To change body of generated methods, choose Tools | Templates.
        Dimension minDim = getMinimumSize();
        prefDim.width = model.getGraphicSize();
        prefDim.height = minDim.height;
        return prefDim;
    }
    
    
    


    
}
