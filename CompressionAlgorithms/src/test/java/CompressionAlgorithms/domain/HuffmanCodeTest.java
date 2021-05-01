package CompressionAlgorithms.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanCodeTest {
    
    @Test
    public void testEncodeSentence() {
        String inputStr = "all letters? try, the quick brown fox jumps over the lazy dog. ";
        String expResult = "00 0000pq0bil000vf0wno0000y0,z0a0?d0r0u0xm000sht000g.0k0cje###10010010101010001011111110111011111101011000100110001101101010000100010001101110011111000100011011001001111101101110100001001010100111011010011011000110010111101110001110111101101011110100001100000011101100011111010001101110011111000101100101000111000000100111011111100011100100";
        String result = HuffmanCode.encode(inputStr);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testEncodeAlphabets() {
        String inputStr = "abcdefghijklmnopqrstuvwxyz .,?";
        String expResult = "0000 z00ic0g.000fp0x?00bh0jk0000y,0vl00wd0te000qa0ro00sn0um###1100101100001011010110111010000011001101001000111001111100111111111101110110100111000110101110010110111101001010100010101000000010000001111000101011";
        String result = HuffmanCode.encode(inputStr);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testDecodeSentence() {
        String encodedStr = "00 0000pq0bil000vf0wno0000y0,z0a0?d0r0u0xm000sht000g.0k0cje###10010010101010001011111110111011111101011000100110001101101010000100010001101110011111000100011011001001111101101110100001001010100111011010011011000110010111101110001110111101101011110100001100000011101100011111010001101110011111000101100101000111000000100111011111100011100100";
        String expResult = "all letters? try, the quick brown fox jumps over the lazy dog. ";
        String result = HuffmanCode.decode(encodedStr);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDecodeAlphabets() {
        String inputStr = "0000 z00ic0g.000fp0x?00bh0jk0000y,0vl00wd0te000qa0ro00sn0um###1100101100001011010110111010000011001101001000111001111100111111111101110110100111000110101110010110111101001010100010101000000010000001111000101011";
        String expResult = "abcdefghijklmnopqrstuvwxyz .,?";
        String result = HuffmanCode.decode(inputStr);
        assertEquals(expResult, result);    
    }
    
    

    
}
