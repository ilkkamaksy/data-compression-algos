/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilkka
 */
public class MinHeapTest {
    
    public MinHeapTest() {
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

    @Test
    public void elementsCanBeAdded() {
        MinHeap instance = new MinHeap();
        for (int i = 0; i < 256; i++) {
            HuffmanNode element = new HuffmanNode((char) i, i, null, null);  
            instance.add(element);
        }
        
        assertEquals(256, instance.size());
    }
    
    @Test
    public void pollReturnsElementWithSmallestFreq() {
        MinHeap instance = new MinHeap();
        int freq = 1000;
        for (int i = 97; i < 123; i++) {
            freq--;
            HuffmanNode element = new HuffmanNode((char) i, freq, null, null);  
            System.out.println("char " + (char) i + "\t" + freq);
            instance.add(element);
        }
        HuffmanNode root = instance.poll();
        assertEquals('z', root.value);
        
        root = instance.poll();
        assertEquals('y', root.value);
        root = instance.poll();
        assertEquals('x', root.value);
        root = instance.poll();
        assertEquals('w', root.value);
        root = instance.poll();
        assertEquals('v', root.value);
        root = instance.poll();
        assertEquals('u', root.value);
        
    }
    
    @Test
    public void pollReturnsNullWhenEmpty() {
        MinHeap instance = new MinHeap();
        assertNull(instance.poll());
    }

    @Test
    public void emptyHeapSizeIsZero() {
        MinHeap instance = new MinHeap();
        assertEquals(0, instance.size());
    }
    
}
