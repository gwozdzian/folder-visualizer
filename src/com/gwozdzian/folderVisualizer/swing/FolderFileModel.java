/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

import com.gwozdzian.folderVisualizer.utilities.Tools;
import java.io.File;
import java.util.*;

/**
 *
 * @author user
 * 
 */
public class FolderFileModel {
    
    public static int nr =  0;

    private final File file;
    public List<ScaleChangeListener> scaleChangeListeners; 
    List<FolderFileModel> directChildModelsList = null;
    private double scale = 1000;
    private long byteSize = -1;
    private FolderFileModel parentModel;
    private boolean withChilds;
    private boolean withGrandChilds;
    
    public FolderFileModel(File f){
        this(f,true);
    }
    
    public FolderFileModel(File f, boolean withChil){
        this(f, withChil, true);
    }
    
    public FolderFileModel(File f, boolean withChil, boolean withGrandChil) {
        Tools.trace("["+nr+"] "+f.getName());
        nr++;
        this.file = f;   
        this.withChilds = withChil; 
        this.withGrandChilds = withGrandChil; 
        this.scaleChangeListeners = new ArrayList<ScaleChangeListener>();
        
//        directChildModelsList = new LinkedList<FolderFileModel>();
//            if(withGrandChilds){
//		if(file!=null && file.isDirectory() && file.listFiles()!=null){
//                    for(File currFile : file.listFiles()) {
//                        if(currFile!=null){
//                           FolderFileModel dirFileModel = new FolderFileModel(currFile, withChilds, withGrandChilds); 
//                           directChildModelsList.add(dirFileModel);
//                        }else{
//                           System.out.println("NULL FILE ERROR file is null for "+ file);
//                        }
//      
//
//                    }
//                }else{
//                    if(file==null){
//                        System.out.println("NULL FILE FATAL ERROR ");
//                    }else if(file.listFiles()==null && file.isDirectory()){
//                        System.out.println("NULL LIST FILES ERROR "+ file);
//              
////                        Tools.trace("getPath ->"+file.getPath());
////                        Tools.trace("getName ->"+file.getName());
////                        Tools.trace("canExecute ->"+file.canExecute());
////                        Tools.trace("canRead ->"+file.canRead());
////                        Tools.trace("canWrite ->"+file.canWrite());
////                        Tools.trace("isAbsolute ->"+file.isAbsolute());
////                        Tools.trace("isDirectory ->"+file.isDirectory());
////                        Tools.trace("isFile ->"+file.isFile());
////                        Tools.trace("isHidden ->"+file.isHidden());
//                    }
//                    
//                }
//            }
        
    }



    
    
    public void setScaleToSize(int wSize){
        Tools.trace(this, ""+file.getName());
        setScale(getScaleToSize(wSize));
    }
    
    public long getScaleToSize(int wSize){
        return (getByteSize()/((long)wSize));
    }
    
    public void setScale(double s){
        
        //Tools.trace(this, "oldScale="+scale+", newScale="+s+", "+file.getName());
        rescale(s);
        
        
        //rescale(s);
        //fireScaleChangeEvent();
    }

    protected void rescale(double s) {
        scale = s;
        if(isFolder()){
            for(FolderFileModel m : getDirectChildModels()){
                m.rescale(s);
            }
        }
        
        
        fireScaleChangeEvent(!hasParent());
        
    }
    
    
    public double getScale() {
        return this.scale;
    }
    
    
    
    public long getByteSize(){
            if(byteSize==-1){
                byteSize = size(this.file);
            }
            return byteSize;
    }
    
    
    
    public int getGraphicSize(){
        return calculateGraphicSize(getByteSize());
    }
    
    
    public int calculateGraphicSize(long byteSize) {
        long gs = (long) Math.floor(byteSize/scale);
        return Math.round(gs);
    }

    
    public String getSimpleName(){
        return file.getName();
    }
    
    
    public String getSize() {
        return Tools.bytesToStringName(getByteSize());
    }
    
    
    
    
    
  	public File[] getDirectChilds() {
		return file.listFiles();
		
	}
        
        
        

	public List<FolderFileModel> getDirectChildModels() {
            
            
            
            if(directChildModelsList!=null) return directChildModelsList;
            
            
            directChildModelsList = new LinkedList<FolderFileModel>();
            
		if(file!=null && file.isDirectory() && file.listFiles()!=null){
                    for(File currFile : file.listFiles()) {
                        if(currFile!=null){
                           FolderFileModel dirFileModel = new FolderFileModel(currFile, withChilds, withGrandChilds); 
                           directChildModelsList.add(dirFileModel);
                        }else{
                           System.out.println("NULL FILE ERROR file is null for "+ file);
                        }
      

                    }
                }else{
                    if(file==null){
                        System.out.println("NULL FILE FATAL ERROR ");
                    }else if(file.listFiles()==null && file.isDirectory()){
                        System.out.println("NULL LIST FILES ERROR "+ file);
              
//                        Tools.trace("getPath ->"+file.getPath());
//                        Tools.trace("getName ->"+file.getName());
//                        Tools.trace("canExecute ->"+file.canExecute());
//                        Tools.trace("canRead ->"+file.canRead());
//                        Tools.trace("canWrite ->"+file.canWrite());
//                        Tools.trace("isAbsolute ->"+file.isAbsolute());
//                        Tools.trace("isDirectory ->"+file.isDirectory());
//                        Tools.trace("isFile ->"+file.isFile());
//                        Tools.trace("isHidden ->"+file.isHidden());
                    }
                    
                }
            
            
            
            
            
            return directChildModelsList;
	}

        


        
	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}  
        
        
        public boolean isFolder() {
            return this.file.isDirectory();
        }

    
    
    
    
    
    
    
    public void addScaleChangeListener(ScaleChangeListener l){
        //Tools.trace(this, "dodaję"+l.toString());
        scaleChangeListeners.add(l);
        //Tools.trace("scaleChangeListeners dla "+getSimpleName()+ " ma"+ scaleChangeListeners.size()+ " i wygląda tak: "+scaleChangeListeners.toString()  );
        
    }
    
    public void removeScaleChangeListener(ScaleChangeListener l){
        //Tools.trace("USUWAM LISTENERA !!!");
        scaleChangeListeners.remove(l);
    }
    
    
    private void fireScaleChangeEvent(boolean isChangeInitiator) {
        //Tools.trace(this, ""+file.getName()+" dla "+scaleChangeListeners.size()+" elementów");
        for(ScaleChangeListener l : scaleChangeListeners){
            l.scaleChanged(this, isChangeInitiator);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static long size(File file) {
            long size=0;
            if(file!=null && file.exists()) {
                    if(file.canRead()) {
                            if(file.isDirectory()) {
                                if(file.listFiles()!=null){
                                    for(File temp: file.listFiles()) {
                                            if(temp.isDirectory()) {
                                                    size+= size(temp);
                                            }else {
                                                    size+=temp.length();
                                            }
                                    }
                                }
                            }else {
                                    size = file.length();
                            }
                    }
            }
            return size;
    }

    public void setParent(FolderFileModel parent) {
        this.parentModel = parent;
    }
    
    public FolderFileModel getParent(){
        return this.parentModel;
    }
    
    public boolean hasParent(){
        return this.parentModel!=null;
    }









    
}
