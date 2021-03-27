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
     * @param targetFile to be compressed
     */
    public boolean compressFileLzw(File targetFile) {
        String selectedFileContent = this.readFileContent(this.selectedFile);
        String compressedContent = Lzw.compress(selectedFileContent);

        return this.saveFile(targetFile, compressedContent);    
    }
    
    /**
     * Read content for a given file
     * @param file File to be read
     * @return content String
     */
    private String readFileContent(File file) {
        String content = null;
        try {
            content = Io.readFileContent(selectedFile.getAbsolutePath());
        } catch (IOException e) {
            this.actionStatus = "An ERROR occurred while reading the file:" + e.getMessage();
        }
        
        return content;
    }
    
    
    /**
     * Save a file to disk
     * 
     * @param file File to be saved
     * @param content String the content of the file to be saved
     */
    private boolean saveFile(File file, String content) {
        boolean result = Io.saveFile(file, content);
        
        if (result) {
            this.actionStatus = "File compressed and saved successfully";
            this.lastSavedFile = file;
        } else {
            this.actionStatus = "An ERROR occurred while saving the file";
        }

        return result;
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
