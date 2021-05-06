package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
import java.io.File;


/**
 * Class responsible for application logic. 
 */
public class AppService {
    File selectedFile = null;
    File lastSavedFile = null;
    String actionStatus = "";

   
    /**
     * Compress the selected file with LZW to target file location
     * @param targetFile File
     * @return boolean success of operation
     */
    public boolean compressWithLzw(File targetFile) {
        setActionStatusBusy();
        String selectedFileContent = Io.readFileContent(selectedFile.getAbsolutePath());
        if (selectedFileContent == null) {
            setActionStatusFail();
            return false;
        }
        List<Byte> compressedContent = Lzw.compress(selectedFileContent);
        return this.saveEncodedBytes(targetFile, compressedContent);    
    }
    
   
    /**
     * Decompress the selected LZW file to target file location
     * @param targetFile File 
     * @return boolean success of operation
     */
    public boolean decompressLzwFile(File targetFile) {
        setActionStatusBusy();
        List<Byte> compressedContent = Io.readBytes(this.selectedFile.getAbsolutePath());
        String decompressedContent = Lzw.decompress(compressedContent);
        boolean success = Io.writeStringToFile(targetFile, decompressedContent);
        setActionStatusBySuccess(success);
        
        return success;
    }
    
    /**
     * Compress the selected file with Huffman Code to target file location
     * @param targetFile File
     * @return boolean success of operation
     */
    public boolean compressWithHff(File targetFile) {
        setActionStatusBusy();
        String selectedFileContent = Io.readFileContent(selectedFile.getAbsolutePath());
        if (selectedFileContent == null) {
            setActionStatusFail();
            return false;
        }
        List<Byte> compressedContent = HuffmanCode.encode(selectedFileContent);
        return this.saveEncodedBytes(targetFile, compressedContent);   
    }
    
    /**
     * Decompress the selected HFF file to target file location
     * @param targetFile File 
     * @return boolean success of operation
     */
    public boolean decompressHffFile(File targetFile) {
        this.setActionStatusBusy();
        List<Byte> compressedContent = Io.readBytes(this.selectedFile.getAbsolutePath());
        String decompressedContent = HuffmanCode.decode(compressedContent);
        boolean success = Io.writeStringToFile(targetFile, decompressedContent);
        setActionStatusBySuccess(success);
        
        return success;
    }
    
    /**
     * Save compressed content to given file
     * 
     * @param file File target file 
     * @param content List<Byte> the list of encoded bytes
     * @return boolean success
     */
    private boolean saveEncodedBytes(File file, List<Byte> content) {
        boolean success = Io.writeBytesToFile(file, content);
        setActionStatusBySuccess(success);
        
        return success;
    }
    
    /**
     * Set action status fail
     */
    private void setActionStatusFail() {
        this.actionStatus = "Could not read file.";
    }
    
    /**
     * Set action status busy
     */
    private void setActionStatusBusy() {
        this.actionStatus = "Working on it...";
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
