package CompressionAlgorithms.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapTest {
    
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
