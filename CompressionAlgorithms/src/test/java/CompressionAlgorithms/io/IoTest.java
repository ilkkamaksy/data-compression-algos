package CompressionAlgorithms.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for IO class
 */
public class IoTest {
    
    File testFile = new File("./testfile.txt");
    String testFileContent = "test file content";
    
    @Before
    public void setUp() {
        try {
            FileWriter writer = new FileWriter(testFile);
            writer.write(testFileContent);
            writer.close();    
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        
    }
    
    @After
    public void tearDown() {
        this.testFile.delete();
    }

    /**
     * The file content of an existing file can be read
     */
    @Test
    public void readFileContentHappyCase() throws Exception {
        
        String fileNameFullPath = testFile.getAbsolutePath();
        String expResult = testFileContent;
        String result = Io.readFileContent(fileNameFullPath);
        assertEquals(expResult, result);
        
    }

    /**
     * Reading an invalid file returns an IOException
     */
    @Test(expected = IOException.class)
    public void readInvalidFileContentThrowsException() throws Exception {
        String fileNameFullPath = "";
        String result = Io.readFileContent(fileNameFullPath);
    }
    
    /**
     * A valid file can be saved to disk
     */
    @Test
    public void saveFileHappyCase() throws Exception {
        File file = new File("./tempFile.txt");
        String content = "temp content";
        Io.saveFile(file, content);
        
        File fileOnDisk = new File("./tempFile.txt");
        assertEquals(file.getAbsolutePath(), fileOnDisk.getAbsolutePath());
        
        file.delete();
    }
    
    /**
     * Saving an invalid file throws IO exception
     */
    @Test(expected = IOException.class)
    public void saveFileWithoutNameThrowsIOException() throws Exception {
        File file = new File("");
        String content = "temp content";
        Io.saveFile(file, content);
    }
    
}
