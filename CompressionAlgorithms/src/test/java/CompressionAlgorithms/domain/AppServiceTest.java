package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for AppService 
 */
public class AppServiceTest {
    
    AppService appService = new AppService();
    File testFile;
    String testFileContent = null;
   
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        File testFile = new File("./testfile.txt");
        String testFileContent = "test file content";
    }
    
    @After
    public void tearDown() {
        testFile.delete();
    }

    /**
     * Compressing an empty file throws an exception
     */
    @Test(expected = NullPointerException.class)
    public void compressingInvalidFileThrowsException() {
        File file = new File("./invalidFile.txt");
        appService.compressFileLzw(file);
        
    }
    
    /**
     * Compressing a valid file works
     */
    @Test(expected = NullPointerException.class)
    public void compressingValidFileWorks() throws IOException {
        appService.compressFileLzw(testFile);
       
        File result = new File("./testFile.txt");
        
        assertEquals(Io.readFileContent(result.getAbsolutePath()), "compressed");
        
    }
   
    
}
