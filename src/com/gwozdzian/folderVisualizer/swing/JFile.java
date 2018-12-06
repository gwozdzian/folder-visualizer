/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import com.gwozdzian.folderVisualizer.utilities.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class JFile extends FolderFile {
    
    public static final int FILE_HEIGHT = 30;

    public JFile(File f, AbstractFolderFile parentFolderFile, boolean drawFolderContent) {
        super(f,parentFolderFile, drawFolderContent);
        
        //Tools.trace(this, "JFile");
    }

    
    
    
    public JFile(FolderFileModel ffModel,AbstractFolderFile parentFolderFile, boolean dfc) {
        super(ffModel,parentFolderFile, dfc);
        //Tools.trace(this, "JFile");
    }

    @Override
    protected  void buildImplementation(){
   
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(model.getGraphicSize(), FILE_HEIGHT); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(model.getGraphicSize(), FILE_HEIGHT); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(model.getGraphicSize(), FILE_HEIGHT); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeight() {
        return FILE_HEIGHT; //super.getHeight(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    

    @Override
    public String getUIClassID() {
            return FFforFileUI.class.getCanonicalName();
        }

    @Override
    public void updateUI() {
        setUI(new FFforFileUI());
    }






    
    


    
}
