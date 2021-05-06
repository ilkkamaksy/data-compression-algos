package CompressionAlgorithms.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import CompressionAlgorithms.domain.List;
import java.nio.file.Files;
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
        if (fileNameFullPath.isEmpty()) {
            return null;
        }
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
            e.printStackTrace();
        }

        return fileContent;
    }
    
     
    /**
     * Write a given uncompressed content to a given file location.
     * 
     * @param file File
     * @param content String
     */
    public static boolean writeStringToFile(File file, String content) {
        if (content == null || file == null) {
            return false;
        }
        if (content.isEmpty()) {
            return false;
        }
        
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();    
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return false;
        
    }
    
    
    /**
     * Write a list of bytes to a given target file
     * @param file File the target file
     * @param bytes List<Byte> list of bytes to write
     * @return boolean success 
     */
    public static boolean writeBytesToFile(File file, List<Byte> bytes) {
        if (file == null || bytes == null) {
            return false;
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < bytes.size(); i++) {       
                outputStream.write(bytes.get(i));    
            }
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    

    /**
     * Read bytes from a given file
     * @param fileName String full path to file
     * @return List<Byte> 
     */
    public static List<Byte> readBytes(String fileName) {
        
        try {
            byte[] byteTable = Files.readAllBytes(Paths.get(fileName));
            List<Byte> fileContent = new List<>();
            for (int i = 0; i < byteTable.length; i++) {
                fileContent.add(byteTable[i]);
            }
            
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
   
}