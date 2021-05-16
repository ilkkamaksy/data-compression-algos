package CompressionAlgorithms;

import CompressionAlgorithms.metrics.Compare;
import CompressionAlgorithms.ui.Launcher;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        for (String arg : args) {
            if (arg.equals("compare")) {
                Compare.run();
                return;
            }
        }
       
        Launcher.run(args);     
        

    }
    
}
