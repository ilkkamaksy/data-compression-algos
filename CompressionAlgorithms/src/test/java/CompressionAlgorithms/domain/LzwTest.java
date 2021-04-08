package CompressionAlgorithms.domain;

import CompressionAlgorithms.domain.Lzw;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

/**
 * Test for LZW class
 */
public class LzwTest {
     
    @Test
    public void returnTrueWhenSuccessfullyCompressed1() {
        String content = "TOBEORNOTTOBEORTOBEORNOT";
        String result = Lzw.compress(content).toString(); 
        assertEquals("[84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]", result.toString());       
    }
    
    @Test
    public void returnTrueWhenSuccessfullyCompressed2() {
        String content = "YESNOHUFTHUFTHUFYHUFYHUFYHK";
        String result = Lzw.compress(content).toString(); 
        assertEquals("[89, 69, 83, 78, 79, 72, 85, 70, 84, 261, 263, 265, 89, 267, 269, 75]", result.toString());       
    }
    
    @Test
    public void returnEmptyListWhenCompressingEmptyString() {
        String content = "";
        List<Integer> result = Lzw.compress(content); 
        assertEquals(0, result.size());       
    }
    
    @Test
    public void returnTrueWhenSuccessfullyDecompressed() {  
        String content = "TOBEORNOTTOBEORTOBEORNOT";
        List<Integer> compressed = Lzw.compress(content); 
        
        String result = Lzw.decompress(compressed);
        assertEquals("TOBEORNOTTOBEORTOBEORNOT", result);
    }
    
    @Test
    public void returnEmptyStringWhenDecompressedEmptyList() {  
        String content = "";
        List<Integer> compressed = Lzw.compress(content); 
        assertEquals(0, compressed.size());
    }
    
}
