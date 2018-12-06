/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer;

import com.gwozdzian.folderVisualizer.swing.JFolderStrip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author user
 */
public class JFolderStripMainClass extends JFrame {

    public JFolderStripMainClass() {
        this.setSize(600, 300);
        List<JFolderStrip> folderStripList = new ArrayList();
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/Rok 2002/Zdjęcia (ap cyfrowy od pana Tomka)/Koncert"), null));
        folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/"), null));
        //folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/"), null));
        //folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/"), null));
        //folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/Rok 2002/"), null));
        //folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/Rok 2002/Zdjęcia (ap cyfrowy od pana Tomka)/"), null));
        //folderStripList.add(new JFolderStrip(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/Rok 2002/Zdjęcia (ap cyfrowy od pana Tomka)/Koncert"), null));
        
        
        
        for(JFolderStrip folderStrip : folderStripList){
            this.getContentPane().add(new JScrollPane(folderStrip));
            folderStrip.setScaleToSize(2500);
        }
        //this.getContentPane().add(new JScrollPane(folderStrip));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        this.pack();
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JFolderStripMainClass();
    }
    
}
