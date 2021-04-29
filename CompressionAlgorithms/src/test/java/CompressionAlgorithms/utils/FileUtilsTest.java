package CompressionAlgorithms.utils;

import java.io.File;
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
        File testFile = new File("./testfile.txt");
        String result = FileUtils.setInitialFileExtension(testFile);
        assertEquals("testfile.lzw", result);
        testFile.delete();
    }
    
    /**
     * setInitialFileExtension returns txt file if given a lzw file
     */
    @Test
    public void initialFileExtensionTxtWhenLzwGiven() {
        File testFile = new File("./testfile.lzw");
        String result = FileUtils.setInitialFileExtension(testFile);
        assertEquals("testfile.txt", result);
        testFile.delete();
    }

    /**
     * isLzwFile returns true when given lzw file
     */
    @Test
    public void testIsLzwFile1() {
        File testFile = new File("./testfile.lzw");
        boolean result = FileUtils.isLzwFile(testFile);
        assertTrue(result);
        testFile.delete();
    }
    
    /**
     * isLzwFile returns false when given txt file
     */
    @Test
    public void testIsLzwFile2() {
        File testFile = new File("./testfile.txt");
        boolean result = FileUtils.isLzwFile(testFile);
        assertFalse(result);
        testFile.delete();
    }
    
    /**
     * isLzwFile returns false when given a file without extension
     */
    @Test
    public void testIsLzwFile3() {
        File testFile = new File("./testfile");
        boolean result = FileUtils.isLzwFile(testFile);
        assertFalse(result);
        testFile.delete();
    }
    
}
