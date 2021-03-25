package CompressionAlgorithms.domain;

import CompressionAlgorithms.io.Io;
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
     * Save the file
     * 
     * @param fileToSave File
     */
    public void saveFile(File fileToSave) {
        try {
            String content = Io.readFileContent(selectedFile.getAbsolutePath());
            Io.saveFile(fileToSave, content);
            this.actionStatus = "File saved: " + fileToSave.getName();
            this.lastSavedFile = fileToSave;
            
        } catch (IOException e) {
            this.actionStatus = "An ERROR occurred while saving the file!" + fileToSave.getName();
            System.out.println(e.getMessage());
        }
    }
    
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
