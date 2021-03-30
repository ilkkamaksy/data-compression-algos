package CompressionAlgorithms.utils;

import java.io.File;

/**
 * Utility class for file extensions
 */
public class FileExtension {
    
    /**
     * Make target lzw file extension for LZW compressed files
     * @param fileName String 
     * @return String filename.lzw 
     */
    public static String makeInitialLzwFileName(String fileName) {
        int position = fileName.lastIndexOf(".");
        if (position == -1) {
            return fileName + ".lzw";
        }
        
        return fileName.substring(0, position) + ".lzw"; 
    }
    
    /**
     * Make target txt file extension for decompressed LZW files
     * @param fileName String
     * @return String filename.txt 
     */
    public static String makeInitialTxtFileName(String fileName) {
        int position = fileName.lastIndexOf(".");
        if (position == -1) {
            return fileName + ".txt";
        }
        
        return fileName.substring(0, position) + ".txt"; 
    }
    
    /**
     * Check if the file is a LZW file
     * @param fileName String the file name
     * @return boolean
     */
    public static boolean isLzwFile(String fileName) {
        int position = fileName.lastIndexOf(".lzw");
        if (position == -1) {
            return false;
        }
        return true;
    }
    
}
