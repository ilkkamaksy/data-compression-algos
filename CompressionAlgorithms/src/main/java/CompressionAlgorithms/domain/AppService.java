package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
import CompressionAlgorithms.utils.FileExtension;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Class responsible for application logic. 
 */
public class AppService {
    File selectedFile = null;
    File lastSavedFile = null;
    String actionStatus = "";

   
    /**
     * Compress the selected file to target file location
     * @param targetFile 
     */
    public boolean compressFileLzw(File targetFile) {
        String selectedFileContent = Io.readFileContent(selectedFile.getAbsolutePath());
        if (selectedFileContent == null) {
            this.actionStatus = "Could not read file.";
            return false;
        }
        List<Integer> compressedContent = Lzw.compress(selectedFileContent);
        return this.saveCompressedFile(targetFile, compressedContent);    
    }
    
   
    /**
     * Decompress the selected file to target file location
     * @param targetFile File 
     */
    public boolean decompressLzwFile(File targetFile) {
        List<Integer> compressedContent = Io.openBinaryFile(this.selectedFile.getAbsolutePath());
        String decompressedContent = Lzw.decompress(compressedContent);
        boolean success = Io.saveTextFile(targetFile, decompressedContent);
        
        if (success) {
            this.actionStatus = "File decompressed and saved successfully";
            this.lastSavedFile = targetFile;
        } else {
            this.actionStatus = "Could not save file.";
        }
        
        return success;
    }
    
    
    /**
     * Save a compressed file to disk
     * 
     * @param file File to be saved
     * @param content String the content of the file to be saved
     */
    private boolean saveCompressedFile(File file, List<Integer> content) {
        boolean success = Io.saveBinaryFile(file, content);
        
        if (success) {
            this.actionStatus = "File compressed and saved successfully";
            this.lastSavedFile = file;
        } else {
            this.actionStatus = "Could not save file.";
        }

        return success;
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
