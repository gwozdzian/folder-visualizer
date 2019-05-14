/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer;


import java.io.File;

/**
 *
 * @author user
 */
public class NonGuiMainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File(".");
        String listMeth = pl.gwozdzian.utils.Tools.listMethodsResult(file.getPath(), file.getName(), file.canExecute(), file.canRead(), file.canWrite(), file.isAbsolute(), file.isDirectory(), file.isFile(), file.isHidden());
        //Tools.trace(file.getPath(), "dla getPath()");

//Tools.trace(listMeth);
        //Tools.trace(Tools.getAllStockTraces());
    }
    
}
