package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
import CompressionAlgorithms.utils.FileUtils;
import java.io.File;
import java.io.IOException;


/**
 * Class responsible for application logic. 
 */
public class AppService {
    File selectedFile = null;
    File lastSavedFile = null;
    String actionStatus = "";

   
    /**
     * Compress the selected file with LZW to target file location
     * @param targetFile 
     * @return boolean success of operation
     */
    public boolean compressWithLzw(File targetFile) {
        String selectedFileContent = Io.readFileContent(selectedFile.getAbsolutePath());
        if (selectedFileContent == null) {
            this.actionStatus = "Could not read file.";
            return false;
        }
        List<Integer> compressedContent = Lzw.compress(selectedFileContent);
        return this.saveLzwEncodingToFile(targetFile, compressedContent);    
    }
    
   
    /**
     * Decompress the selected LZW file to target file location
     * @param targetFile File 
     * @return boolean success of operation
     */
    public boolean decompressLzwFile(File targetFile) {
        List<Integer> compressedContent = Io.readLzwFile(this.selectedFile.getAbsolutePath());
        String decompressedContent = Lzw.decompress(compressedContent);
        boolean success = Io.saveTextFile(targetFile, decompressedContent);
        setActionStatusBySuccess(success);
        
        return success;
    }
    
    /**
     * Compress the selected file with Huffman Code to target file location
     * @param targetFile 
     * @return boolean success of operation
     */
    public boolean compressWithHff(File targetFile) {
        String selectedFileContent = Io.readFileContent(selectedFile.getAbsolutePath());
        if (selectedFileContent == null) {
            this.actionStatus = "Could not read file.";
            return false;
        }
        String compressedContent = HuffmanCode.encode(selectedFileContent);
        return this.saveHuffmanEncodingToFile(targetFile, compressedContent);   
    }
    
    /**
     * Decompress the selected HFF file to target file location
     * @param targetFile File 
     * @return boolean success of operation
     */
    public boolean decompressHffFile(File targetFile) {
        
        String compressedContent = Io.readHffFile(this.selectedFile.getAbsolutePath());
        String decompressedContent = HuffmanCode.decode(compressedContent);
        boolean success = Io.saveTextFile(targetFile, decompressedContent);
        setActionStatusBySuccess(success);
        
        return success;
    }
    
    /**
     * Save LZW compressed content to file
     * 
     * @param file File target file
     * @param content List<Integer> the group of integers encoded with lzw
     * @return boolean success
     */
    private boolean saveLzwEncodingToFile(File file, List<Integer> content) {
        boolean success = Io.writeIntegersAsBinaryToFile(file, content);
        setActionStatusBySuccess(success);
        
        return success;
    }
    
    /**
     * Save Huffman encoded string to file
     * 
     * @param file File target file
     * @param content String the Huffman encoded string
     * @return boolean success
     */
    private boolean saveHuffmanEncodingToFile(File file, String content) {
        boolean success = Io.writeHuffmanCodeAsBinaryToFile(file, content);
        setActionStatusBySuccess(success);

        return success;
    }
    
    /**
     * Set action status by success of operation
     * @param success boolean 
     */
    private void setActionStatusBySuccess(boolean success) {
        if (success) {
            this.actionStatus = "File saved successfully";
        } else {
            this.actionStatus = "Could not save file.";
        }
    }
    
    // Getters and setters 
    
    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public File getLastSavedFile() {
        return lastSavedFile;
    }
}
