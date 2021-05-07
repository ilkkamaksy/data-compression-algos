package CompressionAlgorithms.domain;

import CompressionAlgorithms.domain.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanCodeTest {
    
    @Test
    public void testEncodeSentence() {
        String inputStr = "all letters? try, the quick brown fox jumps over the lazy dog. ";
        String expResult = "[48, 48, 48, 48, 121, 48, 44, 122, 48, 115, 104, 48, 48, 97, 48, 63, 100, 48, 117, 48, 120, 109, 48, 48, 48, 48, 48, 118, 102, 48, 119, 110, 111, 48, 48, 48, 112, 113, 48, 98, 105, 108, 48, 48, 114, 101, 48, 48, 116, 48, 48, 103, 46, 48, 107, 48, 99, 106, 32, 35, 75, -65, -67, -25, 55, 9, 95, -52, 1, 127, 15, 126, -106, -81, -69, -73, -43, -109, 20, 127, 12, -69, -5, -39, -12, 23, -52, 27, -97, -61, -33, -76, 24, 122, -25, -89, 95, 1]";
        List<Byte> encoded = HuffmanCode.encode(inputStr);
        
        assertEquals(expResult, encoded.toString());
        
    }
    
    @Test
    public void testEncodeAlphabets() {
        String inputStr = "abcdefghijklmnopqrstuvwxyz .,?";
        String expResult = "[48, 48, 48, 48, 115, 110, 48, 113, 97, 48, 48, 114, 111, 48, 117, 109, 48, 48, 48, 48, 121, 44, 48, 118, 108, 48, 48, 119, 100, 48, 116, 101, 48, 48, 48, 32, 122, 48, 48, 105, 99, 48, 103, 46, 48, 48, 48, 112, 102, 48, 120, 63, 48, 48, 98, 104, 48, 106, 107, 35, 63, 53, -83, -7, -37, -35, 62, -2, 110, 43, -63, 32, 89, -91, 78, -95, -100, 111, 30, 3, 0]";
        List<Byte> encoded = HuffmanCode.encode(inputStr);
        
        assertEquals(expResult, encoded.toString());
        
    }

    @Test
    public void testDecodeSentence() {
        String inputStr = "all letters? try, the quick brown fox jumps over the lazy dog. ";
        List<Byte> encoded = HuffmanCode.encode(inputStr);
        
        String result = HuffmanCode.decode(encoded);
        assertEquals(inputStr, result);
    }
    
    @Test
    public void testDecodeAlphabets() {
        String inputStr = "abcdefghijklmnopqrstuvwxyz .,?";
        List<Byte> encoded = HuffmanCode.encode(inputStr);
        
        String expResult = "abcdefghijklmnopqrstuvwxyz .,?";
        String result = HuffmanCode.decode(encoded);
        assertEquals(expResult, result);    
    }
    
    

    
}
