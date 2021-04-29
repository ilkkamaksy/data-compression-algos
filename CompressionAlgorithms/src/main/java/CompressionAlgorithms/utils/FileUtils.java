package CompressionAlgorithms.utils;

import java.io.File;

/**
 * Utility class for handling file extensions
 */
public class FileUtils {
    
    /**
     * Make target file extension for compressed and decompressed files
     * @param fileName String 
     * @return String filename with extension lzw or txt
     */
    public static String setInitialFileExtension(File file) {
        String fileName = file.getName();
        int position = fileName.lastIndexOf(".");
        if (isLzwFile(file) || isHffFile(file)) {
            return fileName.substring(0, position) + ".txt";
        } 
        return fileName.substring(0, position) + ".lzw";
    }
   
    /**
     * Check if the file is a LZW file
     * @param fileName String the file name
     * @return boolean
     */
    public static boolean isLzwFile(File file) {
        int position = file.getName().lastIndexOf(".lzw");
        if (position == -1) {
            return false;
        }
        return true;
    }
    
    /**
     * Check if the file is a HFF file
     * @param fileName String the file name
     * @return boolean
     */
    public static boolean isHffFile(File file) {
        int position = file.getName().lastIndexOf(".hff");
        if (position == -1) {
            return false;
        }
        return true;
    }
    
}
