package CompressionAlgorithms.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
     * @param content List<Integer> content
     * @return boolean
     */
    public static boolean saveBinaryFile(File file, List<Integer> content) {
        
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(content);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            List<Integer> listFromFile = (ArrayList) obj;
            return listFromFile;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
}