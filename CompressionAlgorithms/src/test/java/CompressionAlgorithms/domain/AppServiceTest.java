package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
        
import java.io.File;
import java.io.IOException;
import CompressionAlgorithms.domain.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
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
        List<Integer> mockList = new List<>();
        when(Lzw.compress(this.testFileContent)).thenReturn(mockList);
        
        mockStatic(Io.class);
        when(Io.writeIntegersAsBinaryToFile(targetFile, mockList)).thenReturn(true);
        when(Io.readFileContent(this.appService.getSelectedFile().getAbsolutePath())).thenReturn(this.testFileContent);
        
        boolean result = appService.compressWithLzw(targetFile);
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
        List<Integer> mockList = new List<>();
        when(Lzw.compress(this.testFileContent)).thenReturn(mockList);
        
        mockStatic(Io.class);
        when(Io.saveTextFile(targetFile, "compressed")).thenReturn(false);
        
        boolean success = appService.compressWithLzw(targetFile);
        assertFalse(success);
        assertEquals("Could not read file.", this.appService.getActionStatus());
        
        targetFile.delete();
    }
    
    /**
     * Decompression returns false after failing to decompress a file with LZW
     */
    @Test
    public void returnFalseWithFailedDecompressionWithLzw() throws IOException {
        File targetFile = new File("./targetFile.txt");
        
        mockStatic(Lzw.class);
        List<Integer> mockList = new List<>();
        when(Lzw.decompress(mockList)).thenReturn("");
        
        mockStatic(Io.class);
        when(Io.saveTextFile(targetFile, "")).thenReturn(false);
        
        boolean success = appService.decompressLzwFile(targetFile);
        assertFalse(success);
                
        targetFile.delete();
    }
    
    /**
     * Decompression returns true after successful LZW file decompression
     */
    @Test
    public void returnTrueWithSuccessfulDecompressionWithLzw() throws IOException {
        File targetFile = new File("./targetFile.txt");
        
        appService.setSelectedFile(targetFile);
        
        mockStatic(Lzw.class);
        List<Integer> mockList = new List<>();
        when(Lzw.decompress(mockList)).thenReturn("success");
        
        mockStatic(Io.class);
        when(Io.readLzwFile(targetFile.getAbsolutePath())).thenReturn(mockList);
        when(Io.saveTextFile(targetFile, "success")).thenReturn(true);
        
        boolean success = appService.decompressLzwFile(targetFile);
        assertTrue(success);
                
        targetFile.delete();
    }
}
