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
    
    private static BufferedOutputStream out;  
    private static int buffer;                
    private static int n;                     
    
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
                byte[] bytes = DataUtils.convertIntTo2Bytes(content.get(i));
                outputStream.write(bytes);    
            }
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static boolean writeBytes(File file, List<Byte> bytes) {
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
     * Save a string as binary to a file
     * @param file File destination file
     * @param content List<Integer> content
     * @return boolean
     */
    public static boolean writeHuffmanCodeAsBinaryToFile(File file, List<Character> content) {
        if (file == null || content.size() == 0) {
            return false;
        }

        try {
            FileOutputStream os = new FileOutputStream(file);
            out = new BufferedOutputStream(os);
            boolean isHeader = true;
            for (int i = 0; i < content.size(); i++) { 
                
                char c = content.get(i);

                if (isHeader) {
                    writeCharAsByte(c);
                } else {
                    addBitToBuffer(c);
                }
                
                if (c == '#') {
                    isHeader = false;
                }
                
            }
            
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    /**
     * Write char as a byte to output
     * @param c char
     */
    private static void writeCharAsByte(char c) {
        byte bits = (byte) c;
        try {
            out.write(bits);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a bit to the buffer
     * @param c char of the Huffman code
     */
    private static void addBitToBuffer(char c) {     
        buffer <<= 1;
        if (c == '1') {
            buffer |= 1;
        }
        
        n++;
        if (n == 8) {
            writeBufferAndFlush();
        }
    } 
    
    /**
     * Write buffer to output and flush the buffer
     */
    private static void writeBufferAndFlush() {
        if (n == 0) {
            return;
        }
        
        try {
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }
    
    /**
     * Read LZW file 
     * @param fileName String full path to file
     * @return List<Integer> LZW encoded content
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
    
    /**
     * Read HFF file 
     * @param fileName String full path to file
     * @return String HFF encoded content
     */
    public static String readHffFile(String fileName) {
        
        String content = "";
        
        try {
            byte[] byteTable = Files.readAllBytes(Paths.get(fileName));
            
            for (int i = 0; i < byteTable.length; i++) {
                
                int charInt = DataUtils.convertByteToInt(byteTable[i]);

                if (content.indexOf("#") == -1) {
                    content += (char) charInt;
                } else {  
                    content += intToBinaryString(charInt);    
                }
            }
            
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return "";
    }
    
    /**
     * Integer to 8 bit string 
     * @param value int to translate
     * @return String s
     */
    private static String intToBinaryString(int value) {
        String s = Integer.toBinaryString(value);
        if (s.length() < 8) {
            String prefix = "";
            for (int j = 0; j < (8 - s.length()); j++) {
                prefix += "0";
            }
            s = prefix + s;
        }
        return s;
    }
       
}