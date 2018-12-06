/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import com.gwozdzian.folderVisualizer.utilities.Tools;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public abstract class AbstractFolderFile extends JComponent implements ScaleChangeListener{

    protected FolderFileModel model;
    protected boolean drawFolderContent;
    protected JComponent folderFileContainer;
    protected AbstractFolderFile parent;
    private AbstractFolderFile me;

    public AbstractFolderFile(File f,AbstractFolderFile parentFolderFile , boolean drawFolderContent) {
        super();
        //Tools.trace(this, "AFF for File");
        init(new FolderFileModel(f, drawFolderContent), parentFolderFile, drawFolderContent);
    }

    public AbstractFolderFile(FolderFileModel ffModel, AbstractFolderFile parentFolderFile ,boolean drawFolderContent) {
        super();
        //Tools.trace(this, "AFF for FFM");
        init(ffModel,parentFolderFile, drawFolderContent);
    }

    private void init(FolderFileModel ffModel, AbstractFolderFile parentFolderFile ,  boolean drawFolderContent) {
                this.model = ffModel;
                this.parent = parentFolderFile;
                if(parent!=null){
                    model.setParent(parent.getModel());
                }
                me = this;
                this.drawFolderContent = drawFolderContent;
                //Tools.trace(this, "w initializeMe z AFF");
                model.addScaleChangeListener(this);
		this.setOpaque(false);
                
                this.setBackground(null);
                this.setToolTipText(model.getSimpleName()+" - "+model.getSize());
                updateUI();
                
                setBuildEnvirment();
                buildImplementation();
                
                
                
    }
    
    protected abstract void setBuildEnvirment();

    protected abstract void buildImplementation(); 

    @Override
    public abstract String getUIClassID();

    @Override
    public abstract void updateUI();
    
    public FolderFileModel getModel(){
        return model;
    }

    public long getByteSize() {
        return model.getByteSize();
    }

    public File getFile() {
        return model.getFile();
    }

    public boolean isFolder() {
        return model.isFolder();
    }
    
    
    
    public AbstractFolderFile getParentFolderFile(){
        return this.parent;
    }
    
    
    
    
    public void setScaleToSize(int wSize) {
        model.setScaleToSize(wSize);
    }

    public long getScaleToSize(int wSize) {
        return model.getScaleToSize(wSize);
    }

    public void setScale(double s) {
        model.setScale(s);
    }
    

    public double getScale() {
        return model.getScale();
    }

    @Override
    public void scaleChanged(FolderFileModel folderModel, boolean isChangeInitiator) {
        //Tools.trace(this, model.getSimpleName()+" pack(), invalidate(), repaint()");
        
        this.invalidate();
        this.repaint();
        
        if(isChangeInitiator){
            SwingUtilities.getWindowAncestor(this).pack();
        }
        
    }
    
    
    


    
    




    
    
}
