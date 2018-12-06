/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import com.gwozdzian.folderVisualizer.utilities.Tools;
import java.io.File;
import javax.swing.UIManager;


public abstract class FolderFile extends AbstractFolderFile {

    protected FolderFile(File f,AbstractFolderFile parentFolderFile, boolean drawFolderContent) {
        super(f,parentFolderFile, drawFolderContent);
        //Tools.trace(this, "FF for File");
    }

    protected FolderFile(FolderFileModel ffModel,AbstractFolderFile parentFolderFile, boolean drawFolderContent) {
        super(ffModel,parentFolderFile, drawFolderContent);
         //Tools.trace(this, "FF for FFM");
    }
    
    
    public static FolderFile createFolderFile(File f,AbstractFolderFile parentFolderFile, boolean dfc){
        return createFolderFile(new FolderFileModel(f),parentFolderFile, dfc);
    }
    
    public static FolderFile createFolderFile(FolderFileModel ffModel,AbstractFolderFile parentFolderFile, boolean dfc){
        if(ffModel.isFolder()){
            //Tools.trace(null, "tworzy JFold dla "+ffModel.getSimpleName());
            return new JFolder(ffModel, parentFolderFile, dfc);
        }else{
            //Tools.trace(null, "tworzy JFile dla "+ ffModel.getSimpleName());
            return new JFile(ffModel, parentFolderFile, dfc);
        }
    }

    @Override
    protected void setBuildEnvirment() {
        this.folderFileContainer = this;
    }
    
    
    

    protected abstract void buildImplementation();

    public abstract String getUIClassID();

    public abstract void updateUI();

    @Override
    public int getWidth() {
        return model.getGraphicSize(); //super.getWidth(); //To change body of generated methods, choose Tools | Templates.
    }

   


    
    
    
}
