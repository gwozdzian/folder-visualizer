/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer.swing;


import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import pl.gwozdzian.codestopwatch.CodeStopwatch;
import pl.gwozdzian.utils.Tools;

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
                CodeStopwatch.start(ffModel.getSimpleName()+" AbstractFolderFile.init()");
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
                
                
                CodeStopwatch.stop(ffModel.getSimpleName()+" AbstractFolderFile.init()");
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
    
    
    public AbstractFolderFile getRootFolderFile(){
        AbstractFolderFile rootFolderFile = this;
        while(rootFolderFile.getParentFolderFile()!=null){
            rootFolderFile = rootFolderFile.getParentFolderFile();
        }
        return rootFolderFile;
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
        CodeStopwatch.start();
        model.setScale(s);
        //model.setScale(s);
        CodeStopwatch.stop();
    }
    

    public double getScale() {
        return model.getScale();
    }

    @Override
    public void setVisible(boolean aFlag) {
        CodeStopwatch.start(this.getName()+ " setVisible("+aFlag+")");
        super.setVisible(aFlag); //To change body of generated methods, choose Tools | Templates.
        CodeStopwatch.stop(this.getName()+ " setVisible("+aFlag+")");
    }

    
    
    
    @Override
    public void scaleChanged(FolderFileModel folderModel, boolean isChangeInitiator) {
        CodeStopwatch.start(this.getName()+" scaleChanged()");
        
        
        this.setVisible(this.getWidth()>10);

        
        
        //Tools.trace(this, model.getSimpleName()+" pack(), invalidate(), repaint()");
        CodeStopwatch.start(this.getName()+" invalidate()");
        this.invalidate();
        CodeStopwatch.stop(this.getName()+" invalidate()");
        
        
        CodeStopwatch.start(this.getName()+" repaint()");
        this.repaint();
        CodeStopwatch.stop(this.getName()+" repaint()");
        //if(isChangeInitiator){
            SwingUtilities.getWindowAncestor(this).pack();
        //}
        CodeStopwatch.stop(this.getName()+" scaleChanged()");
    }

    @Override
    public int getWidth() {
        return model.getGraphicSize(); //super.getWidth(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
    
    /////////////////////
    
    
    
    
    @Override
    public void revalidate() {
        Tools.trace(this.model.getSimpleName()+" - "+Tools.getMethodName());
        super.revalidate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        Tools.trace(this.model.getSimpleName()+" - "+Tools.getMethodName());
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void invalidate() {
        Tools.trace(this.model.getSimpleName()+" - "+Tools.getMethodName());
        super.invalidate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void repaint() {
        Tools.trace(this.model.getSimpleName()+" - "+Tools.getMethodName());
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
    
    
    
    
    
    
    
    
    
    


    
    




    
    
}
