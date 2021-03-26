package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
import CompressionAlgorithms.utils.FileExtension;
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
     * Compress a file
     * 
     * @param file to be compressed
     */
    public void compressFileLzw(File file) {
        String content = Io.readFileContent(selectedFile.getAbsolutePath());
        String compressedContent = Lzw.compress(content);
        this.saveFile(file, compressedContent);
    }
    
    
    /**
     * Save a file to disk
     * 
     * @param file File to be saved
     * @param content String the content of the file to be saved
     */
    private void saveFile(File file, String content) {
        try {
            Io.saveFile(file, content);
            this.actionStatus = "File compressed and saved successfully";
            this.lastSavedFile = file;
            
        } catch (IOException e) {
            this.actionStatus = "An ERROR occurred while saving the file:" + e.getMessage();
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
