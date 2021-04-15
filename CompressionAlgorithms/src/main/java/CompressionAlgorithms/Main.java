/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms;

import CompressionAlgorithms.domain.HuffmanCode;
import CompressionAlgorithms.ui.Launcher;
import CompressionAlgorithms.domain.HashTable;

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
        HashTable<Character, String> res = HuffmanCode.encode(inputStr);
        
        System.out.println("res");
        for (Character c: inputStr.toCharArray()) {
            
            System.out.println(c + "\t" + res.get(c));
        }
        
        Launcher.run(args); 
        
    }
    
}
