package CompressionAlgorithms.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The class responsible for file IO operations.
 */
public class Io {
    
    /**
     * Read content of a file given the full path to the file. 
     * 
     * @param fileNameFullPath String
     * @return fileContent String
     */
    public static String readFileContent(String fileNameFullPath) {
       
        String fileContent = null;
        
        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(fileNameFullPath));
            int nBytesToRead = reader.available();
            if (nBytesToRead > 0) {
                byte[] bytes = new byte[nBytesToRead];
                reader.read(bytes);
                fileContent = new String(bytes);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 

        return fileContent;
    }
    
     
    /**
     * Write a given content to a given file location.
     * 
     * @param file File
     * @param content String
     */
    public static void saveFile(File file, String content) throws IOException {
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }
    
}