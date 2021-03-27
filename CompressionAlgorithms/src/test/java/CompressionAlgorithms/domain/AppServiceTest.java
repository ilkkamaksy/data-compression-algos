package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
        
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;


/**
 * Tests for AppService class
 */
public class AppServiceTest {
   
    AppService appService;
    Io mockIo;
    
    File testFile = new File("./testfile.txt");
    String testFileContent = "test file content";
   
    @Before
    public void setUp() {
        appService = new AppService();
        mockIo = mock(Io.class);

        appService.setSelectedFile(testFile);
    }
    
    @After
    public void tearDown() {
        this.testFile.delete();
    }

    /**
     * Return true after successfully compressing a file with LZW
     */
    @Test
    public void returnTrueWhenSuccessfullyCompressedLzw() {
        File targetFile = new File("targetFile.txt");
        
        boolean result = appService.compressFileLzw(targetFile);
        assertTrue(result);
        
    }

    /**
     * Return false after failing to compress a file with LZW
     */
    @Test
    public void returnFalseWithFailedCompressionWithLzw() throws IOException {
        File targetFile = new File("targetFile.txt");
        
        doThrow(new IOException()).when(mockIo).saveFile(targetFile, "compressed");

        boolean result = appService.compressFileLzw(targetFile);
        assertTrue (result);
        
    }
    
}
