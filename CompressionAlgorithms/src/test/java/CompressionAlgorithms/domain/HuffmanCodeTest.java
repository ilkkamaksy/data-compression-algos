package CompressionAlgorithms.domain;

import CompressionAlgorithms.domain.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanCodeTest {
    
    @Test
    public void testEncodeSentence() {
        String inputStr = "all letters? try, the quick brown fox jumps over the lazy dog. ";
        String expResult = "0000y0,z0sh00a0?d0u0xm00000vf0wno000pq0bil00re00t00g.0k0cj #01001011101111111011110111100111001101110000100101011111110011000000000101111111000011110111111010010110101011111011101110110111110101011001001100010100011111110000110010111011111110111101100111110100000101111100110000011011100111111100001111011111101101000001100001111010111001111010011101011111";
        List<Character> encoded = HuffmanCode.encode(inputStr);
        String result = "";
        for (int i = 0; i < encoded.size(); i++) {
            result += encoded.get(i);
        }
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testEncodeAlphabets() {
        String inputStr = "abcdefghijklmnopqrstuvwxyz .,?";
        String expResult = "0000sn0qa00ro0um0000y,0vl00wd0te000 z00ic0g.000pf0x?00bh0jk#0011111100110101101011011111100111011011110111010011111011111110011011100010101111000001001000000101100110100101010011101010000110011100011011110001111011";
        List<Character> encoded = HuffmanCode.encode(inputStr);
        String result = "";
        for (int i = 0; i < encoded.size(); i++) {
            result += encoded.get(i);
        }
        assertEquals(expResult, result);
        
    }

    @Test
    public void testDecodeSentence() {
        String encodedStr = "0000y0,z0sh00a0?d0u0xm00000vf0wno000pq0bil00re00t00g.0k0cj #01001011101111111011110111100111001101110000100101011111110011000000000101111111000011110111111010010110101011111011101110110111110101011001001100010100011111110000110010111011111110111101100111110100000101111100110000011011100111111100001111011111101101000001100001111010111001111010011101011111";
        String expResult = "all letters? try, the quick brown fox jumps over the lazy dog. ";
        String result = HuffmanCode.decode(encodedStr);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDecodeAlphabets() {
        String inputStr = "0000sn0qa00ro0um0000y,0vl00wd0te000 z00ic0g.000pf0x?00bh0jk#0011111100110101101011011111100111011011110111010011111011111110011011100010101111000001001000000101100110100101010011101010000110011100011011110001111011";
        String expResult = "abcdefghijklmnopqrstuvwxyz .,?";
        String result = HuffmanCode.decode(inputStr);
        assertEquals(expResult, result);    
    }
    
    

    
}
