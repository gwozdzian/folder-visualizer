/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer.swing;

/**
 *
 * @author user
 */
public interface ScaleChangeListener {

    public void scaleChanged(FolderFileModel folderModel, boolean isChangeInitiator);
    
}
