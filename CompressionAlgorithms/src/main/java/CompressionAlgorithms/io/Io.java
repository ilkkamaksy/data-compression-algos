package CompressionAlgorithms.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import CompressionAlgorithms.domain.List;
import CompressionAlgorithms.utils.DataUtils;
import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The class responsible for file IO operations.
 */
public class Io {
    
    private BufferedOutputStream out;  
    private int buffer;                
    private int n;                     
    
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
    public static boolean saveTextFile(File file, String content) {
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
     * Write a a group of integers as 16 bit values to a file
     * @param file File target file
     * @param content List<Integer> group of integers
     * @return boolean
     */
    public static boolean writeIntegersAsBinaryToFile(File file, List<Integer> content) {
        if (file == null || content == null) {
            return false;
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < content.size(); i++) {       
                byte[] bytes = DataUtils.convertIntToByteArray(content.get(i));
                outputStream.write(bytes);    
            }
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
   
    /**
     * Save a string as bits to a file
     * @param file File destination file
     * @param content List<Integer> content
     * @return boolean
     */
    public static boolean writeStringAsBinaryFile(File file, String content) {
        if (file == null || content == null) {
            return false;
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i++) {       
                outputStream.write(content.charAt(i));    
            }
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    /**
     * Open binary file 
     * @param fileName String full path to file
     * @return List<Integer>
     */
    public static List<Integer> openBinaryFile(String fileName) {
        
        try {
            byte[] byteTable = Files.readAllBytes(Paths.get(fileName));
            List<Integer> fileContent = new List<>();
            for (int i = 0; i < byteTable.length - 1; i = i + 2) {
                byte[] entry = {
                    byteTable[i], 
                    byteTable[i + 1]
                };
                fileContent.add(DataUtils.convertByteArrayToInt(entry));
            }
            
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
       
}