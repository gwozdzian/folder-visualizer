/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gwozdzian.foldervisualizer.swing;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class FolderModelTest {
    
    public FolderModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    


    /**
     * Test of setScaleToSize method, of class FolderFileModel.
     */
    @Test
    public void testSetScaleToSize() {
        System.out.println("setScaleToSize");
        int wSize = 0;
        FolderFileModel instance = null;
        instance.setScaleToSize(wSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScale method, of class FolderFileModel.
     */
    @Test
    public void testSetScale() {
        System.out.println("setScale");
        long s = 0L;
        FolderFileModel instance = null;
        instance.setScale(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByteSize method, of class FolderFileModel.
     */
    @Test
    public void testByteSize() {
        System.out.println("byteSize");
        FolderFileModel instance = null;
        long expResult = 0L;
        long result = instance.getByteSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class FolderFileModel.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        File file = null;
        long expResult = 0L;
        long result = FolderFileModel.size(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
