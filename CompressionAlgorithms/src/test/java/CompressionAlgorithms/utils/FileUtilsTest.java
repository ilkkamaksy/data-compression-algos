package CompressionAlgorithms.utils;

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
public class FileUtilsTest {
    

    /**
     * setInitialFileExtension returns lzw file if given a txt file
     */
    @Test
    public void initialFileExtensionLzwWhenTxtGiven() {
        String fileName = "test.txt";
        String result = FileUtils.setInitialFileExtension(fileName);
        assertEquals("test.lzw", result);
    }
    
    /**
     * setInitialFileExtension returns txt file if given a lzw file
     */
    @Test
    public void initialFileExtensionTxtWhenLzwGiven() {
        String fileName = "test.lzw";
        String result = FileUtils.setInitialFileExtension(fileName);
        assertEquals("test.txt", result);
    }

    /**
     * isLzwFile returns true when given lzw file
     */
    @Test
    public void testIsLzwFile1() {
        String fileName = "test.lzw";
        boolean result = FileUtils.isLzwFile(fileName);
        assertTrue(result);
    }
    
    /**
     * isLzwFile returns false when given txt file
     */
    @Test
    public void testIsLzwFile2() {
        String fileName = "test.txt";
        boolean result = FileUtils.isLzwFile(fileName);
        assertFalse(result);
    }
    
    /**
     * isLzwFile returns false when given a file without extension
     */
    @Test
    public void testIsLzwFile3() {
        String fileName = "test";
        boolean result = FileUtils.isLzwFile(fileName);
        assertFalse(result);
    }
    
}
