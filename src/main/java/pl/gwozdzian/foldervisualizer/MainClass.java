/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.foldervisualizer;

import pl.gwozdzian.foldervisualizer.swing.FolderFileModel;
import pl.gwozdzian.foldervisualizer.swing.GuiTools;
import pl.gwozdzian.foldervisualizer.swing.FolderFile;
import pl.gwozdzian.foldervisualizer.swing.JFolderStrip;
import pl.gwozdzian.foldervisualizer.swing.MarginLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import pl.gwozdzian.codestopwatch.CodeStopwatch;
import pl.gwozdzian.utils.Tools;
import pl.gwozdzian.codestopwatch.swing.JCodeStopwatchComponent;

/**
 *
 * @author user
 */
public class MainClass extends JFrame{

    protected JScrollPane scroolPane;
    protected FolderFile jFolder = null;
    protected JScrollPane stopwatchPane;
    private JCodeStopwatchComponent stopwatchComponent = null;

    public MainClass() {
        
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
        

        
        
    }

    protected void createGUI() {
        scroolPane = new JScrollPane();
        stopwatchPane = new JScrollPane();
        stopwatchPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        stopwatchPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        stopwatchPane.setPreferredSize(new Dimension(600, 200));
        Tools.trace("SCREEN SIZE = "+Toolkit.getDefaultToolkit().getScreenSize().toString());
        //SwingUtilities.
        //stopwatchPane.setMinimumSize(new Dimension(300,100));
        //stopwatchPane.setMaximumSize(new Dimension(500,200));
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        //this.setMinimumSize(new Dimension(300,200));
        //this.setMaximumSize(new Dimension(500,300));
        System.out.println(this.getLayout().toString());
        System.out.println(this.getContentPane().getLayout().toString());
        
        
        createComponents();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
    
    
    protected void createComponents() {
        //new FolderFile(new File("C:/Projekty"));

        //FolderFile jFolder = FolderFile.createFolderFile(new File("C:/FOTO - storage/"), null, true);//POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/Rok 2002/Zdjęcia (ap cyfrowy od pana Tomka)/Koncert/
       
        //FolderFile jFolder = FolderFile.createFolderFile(new File("C:/Projekty/"), null, true);//Zdjęcia (ap cyfrowy od pana Tomka)/Koncert/
       //jFolder.setScaleToSize(500);
       this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
       
       this.getContentPane().add(scroolPane);
       this.getContentPane().add(stopwatchPane);
       
       JFolderStrip folderStrip = new JFolderStrip(new File("C:\\Users\\user\\Desktop\\CV"), null);
       this.getContentPane().add(new JScrollPane(folderStrip));
       folderStrip.setScaleToSize(600);
       
       
       JSlider slid = new JSlider(1, 5, 2);
       slid.setMajorTickSpacing(1);
       slid.setPaintTicks(true);
       slid.setPaintLabels(true);
       slid.setPaintTrack(true);
       slid.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //Tools.trace(this, "");
            }
           
       });
       
       slid.setName("Wartość powiększania / pomniejszania");
       this.getContentPane().add(slid);
       
       JButton button = new JButton("Skaluj do ekranu");
       button.setPreferredSize(new Dimension(100,40));
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jFolder != null){
                    jFolder.setScaleToSize(600);
                }
                
            }
            
        });
        
        this.getContentPane().add(button);
        
        
       JButton butt2 = new JButton("Pomniejsz");
       butt2.setPreferredSize(new Dimension(100,40));
        butt2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(jFolder!=null){
                    ((JComponent)e.getSource()).setEnabled(false);
                    double multiplier = ((double)slid.getValue())/10;
                    double newScale = (jFolder.getScale()+(jFolder.getScale()*multiplier));
                    Tools.trace("Slider value: "+slid.getValue()+"newScale:"+newScale+", oldScale:"+jFolder.getScale());
                    jFolder.setScale(newScale);
//                    jFolder.invalidate();
//                    jFolder.repaint();
//                    SwingUtilities.getWindowAncestor(jFolder).pack();
                    ((JComponent)e.getSource()).setEnabled(true);
                }
            }
            
        });
        
        this.getContentPane().add(butt2);
        
        
        
       JButton butt3 = new JButton("Powiększ");
       butt3.setPreferredSize(new Dimension(100,40));
        butt3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jFolder!=null){
                    ((JComponent)e.getSource()).setEnabled(false);
                    double multiplier = ((double)slid.getValue())/10;
                    double newScale = Math.max(1, (jFolder.getScale()-(jFolder.getScale()*multiplier)));
                    Tools.trace("Slider value: "+slid.getValue()+"newScale:"+newScale+", oldScale:"+jFolder.getScale());
                    CodeStopwatch.start("jFolder.setScale()");
                    jFolder.setScale(newScale);
                    CodeStopwatch.stop("jFolder.setScale()");
                    
//                    CodeStopwatch.start("jFolder.invalidate()");
//                    jFolder.invalidate();
//                    CodeStopwatch.stop("jFolder.invalidate()");
//                    
//                    CodeStopwatch.start("jFolder.repaint()");
//                    jFolder.repaint();
//                    CodeStopwatch.stop("jFolder.repaint()");
//                    
//                    CodeStopwatch.start("jFolder...pack()");
//                    SwingUtilities.getWindowAncestor(jFolder).pack();
//                    CodeStopwatch.stop("jFolder...pack()");
                    ((JComponent)e.getSource()).setEnabled(true);
                }
            }
            
        });
        
        
        this.getContentPane().add(butt3);
        
        
        
       JButton butt4 = new JButton("Otwórz inny dialog");
       butt4.setPreferredSize(new Dimension(100,40));
        butt4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
		changeJFolderFile();
            }
            
        });
        
        
        
        
        this.getContentPane().add(butt4);
        
        
        
       JButton butt5 = new JButton("Pokaż logi czasowe");
       butt5.setPreferredSize(new Dimension(100,40));
        butt5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
		CodeStopwatch.getStopwatchJSON();
                addStopwatchComponent();
                
            }


            
        });
        
        
        
        
     
        
        this.getContentPane().add(butt5);       
       JLabel scaleLabel = new JLabel();
       this.getContentPane().add(scaleLabel);
        
       JSlider scaleSlid = new JSlider(1, 60, 1);
       scaleSlid.setMajorTickSpacing(10);
       scaleSlid.setValue((int) Math.round(40/0.5));
       scaleSlid.setPaintTicks(true);
       scaleSlid.setPaintLabels(true);
       scaleSlid.setPaintTrack(true);
       
       scaleSlid.addMouseListener(new MouseAdapter(){
           @Override
           public void mouseReleased(MouseEvent e) {
               double scaleToSet = 40/(double)scaleSlid.getValue();
               
               if(stopwatchComponent!=null){
                   Tools.trace("Old scale = "+stopwatchComponent.getScale()+" , new scale = "+scaleToSet);
                   scaleLabel.setText("Skala = "+scaleToSet);
                   stopwatchComponent.setScale(scaleToSet);
                   
                   SwingUtilities.getWindowAncestor(stopwatchPane).pack();
               }
           }
           
       });
       
       scaleSlid.addChangeListener(new ChangeListener(){
           @Override
           public void stateChanged(ChangeEvent e) {
               Tools.trace("stateChanged() to "+scaleSlid.getValue());
           }
           
       });
       scaleSlid.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //Tools.trace(this, "");
            }
           
       });
       
       scaleSlid.setName("Skaluj Stopwatch Displayera");
       this.getContentPane().add(scaleSlid);
        
        
        //changeJFolderFile();
        
        
        //JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        //fileChooser.showDialog(this, "Text w fileChooserze");
        
                
                
   
    }
    
    
    private void addStopwatchComponent() {
           //this.getContentPane().add(CodeStopwatch.getJStopwatchComponent(false));
           CodeStopwatch.getStopwatchJSON();
            try {
                CodeStopwatch.writeStopwatchJsonToJsonFile(new File("C:\\Projekty\\code_stopwatch_snapshot_"+System.currentTimeMillis()+".json").toPath(), false);
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
           stopwatchComponent = CodeStopwatch.getJStopwatchComponent(true);
           stopwatchPane.getViewport().removeAll();
           stopwatchPane.getViewport().add(stopwatchComponent);
           SwingUtilities.getWindowAncestor(stopwatchPane).pack();
           

    }       
    
    
    protected void loadFolderFile(File file) {
        CodeStopwatch.start();
        scroolPane.getViewport().removeAll();
        jFolder = FolderFile.createFolderFile(file, null, true);
        scroolPane.getViewport().add(jFolder);
        jFolder.setScaleToSize(800);
        SwingUtilities.getWindowAncestor(scroolPane).pack();
        CodeStopwatch.stop();
    }
    
    
    protected void changeJFolderFile() throws HeadlessException {
         JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
         jfc.setDialogTitle("Choose a directory to save your file: ");
         jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         jfc.setCurrentDirectory(new File("/User/alvinreyes"));

        //int result = jFileChooser.showOpenDialog(new JFrame());


         int returnValue = jfc.showOpenDialog(new JFrame());
         if (returnValue == JFileChooser.APPROVE_OPTION) {
             if (jfc.getSelectedFile().isDirectory()) {
                 loadFolderFile(jfc.getSelectedFile());
             }
         }
     }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           CodeStopwatch.setTraceToConsole(false);
           new MainClass();
        
        
        
        //FolderModel fM = new FolderFileModel(new File("C:/FOTO - storage/POZOSTAŁE (do przeglądnięcia)/Zdjęcia - SKATALOGOWANE/Rok 2002/")); //
        //FolderModel fM = new FolderFileModel(new File("C:/Projekty/")); //
//        FolderFileModel fM = new FolderFileModel(new File("C:/FOTO - storage/")); //
//        fM.getGraphicSize();
//        System.out.println(fM.getSimpleName()+"   "+ fM.getSize()+", dla skali="+fM.getScale()+"   "+fM.getGraphicSize()+"px");
//        fM.setScaleToSize(800);
//        long s = 1000000;
//        System.out.println("Dla "+Tools.bytesToStringName(s)+" potrzeba "+fM.calculateGraphicSize(s)+"px");
//      
//        s = 1000;
//        System.out.println("Dla "+Tools.bytesToStringName(s)+" potrzeba "+fM.calculateGraphicSize(s)+"px");
//
//        s = 100000000;
//        System.out.println("Dla "+Tools.bytesToStringName(s)+" potrzeba "+fM.calculateGraphicSize(s)+"px");        
//        
//        s = 1000000000;
//        System.out.println("Dla "+Tools.bytesToStringName(s)+" potrzeba "+fM.calculateGraphicSize(s)+"px");        
//        
//                
//
//        s = 1000000000;
//        System.out.println("Dla "+Tools.bytesToStringName(s)+" potrzeba "+fM.calculateGraphicSize(s)+"px");        
//
//        s = 2000000000;
//        System.out.println("Dla "+Tools.bytesToStringName(s)+" potrzeba "+fM.calculateGraphicSize(s)+"px");        
//                
//        
//        System.out.println(fM.getSimpleName()+"   "+ fM.getSize()+", dla skali="+fM.getScale()+"   "+fM.getGraphicSize()+"px");
    }


}
