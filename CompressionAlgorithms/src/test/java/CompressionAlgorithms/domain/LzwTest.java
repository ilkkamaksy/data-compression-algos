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
    public void returnTrueWhenSuccessfullyCompressedLzw() {
       
        String content = "TOBEORNOTTOBEORTOBEORNOT";
        String result = Lzw.compress(content); 
        assertEquals(result.toString(), "[84, 79, 66, 69, 79, 82, 78, 79, 84, 257, 259, 261, 266, 260, 262, 264]");
       
    }
    
}
