package CompressionAlgorithms.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
    public static String readFile(String fileNameFullPath) {
        File file = new File(fileNameFullPath);
        String fileContent = readFileContent(file);
        return fileContent;
    }
    
     /**
     * Write a given text string to a given file location.
     * 
     * @param content String
     * @param fileNameFullPath String
     */
    public static void writeToFile(String content, String fileNameFullPath) {
        
        try {
            Path path = Paths.get(fileNameFullPath);
            byte[] strToBytes = content.getBytes();
            Files.write(path, strToBytes);    
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private static String readFileContent(File file) {
        
        StringBuilder data = new StringBuilder();
        
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
              data.append(reader.nextLine());
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        
        return data.toString();
    }
}
