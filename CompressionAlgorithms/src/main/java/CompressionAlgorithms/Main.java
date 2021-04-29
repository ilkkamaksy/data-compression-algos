/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms;

import CompressionAlgorithms.domain.HuffmanCode;
import CompressionAlgorithms.domain.HuffmanNode;
import CompressionAlgorithms.ui.Launcher;

/**
 *
 * @author ilkka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String inputStr = "The brown fox jumped over the lazy dog!";
        
        String res = HuffmanCode.encode(inputStr);
        
        System.out.println("enc");
        System.out.println(res);

        String decodedStr = HuffmanCode.decode(res, HuffmanCode.header);
        
        System.out.println("dec");
        System.out.println(decodedStr);
        
        Launcher.run(args); 
        
    }
    
}
