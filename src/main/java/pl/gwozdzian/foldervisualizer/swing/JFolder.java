/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer.swing;


import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.File;
import javax.swing.UIManager;
import pl.gwozdzian.codestopwatch.CodeStopwatch;
import pl.gwozdzian.utils.Tools;

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
        CodeStopwatch.start("JFolder.buildImplementation() "+model.getSimpleName());
        //model.addScaleChangeListener(this);
        //LayoutManager layout = this.getLayout();
        
        this.folderFileContainer.setLayout(new MarginLayout(FOLDER_TOP_HEIGHT, FOLDER_BOTTOM_HEIGHT, 0,0));
        
        if(drawFolderContent){
          
            for(FolderFileModel m :model.getDirectChildModels()){
                //TODO this is template optimalisation solution
                if(m.isFolder()){
                    folderFileContainer.add(FolderFile.createFolderFile(m, this, drawFolderContent));
                }else if(m.getByteSize()>1000000){
                    //folderFileContainer.add(FolderFile.createFolderFile(m, this, drawFolderContent));
                }else{
                    //Tools.trace("<< "+m.getSimpleName()+", "+m.getSize());
                }
            }
        }
        //layout = this.getLayout();
        //Tools.trace(layout, "to jest layout po podstawieniu");
        
        CodeStopwatch.stop("JFolder.buildImplementation() "+model.getSimpleName());
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
