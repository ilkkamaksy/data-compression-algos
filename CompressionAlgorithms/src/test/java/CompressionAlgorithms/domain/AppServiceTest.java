package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
import CompressionAlgorithms.domain.Lzw;
import CompressionAlgorithms.domain.AppService;
        
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * Tests for AppService class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Lzw.class, Io.class})
public class AppServiceTest {
   
    AppService appService;
   
    File testFile;
    String testFileContent;
   
    @Before
    public void setUp() {
        appService = new AppService();
        testFile = new File("./testfile.txt");
        testFileContent = "TOBEORNOTTOBEORTOBEORNOT";
        appService.setSelectedFile(testFile);
    }
    
    @After
    public void tearDown() {
        this.testFile.delete();
    }

    /**
     * Compression returns true after successfully compressing a file with LZW
     */
    @Test
    public void returnTrueWhenSuccessfullyCompressedLzw() throws IOException {
        File targetFile = new File("./targetFile.lzw");
        this.appService.setSelectedFile(testFile);
        
        assertNotNull(this.appService.getSelectedFile());
         
        mockStatic(Lzw.class);
        when(Lzw.compress(this.testFileContent)).thenReturn("compressed");
        
        mockStatic(Io.class);
        when(Io.saveBinaryFile(targetFile, "compressed")).thenReturn(true);
        when(Io.readFileContent(this.appService.getSelectedFile().getAbsolutePath())).thenReturn(this.testFileContent);
        
        boolean result = appService.compressFileLzw(targetFile);
        assertTrue(result);
        targetFile.delete();
        
        
    }

    /**
     * Compression returns false after failing to compress a file with LZW
     */
    @Test
    public void returnFalseWithFailedCompressionWithLzw() throws IOException {
        File targetFile = new File("./targetFile.txt");
        
        mockStatic(Lzw.class);
        when(Lzw.compress(this.testFileContent)).thenReturn("compressed");
        
        mockStatic(Io.class);
        when(Io.saveTextFile(targetFile, "compressed")).thenReturn(false);
        
        appService.compressFileLzw(targetFile);
        assertEquals(this.appService.getActionStatus(), "An ERROR occurred while saving the file");
        
        targetFile.delete();
    }
    
}
