package CompressionAlgorithms.domain;

import java.io.File;


/**
 * Class responsible for application logic. 
 */
public class AppService {
    File selectedFile;

    /*
    * Get selected file.
    * @return selectedFile File
    */
    public File getSelectedFile() {
        return selectedFile;
    }

    /*
    * Set selected file.
    * @param selectedFile File
    */
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
    
    
}
