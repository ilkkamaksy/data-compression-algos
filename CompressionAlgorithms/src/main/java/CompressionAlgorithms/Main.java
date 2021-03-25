/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms;

import CompressionAlgorithms.ui.MainScene;
import CompressionAlgorithms.io.Io;

/**
 *
 * @author ilkka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.out.println("Please provide arguments: \n\n"
//                    + "[1] source file, e.g. /path/to/file/filename.txt and \n"
//                    + "[2] destination location, e.g. path/to/file/example.txt.\n\n"
//                    + "For example running with gradle: gradle run --args=\"myfile.txt destfile.txt\"");
//            return;
//        }

        MainScene.init(args);        
        
//        String sourceFilePath = args[0];
//        String destinationFilePath = args[1];
//             
//        System.out.println("--" + sourceFilePath);
//        
//        String fileContent = Io.readFile(sourceFilePath);
//        
//        Io.writeToFile(fileContent, destinationFilePath);
//        
//        System.out.println(fileContent);
    }
    
}
