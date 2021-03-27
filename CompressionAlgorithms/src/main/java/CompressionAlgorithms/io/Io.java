package CompressionAlgorithms.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
    public static String readFileContent(String fileNameFullPath) throws IOException {
        String fileContent = null;
        DataInputStream reader = new DataInputStream(new FileInputStream(fileNameFullPath));
        int nBytesToRead = reader.available();
        if (nBytesToRead > 0) {
            byte[] bytes = new byte[nBytesToRead];
            reader.read(bytes);
            fileContent = new String(bytes);
        }
        return fileContent;
    }
    
     
    /**
     * Write a given uncompressed content to a given file location.
     * 
     * @param file File
     * @param content String
     */
    public static boolean saveTextFile(File file, String content) {
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();    
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    
        return false;
        
    }
    
    /**
     * Save given content as binary file to a given file location
     * @param file File destination file
     * @param content String content
     * @return boolean
     */
    public static boolean saveBinaryFile(File file, String content) {
        
        byte[] data = content.getBytes(StandardCharsets.UTF_8);
 
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(content);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
    
}