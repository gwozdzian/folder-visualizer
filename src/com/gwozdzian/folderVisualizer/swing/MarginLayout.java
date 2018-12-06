/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.folderVisualizer.swing;

/**
 *
 * @author user
 */
/**
 *
 * @author user
 */
import java.awt.*;

public class MarginLayout implements LayoutManager {
    
    private int minWidth = 0, minHeight = 0;
    private int preferredWidth = 0, preferredHeight = 0;
    private boolean sizeUnknown = true;
    private final int top;
    private final int bottom;
    private final int left;
    private final int right;

    public MarginLayout(int t,int b,int l, int r) {
        top = t;
        bottom = b;
        left = l;
        right = r;
    }

 

    /* Required by LayoutManager. */
    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    /* Required by LayoutManager. */
    @Override
    public void removeLayoutComponent(Component comp) {
    }

    private void setSizes(Container parent) {
        int nComps = parent.getComponentCount();
        Dimension d = null;

        //Reset preferred/minimum width and height.
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                d = c.getPreferredSize();

                if (i > 0) {
                    preferredWidth += d.width;
                    preferredHeight = Math.max(preferredHeight, d.height);
                } else {
                    preferredWidth = d.width;
                    preferredHeight = d.height;
                }

                
                
                
                
                d = c.getMinimumSize();

                if (i > 0) {
                    minWidth += d.width;
                    minHeight = Math.max(minHeight, d.height);
                } else {
                    minWidth = d.width;
                    minHeight = d.height;
                }



                
            }
        }
        
        
    preferredWidth += left;
    preferredWidth += right;
    preferredHeight += top;
    preferredHeight += bottom;


    minWidth += left;
    minWidth += right;
    minHeight += top;
    minHeight += bottom;
            
    //preferredWidth = minWidth;
    //preferredHeight = minHeight;
        
    }


    /* Required by LayoutManager. */
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        setSizes(parent);

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = preferredWidth
                    + insets.left + insets.right;
        dim.height = preferredHeight
                     + insets.top + insets.bottom;

        //setSizes(parent);
        sizeUnknown = false;

        return dim;
    }

    /* Required by LayoutManager. */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        setSizes(parent);
        sizeUnknown = false;
        //Always add the container's insets!
        Insets insets = parent.getInsets();
        //System.out.println("Insets: "+insets.toString()+ " dla "+parent.getName()+", "+parent.toString());
        dim.width = minWidth
                    + insets.left + insets.right;
        dim.height = minHeight
                     + insets.top + insets.bottom;

        //setSizes(parent);
        

        return dim;
    }

    /* Required by LayoutManager. */
    /*
     * This is called when the panel is first displayed,
     * and every time its size changes.
     * Note: You CAN'T assume preferredLayoutSize or
     * minimumLayoutSize will be called -- in the case
     * of applets, at least, they probably won't be.
     */
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth()
                       - (insets.left + insets.right);
        int maxHeight = parent.getHeight()
                        - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();
        int previousWidth = 0, previousHeight = 0;
        int x = left, y = top;


        // Go through the components' sizes, if neither
        // preferredLayoutSize nor minimumLayoutSize has
        // been called.
        if (sizeUnknown) {
            setSizes(parent);
        }



        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();

                 // increase x and y, if appropriate

                
                
                c.setBounds(x, y, d.width, d.height);
                
                x+=d.width;
       

                previousWidth = d.width;
                previousHeight = d.height;
            }
        }
    }

    public String toString() {
      
        return getClass().getName() + "[left=" + left +", top="+top+ ", right="+right+", bottom="+bottom+"]";
    }
}