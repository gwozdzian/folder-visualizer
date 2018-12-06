/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import java.io.File;


public class JFolderFile extends AbstractFolderFile {

    public JFolderFile(File f, boolean drawFolderContent) {
        super(f, null, drawFolderContent);
    }

    public JFolderFile(FolderFileModel ffModel, boolean drawFolderContent) {
        super(ffModel, null ,drawFolderContent);
    }

    @Override
    protected void setBuildEnvirment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildImplementation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUIClassID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
