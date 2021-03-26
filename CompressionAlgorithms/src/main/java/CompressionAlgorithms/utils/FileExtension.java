package CompressionAlgorithms.utils;

import java.io.File;

/**
 * Utility class for file extensions
 */
public class FileExtension {
    
    /**
     * Make the correct file name extension for LZW compressed files
     * 
     * @return String with extension lzw
     */
    public static String makeInitialLzwFileName(String fileName) {
        int position = fileName.lastIndexOf(".");
        if (position == -1) {
            return fileName + ".lzw";
        }
        
        return fileName.substring(0, position) + ".lzw"; 
    }   
    
}
