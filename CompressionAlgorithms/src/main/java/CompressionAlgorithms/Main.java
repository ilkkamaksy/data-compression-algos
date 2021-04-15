/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms;

import CompressionAlgorithms.domain.HuffmanCode;
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

        String inputStr = "this is an example for huffman encoding";
        String res = HuffmanCode.encode(inputStr);
        
        System.out.println("res " + res);
        
        Launcher.run(args); 
        
    }
    
}
