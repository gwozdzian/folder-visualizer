package com.gwozdzian.folderVisualizer.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;



public class GuiTools {

	public static Dimension getScreenDimension() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
	}

	
	public static JLabel createLabel(String labelName) {
		return createLabel(labelName, 12);
	}
	
	public static JLabel createLabel(String labelName, int fontSize) {
		return createLabel(labelName, fontSize,  Font.PLAIN,  "Arial");
	}
	
	public static JLabel createLabel(String labelName, int fontSize, int fontType, String fontName) {
		JLabel label = new JLabel(labelName);
		label.setFont(new Font(fontName, fontType, fontSize));
		

		return label;
	}
	
	
	
        
	
	

}
