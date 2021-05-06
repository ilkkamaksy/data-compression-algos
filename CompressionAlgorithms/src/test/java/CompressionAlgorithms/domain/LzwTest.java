package CompressionAlgorithms.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import CompressionAlgorithms.domain.List;
import CompressionAlgorithms.utils.DataUtils;

/**
 * Test for LZW class
 */
public class LzwTest {
     
    @Test
    public void returnTrueWhenSuccessfullyCompressed1() {
        String content = "TOBEORNOTTOBEORTOBEORNOT";
        List<Byte> byteList = Lzw.compress(content);
        List<Integer> result = new List<>();
        for (int i = 0; i < byteList.size() - 1; i = i + 2) {
            byte[] bytes = {
                byteList.get(i),
                byteList.get(i + 1)
            };
            result.add((DataUtils.convert2BytesToInt(bytes)));
        }
        assertEquals("[84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]", result.toString());       
    }
    
    @Test
    public void returnTrueWhenSuccessfullyCompressed2() {
        String content = "YESNOHUFTHUFTHUFYHUFYHUFYHK";
        List<Byte> byteList = Lzw.compress(content); 
        List<Integer> result = new List<>();
        for (int i = 0; i < byteList.size() - 1; i = i + 2) {
            byte[] bytes = {
                byteList.get(i),
                byteList.get(i + 1)
            };
            result.add((DataUtils.convert2BytesToInt(bytes)));
        }
        assertEquals("[89, 69, 83, 78, 79, 72, 85, 70, 84, 261, 263, 265, 89, 267, 269, 75]", result.toString());       
    }
    
    @Test
    public void returnEmptyListWhenCompressingEmptyString() {
        String content = "";
        List<Byte> result = Lzw.compress(content); 
        assertEquals(0, result.size());       
    }
    
    @Test
    public void returnTrueWhenSuccessfullyDecompressed() {  
//        String content = "TOBEORNOTTOBEORTOBEORNOT";
//        List<Byte> compressed = Lzw.compress(content); 
//        
//        String result = Lzw.decompress(compressed);
//        assertEquals("TOBEORNOTTOBEORTOBEORNOT", result);
    }
    
    @Test
    public void returnEmptyStringWhenDecompressedEmptyList() {  
        String content = "";
        List<Byte> compressed = Lzw.compress(content); 
        assertEquals(0, compressed.size());
    }
    
}
