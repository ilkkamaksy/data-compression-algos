package CompressionAlgorithms.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import CompressionAlgorithms.domain.List;
import java.nio.ByteBuffer;
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
     * Save given content as binary file to a given file location
     * @param file File destination file
     * @param content List<Integer> content
     * @return boolean
     */
    public static boolean saveBinaryFile(File file, List<Integer> content) {
        if (file == null || content == null) {
            return false;
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < content.size(); i++) {
                byte[] bytes = convertIntToByteArray(content.get(i));
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
     * Open binary file 
     * @param fileName String full path to file
     * @return List<Integer>
     */
    public static List<Integer> openBinaryFile(String fileName) {
        
        try {
            byte[] byteTable = Files.readAllBytes(Paths.get(fileName));
            List<Integer> fileContent = new List<>();
            for (int i = 0; i < byteTable.length - 3; i = i + 4) {
                byte[] entry = {
                    byteTable[i], 
                    byteTable[i + 1], 
                    byteTable[i + 2], 
                    byteTable[i + 3]
                };
                fileContent.add(convertByteArrayToInt(entry));
            }
            
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * Convert integer to byte array
     * @param value int to convert
     * @return byte[]
     */
    private static byte[] convertIntToByteArray(int value) {
        return new byte[] {
            (byte) (value >> 24),
            (byte) (value >> 16),
            (byte) (value >> 8),
            (byte) value 
        };
    }
    
    /**
     * Convert byte array to integer
     * @param bytes to convert
     * @return integer
     */
    private static int convertByteArrayToInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
               ((bytes[1] & 0xFF) << 16) |
               ((bytes[2] & 0xFF) << 8) |
               ((bytes[3] & 0xFF) << 0);
    }
    
}